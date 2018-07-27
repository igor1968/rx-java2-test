package com.igordanilchik.daggertest.common.mvp.presenter;

import android.annotation.SuppressLint;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpPresenter;
import com.igordanilchik.daggertest.common.mvp.view.AppBaseView;
import com.igordanilchik.daggertest.repo.SchedulersSet;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import rx.Completable;
import rx.Observable;
import rx.Scheduler;
import rx.Single;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

import static com.igordanilchik.daggertest.common.mvp.presenter.AppBasePresenter.ExecuteOn.COMPUTATION_DESTROY;
import static com.igordanilchik.daggertest.common.mvp.presenter.AppBasePresenter.ExecuteOn.COMPUTATION_DESTROY_DELAY_ERROR;
import static com.igordanilchik.daggertest.common.mvp.presenter.AppBasePresenter.ExecuteOn.COMPUTATION_DETACH;
import static com.igordanilchik.daggertest.common.mvp.presenter.AppBasePresenter.ExecuteOn.COMPUTATION_DETACH_DELAY_ERROR;
import static com.igordanilchik.daggertest.common.mvp.presenter.AppBasePresenter.ExecuteOn.IMMEDIATE_DESTROY;
import static com.igordanilchik.daggertest.common.mvp.presenter.AppBasePresenter.ExecuteOn.IMMEDIATE_DESTROY_DELAY_ERROR;
import static com.igordanilchik.daggertest.common.mvp.presenter.AppBasePresenter.ExecuteOn.IMMEDIATE_DETACH;
import static com.igordanilchik.daggertest.common.mvp.presenter.AppBasePresenter.ExecuteOn.IMMEDIATE_DETACH_DELAY_ERROR;
import static com.igordanilchik.daggertest.common.mvp.presenter.AppBasePresenter.ExecuteOn.IO_DESTROY;
import static com.igordanilchik.daggertest.common.mvp.presenter.AppBasePresenter.ExecuteOn.IO_DESTROY_DELAY_ERROR;
import static com.igordanilchik.daggertest.common.mvp.presenter.AppBasePresenter.ExecuteOn.IO_DETACH;
import static com.igordanilchik.daggertest.common.mvp.presenter.AppBasePresenter.ExecuteOn.IO_DETACH_DELAY_ERROR;


/**
 * @author Igor Danilchik
 */

public class AppBasePresenter<View extends AppBaseView> extends MvpPresenter<View> {


    private final SchedulersSet schedulersSet;

    private final CompositeSubscription compositeSubscriptionDetach = new CompositeSubscription();
    private final CompositeSubscription compositeSubscriptionDestroy = new CompositeSubscription();

    protected AppBasePresenter(@NonNull final SchedulersSet schedulersSet) {
        this.schedulersSet = schedulersSet;
    }
    
    private void addSubscription(@NonNull final Subscription subscription, final boolean onDestroy) {
        if (onDestroy) {
            compositeSubscriptionDestroy.add(subscription);
        } else {
            compositeSubscriptionDetach.add(subscription);
        }
    }

    @Override
    public void detachView(final View view) {
        super.detachView(view);
        compositeSubscriptionDetach.clear();
    }

    @Override
    public void destroyView(final View view) {
        super.destroyView(view);
        compositeSubscriptionDetach.clear();
        compositeSubscriptionDestroy.clear();
    }

    protected void clearSubscription() {
        compositeSubscriptionDetach.clear();
        compositeSubscriptionDestroy.clear();
    }

    @IntDef({
            IO_DETACH,
            IO_DETACH_DELAY_ERROR,
            IO_DESTROY,
            IO_DESTROY_DELAY_ERROR,
            COMPUTATION_DETACH,
            COMPUTATION_DETACH_DELAY_ERROR,
            COMPUTATION_DESTROY,
            COMPUTATION_DESTROY_DELAY_ERROR,
            IMMEDIATE_DETACH,
            IMMEDIATE_DETACH_DELAY_ERROR,
            IMMEDIATE_DESTROY,
            IMMEDIATE_DESTROY_DELAY_ERROR
    })
    @Retention(RetentionPolicy.SOURCE)
    protected @interface ExecuteOn {

