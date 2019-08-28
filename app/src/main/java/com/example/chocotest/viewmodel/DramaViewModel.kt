package com.example.chocotest.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.chocotest.db.entity.ChocoDataEntity
import com.example.chocotest.repository.ChocoDataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class DramaViewModel : BaseViewModel(), KoinComponent {

    private val repository: ChocoDataRepository by inject()
    val dramaList = MutableLiveData<List<ChocoDataEntity>>()
    val onClick = MutableLiveData<ChocoDataEntity>()
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun syncDramas () {
        compositeDisposable +=
            repository.syncDrama()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(15, TimeUnit.SECONDS)
                .subscribe({ Timber.d("success to sync message list ")}, { throwable ->
                    Timber.w(throwable, "fail to sync message list")})

    }

    @SuppressLint("CheckResult")
    fun getDramas () {
        repository.getDrama()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(15, TimeUnit.SECONDS)
            .doOnSubscribe { isLoading.value = true }
            .doOnError { Timber.d("bbb") }
            .subscribe ({
                isLoading.value = false
                dramaList.value = it
                Timber.d("${it.size}")
            }, { throwable ->
                Timber.w(throwable, "fail to get drama list")
                errorMessage.postValue(throwable.toString())
            })
    }
}