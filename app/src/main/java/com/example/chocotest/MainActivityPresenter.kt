package com.example.chocotest

import com.example.chocotest.repository.ChocoDataRepository
import com.example.chocotest.state.MainActivityState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivityPresenter(val view: MainActivity) : KoinComponent {

    private val repository: ChocoDataRepository by inject()

    protected val compositeDisposable = CompositeDisposable()


    fun create() {
        compositeDisposable +=
            repository.syncDrama()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(15, TimeUnit.SECONDS)
                .subscribe({
                    Timber.d("success to sync drama list ")
                    view.render(MainActivityState.ShowDramaList(it))
                }, { throwable ->
                    Timber.w(throwable, "fail to sync drama list")})
    }

    fun destroy() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}