        /**
         * <pre>
         *  {@code}
         *  subscribeOn(Schedulers.ioScheduler())
         *  observeOn(Schedulers.uiScheduler())
         *  subscribe(... , delayError = false)
         *  {@code}
         *
         *  {@code} compositeSubscription.clear() {@code} @ DetachView
         *  </pre>
         **/
        int IO_DETACH = 1;

        /**
         * <pre>
         *  {@code}
         *  subscribeOn(Schedulers.ioScheduler())
         *  observeOn(Schedulers.uiScheduler())
         *  subscribe(... , delayError = true)
         *  {@code}
         *
         *  {@code} compositeSubscription.clear() {@code} @ DetachView
         *  </pre>
         **/
        int IO_DETACH_DELAY_ERROR = 2;

        /**
         * <pre>
         *  {@code}
         *  subscribeOn(Schedulers.ioScheduler())
         *  observeOn(Schedulers.uiScheduler())
         *  subscribe(... , delayError = false)
         *  {@code}
         *
         *  {@code} compositeSubscription.clear() {@code} @ DestroyView
         *  </pre>
         **/
        int IO_DESTROY = 3;

        /**
         * <pre>
         *  {@code}
         *  subscribeOn(Schedulers.ioScheduler())
         *  observeOn(Schedulers.uiScheduler())
         *  subscribe(... , delayError = true)
         *  {@code}
         *
         *  {@code} compositeSubscription.clear() {@code} @ DestroyView
         *  </pre>
         **/
        int IO_DESTROY_DELAY_ERROR = 4;

        /**
         * <pre>
         *  {@code}
         *  subscribeOn(Schedulers.computationScheduler())
         *  observeOn(Schedulers.uiScheduler())
         *  subscribe(... , delayError = false)
         *  {@code}
         *
         *  {@code} compositeSubscription.clear() {@code} @ DetachView
         *  </pre>
         **/
        int COMPUTATION_DETACH = 5;

        /**
         * <pre>
         *  {@code}
         *  subscribeOn(Schedulers.computationScheduler())
         *  observeOn(Schedulers.uiScheduler())
         *  subscribe(... , delayError = true)
         *  {@code}
         *
         *  {@code} compositeSubscription.clear() {@code} @ DetachView
         *  </pre>
         **/
        int COMPUTATION_DETACH_DELAY_ERROR = 6;

        /**
         * <pre>
         *  {@code}
         *  subscribeOn(Schedulers.computationScheduler())
         *  observeOn(Schedulers.uiScheduler())
         *  subscribe(... , delayError = false)
         *  {@code}
         *
         *  {@code} compositeSubscription.clear() {@code} @ DestroyView
         *  </pre>
         **/
        int COMPUTATION_DESTROY = 7;

        /**
         * <pre>
         *  {@code}
         *  subscribeOn(Schedulers.computationScheduler())
         *  observeOn(Schedulers.uiScheduler())
         *  subscribe(... , delayError = true)
         *  {@code}
         *
         *  {@code} compositeSubscription.clear() {@code} @ DestroyView
         *  </pre>
         **/
        int COMPUTATION_DESTROY_DELAY_ERROR = 8;

        /**
         * <pre>
         *  {@code}
         *  subscribeOn(Schedulers.immediateScheduler())
         *  observeOn(Schedulers.immediateScheduler())
         *  subscribe(... , delayError = false)
         *  {@code}
         *
         *  {@code} compositeSubscription.clear() {@code} @ DetachView
         *  </pre>
         **/
        int IMMEDIATE_DETACH = 9;

