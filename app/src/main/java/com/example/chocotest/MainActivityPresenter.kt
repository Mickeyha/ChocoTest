package com.example.chocotest

import android.annotation.SuppressLint
import com.example.chocotest.repository.ChocoDataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainActivityPresenter : KoinComponent {

    private val repository: ChocoDataRepository by inject()

    private val compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    fun create() {
        Timber.d("create()")
        compositeDisposable.add(repository.syncDrama()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(15, TimeUnit.SECONDS)
            .subscribe({ Timber.d("success to sync message list ")}, { throwable ->
                Timber.w(throwable, "fail to sync message list")})
        )


        compositeDisposable.add(repository.getDrama()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { Timber.d("aaa") }
            .doOnError { Timber.d("bbb") }
            .subscribe ({
                Timber.d("${it.size}")
            },{throwable ->
                Timber.w(throwable, "fail to get message list")

            }))
    }

    fun destroy() {
        Timber.d("destroy()")
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

}