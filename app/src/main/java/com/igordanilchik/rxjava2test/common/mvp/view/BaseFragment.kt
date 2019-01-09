package com.igordanilchik.rxjava2test.common.mvp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import butterknife.ButterKnife
import butterknife.Unbinder
import com.arellomobile.mvp.MvpAppCompatFragment
import com.igordanilchik.rxjava2test.app.DaggerApplication
import com.igordanilchik.rxjava2test.common.di.ApplicationComponent

/**
 * @author Igor Danilchik
 */
abstract class BaseFragment : MvpAppCompatFragment() {

    private var unbinder: Unbinder? = null

    abstract val layoutResID: Int

    @StringRes
    open val baseTitle: Int? = null

    fun appComponent(): ApplicationComponent = DaggerApplication[context].appComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutResID, container, false)
        unbinder = ButterKnife.bind(this, view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseTitle?.let { setTitle(getString(it)) }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        unbinder?.unbind()
    }

    protected fun setTitle(title: CharSequence) {
        activity?.apply {
            if (!isFinishing)
                setTitle(title)
        }
    }
}