        /**
         * <pre>
         *  {@code}
         *  subscribeOn(Schedulers.immediateScheduler())
         *  observeOn(Schedulers.immediateScheduler())
         *  subscribe(... , delayError = true)
         *  {@code}
         *
         *  {@code} compositeSubscription.clear() {@code} @ DetachView
         *  </pre>
         **/
        int IMMEDIATE_DETACH_DELAY_ERROR = 10;

        /**
         * <pre>
         *  {@code}
         *  subscribeOn(Schedulers.immediateScheduler())
         *  observeOn(Schedulers.immediateScheduler())
         *  subscribe(... , delayError = false)
         *  {@code}
         *
         *  {@code} compositeSubscription.clear() {@code} @ DestroyView
         *  </pre>
         **/
        int IMMEDIATE_DESTROY = 11;

        /**
         * <pre>
         *  {@code
         *  subscribeOn(Schedulers.immediateScheduler())
         *  observeOn(Schedulers.immediateScheduler())
         *  subscribe(... , delayError = true)
         *  }
         *
         *  {@code compositeSubscription.clear() } @ DestroyView
         *  </pre>
         **/
        int IMMEDIATE_DESTROY_DELAY_ERROR = 12;
    }

    protected <RESULT> Subscription executeOn(
            @ExecuteOn final int executeOn,
            @NonNull final Observable<RESULT> observable,
            @NonNull final Action1<RESULT> onNextAction,
            @NonNull final Action1<Throwable> onErrorAction,
            @NonNull final Action0 onCompleteAction,
            @NonNull final Observable.Transformer<RESULT, RESULT> onSchedulersApplied
    ) {
        return execute(
                executeOn,
                observable,
                onNextAction,
                onErrorAction,
                onCompleteAction,
                onSchedulersApplied
        );
    }

    protected <RESULT> Subscription executeOn(
            @ExecuteOn final int executeOn,
            @NonNull final Observable<RESULT> observable,
            @NonNull final Action1<RESULT> onNextAction,
            @NonNull final Action1<Throwable> onErrorAction,
            @NonNull final Action0 onCompleteAction
    ) {
        return execute(
                executeOn,
                observable,
                onNextAction,
                onErrorAction,
                onCompleteAction,
                o -> o
        );
    }

    protected <RESULT> Subscription executeOn(
            @ExecuteOn final int executeOn,
            @NonNull final Observable<RESULT> observable,
            @NonNull final Action1<RESULT> onNextAction,
            @NonNull final Action1<Throwable> onErrorAction
    ) {
        return executeOn(
                executeOn,
                observable,
                onNextAction,
                onErrorAction,
                () -> {
                },
                (o) -> o
        );
    }

    protected <RESULT> Subscription executeOn(
            @ExecuteOn final int executeOn,
            @NonNull final Observable<RESULT> observable,
            @NonNull final Action1<RESULT> onNextAction
    ) {
        return executeOn(
                executeOn,
                observable,
                onNextAction,
                (t) -> {
                },
                () -> {
                },
                (o) -> o
        );
    }

    protected <RESULT> Subscription executeOn(
            @ExecuteOn final int executeOn,
            @NonNull final Observable<RESULT> observable,
            @NonNull final Action1<RESULT> onNextAction,
            @NonNull final Action1<Throwable> onErrorAction,
            @NonNull final Observable.Transformer<RESULT, RESULT> onSchedulersApplied
    ) {

        return executeOn(
                executeOn,
                observable,
                onNextAction,
                onErrorAction,
                () -> {
                },
                onSchedulersApplied
        );
    }

