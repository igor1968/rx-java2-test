package com.igordanilchik.daggertest;

import android.util.Log;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

@Module
public class HttpClientModule {
    private static final String LOG_TAG = HttpClientModule.class.getSimpleName();

    @Provides
    @Singleton
    OkHttpClient provideHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            httpClient.addInterceptor(logging);

            httpClient.addNetworkInterceptor(chain -> {
                okhttp3.Response originalResponse = chain.proceed(chain.request());
                return originalResponse.newBuilder()
                        .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                        .build();
            });
        }
        return httpClient.build();
    }

    private static class ProgressResponseBody extends ResponseBody {

        private final ResponseBody responseBody;
        private final ProgressListener progressListener;
        private BufferedSource bufferedSource;

        ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
            this.responseBody = responseBody;
            this.progressListener = progressListener;
        }

        @Override public MediaType contentType() {
            return responseBody.contentType();
        }

        @Override public long contentLength() {
            return responseBody.contentLength();
        }

        @Override public BufferedSource source() {
            if (bufferedSource == null) {
                bufferedSource = Okio.buffer(source(responseBody.source()));
            }
            return bufferedSource;
        }

        private Source source(Source source) {
            return new ForwardingSource(source) {
                long totalBytesRead = 0L;

                @Override public long read(Buffer sink, long byteCount) throws IOException {
                    long bytesRead = super.read(sink, byteCount);
                    // read() returns the number of bytes read, or -1 if this source is exhausted.
                    totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                    if (responseBody.contentLength() > 0) {
                        progressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                    } else {
                        progressListener.update(totalBytesRead, bytesRead == -1);
                    }
                    return bytesRead;
                }
            };
        }
    }

    interface ProgressListener {
        //Known content length
        void update(long bytesRead, long contentLength, boolean done);
        //Unknown content length
        void update(long bytesRead, boolean done);
    }

    private final ProgressListener progressListener = new ProgressListener() {
        @Override
        public void update(long bytesRead, long contentLength, boolean done) {
            update(bytesRead, done);
            Log.d(LOG_TAG, "Content length: " + contentLength);
            Log.d(LOG_TAG, String.format("%d%% done\n", (100 * bytesRead) / contentLength));
        }

        @Override
        public void update(long bytesRead, boolean done) {
            Log.d(LOG_TAG, "Bytes read: " + bytesRead);
            Log.d(LOG_TAG, "Done: " + done);
        }
    };
}