    protected Subscription executeOn(
            @ExecuteOn final int executeOn,
            @NonNull final Completable completable,
            @NonNull final Action1<Throwable> onErrorAction,
            @NonNull final Action0 onCompleteAction,
            @NonNull final Completable.Transformer onSchedulersApplied
    ) {

        final boolean clearOnDestroy = isClearCompositeSubscriptionOnDestroy(executeOn);

        final Subscription subscription = completable
                .compose(applyCompletableSchedulers(executeOn))
                .compose(onSchedulersApplied)
                .subscribe(
                        onCompleteAction,
                        throwable -> {
                            Timber.e(throwable);
                            onErrorAction.call(throwable);
                        }
                );

        addSubscription(subscription, clearOnDestroy);

        return subscription;
    }

    protected Subscription executeOn(
            @ExecuteOn final int executeOn,
            @NonNull final Completable completable,
            @NonNull final Action1<Throwable> onErrorAction,
            @NonNull final Action0 onCompleteAction
    ) {

        return executeOn(
                executeOn,
                completable,
                onErrorAction,
                onCompleteAction,
                c -> c
        );
    }

    protected Subscription executeOn(
            @ExecuteOn final int executeOn,
            @NonNull final Completable completable,
            @NonNull final Action0 onCompleteAction
    ) {

        return executeOn(
                executeOn,
                completable,
                t -> {
                },
                onCompleteAction,
                c -> c
        );
    }

    protected Subscription executeOn(
            @ExecuteOn final int executeOn,
            @NonNull final Completable completable,
            @NonNull final Action1<Throwable> onErrorAction
    ) {

        return executeOn(
                executeOn,
                completable,
                onErrorAction,
                () -> {
                },
                c -> c
        );
    }

    protected Subscription executeOn(
            @ExecuteOn final int executeOn,
            @NonNull final Completable completable
    ) {

        return executeOn(
                executeOn,
                completable,
                t -> {
                },
                () -> {
                },
                c -> c
        );
    }

    protected <RESULT> Subscription executeOn(
            @ExecuteOn final int executeOn,
            @NonNull final Single<RESULT> single,
            @NonNull final Action1<RESULT> onSuccess,
            @NonNull final Action1<Throwable> onErrorAction,
            @NonNull final Single.Transformer<RESULT, RESULT> onSchedulersApplied
    ) {

        final boolean clearOnDestroy = isClearCompositeSubscriptionOnDestroy(executeOn);

        final Subscription subscription = single
                .compose(applySingleSchedulers(executeOn))
                .compose(onSchedulersApplied)
                .subscribe(
                        onSuccess,
                        throwable -> {
                            Timber.e(throwable);
                            onErrorAction.call(throwable);
                        }
                );

        addSubscription(subscription, clearOnDestroy);

        return subscription;
    }

    protected <RESULT> Subscription executeOn(
            @ExecuteOn final int executeOn,
            @NonNull final Single<RESULT> single,
            @NonNull final Action1<RESULT> onSuccess,
            @NonNull final Action1<Throwable> onErrorAction
    ) {

        return executeOn(
                executeOn,
                single,
                onSuccess,
                onErrorAction,
                s -> s
        );
    }

    protected <RESULT> Subscription executeOn(
            @ExecuteOn final int executeOn,
            @NonNull final Single<RESULT> single,
            @NonNull final Action1<RESULT> onSuccess
    ) {

        return executeOn(
                executeOn,
                single,
                onSuccess,
                t -> {
                },
                s -> s
        );
    }

    private <RESULT> Subscription execute(
            @ExecuteOn final int executeOn,
            @NonNull final Observable<RESULT> observable,
            @NonNull final Action1<RESULT> onNextAction,
            @NonNull final Action1<Throwable> onErrorAction,
            @NonNull final Action0 onCompleteAction,
            @NonNull final Observable.Transformer<RESULT, RESULT> onSchedulersApplied
    ) {

        final boolean clearOnDestroy = isClearCompositeSubscriptionOnDestroy(executeOn);

        final Subscription subscription = observable
                .compose(applyObservableSchedulers(executeOn))
                .compose(onSchedulersApplied)
                .subscribe(
                        onNextAction,
                        throwable -> {
                            Timber.e(throwable);
                            onErrorAction.call(throwable);
                        },
                        onCompleteAction
                );

        addSubscription(subscription, clearOnDestroy);

        return subscription;
    }

    private Scheduler getSubscribeScheduler(@ExecuteOn final int executeOn) {
        final Scheduler scheduler;

        switch (executeOn) {
            case IO_DETACH:
            case IO_DETACH_DELAY_ERROR:
            case IO_DESTROY:
            case IO_DESTROY_DELAY_ERROR:
                scheduler = schedulersSet.getIoScheduler();
                break;
            case COMPUTATION_DETACH:
            case COMPUTATION_DETACH_DELAY_ERROR:
            case COMPUTATION_DESTROY:
            case COMPUTATION_DESTROY_DELAY_ERROR:
                scheduler = schedulersSet.getComputationScheduler();
                break;
            case IMMEDIATE_DETACH:
            case IMMEDIATE_DETACH_DELAY_ERROR:
            case IMMEDIATE_DESTROY:
            case IMMEDIATE_DESTROY_DELAY_ERROR:
            default:
                scheduler = schedulersSet.getImmediateScheduler();
                break;
        }
        return scheduler;
    }

    @SuppressLint("SwitchIntDef")
    private Scheduler getObserveScheduler(@ExecuteOn final int executeOn) {
        final Scheduler scheduler;

        switch (executeOn) {
            case IMMEDIATE_DETACH:
            case IMMEDIATE_DETACH_DELAY_ERROR:
            case IMMEDIATE_DESTROY:
            case IMMEDIATE_DESTROY_DELAY_ERROR:
                scheduler = schedulersSet.getImmediateScheduler();
                break;
            default:
                scheduler = schedulersSet.getUiScheduler();
                break;
        }
        return scheduler;
    }

    @SuppressLint("SwitchIntDef")
    private boolean isDelayError(@ExecuteOn final int executeOn) {
        final boolean delayError;

        switch (executeOn) {
            case IO_DETACH_DELAY_ERROR:
            case IO_DESTROY_DELAY_ERROR:
            case COMPUTATION_DETACH_DELAY_ERROR:
            case COMPUTATION_DESTROY_DELAY_ERROR:
            case IMMEDIATE_DETACH_DELAY_ERROR:
            case IMMEDIATE_DESTROY_DELAY_ERROR:
                delayError = true;
                break;
            default:
                delayError = false;
                break;
        }
        return delayError;
    }

    @SuppressLint("SwitchIntDef")
    private boolean isClearCompositeSubscriptionOnDestroy(@ExecuteOn final int executeOn) {
        final boolean clearOnDestroy;

        switch (executeOn) {
            case IO_DESTROY:
            case IO_DESTROY_DELAY_ERROR:
            case COMPUTATION_DESTROY:
            case COMPUTATION_DESTROY_DELAY_ERROR:
            case IMMEDIATE_DESTROY:
            case IMMEDIATE_DESTROY_DELAY_ERROR:
                clearOnDestroy = true;
                break;
            default:
                clearOnDestroy = false;
                break;
        }
        return clearOnDestroy;
    }

    private <T> Observable.Transformer<T, T> applyObservableSchedulers(@ExecuteOn final int executeOn) {
        return observable -> observable.subscribeOn(getSubscribeScheduler(executeOn))
                .observeOn(getObserveScheduler(executeOn), isDelayError(executeOn));
    }

    private <T> Single.Transformer<T, T> applySingleSchedulers(@ExecuteOn final int executeOn) {
        return single -> single.subscribeOn(getSubscribeScheduler(executeOn))
                .observeOn(getObserveScheduler(executeOn));
    }

    private Completable.Transformer applyCompletableSchedulers(@ExecuteOn final int executeOn) {
        return completable -> completable.subscribeOn(getSubscribeScheduler(executeOn))
                .observeOn(getObserveScheduler(executeOn));
    }
}
