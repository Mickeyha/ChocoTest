package com.example.chocotest.repository

import com.example.chocotest.db.ChocoDatabase
import com.example.chocotest.db.entity.ChocoDataEntity
import com.example.chocotest.service.ChocoService
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class ChocoDataRepository : KoinComponent {

    private val service: ChocoService by inject()
    private val database: ChocoDatabase by inject()

    fun syncDrama(): Single<List<ChocoDataEntity>> =
        Single.zip(database.getChocoDataDao().getAll().firstOrError(),
            service.getData(ChocoService.CHOCO_DATA_URL).map { it.data },
            BiFunction<List<ChocoDataEntity>, List<ChocoDataEntity>, List<ChocoDataEntity>> { databaseList, serverList ->
                val missList = serverList.filterNot { serverData ->
                    databaseList.any { it.id == serverData.id }
                }
                if (!missList.isNullOrEmpty()) {
                    database.getChocoDataDao().insertAll(*missList.toTypedArray())
                }
                if (serverList.isNotEmpty()) serverList
                else databaseList
            })


    fun getDrama(): Single<List<ChocoDataEntity>> =
        database.getChocoDataDao().getAll()
            .firstOrError()
            .doOnSubscribe { Timber.v("start getDrama from db.") }
            .doOnSuccess { Timber.v("get dramas from db success.") }
            .doOnError { throwable ->
                Timber.e(throwable, "failed to load data from local. Start getting dramas from server.")
            }
            .onErrorReturnItem(ArrayList<ChocoDataEntity>().apply {
                add(ChocoDataEntity(id = 0, name = "", totalViews = 0, createdAt = "", thumb = "", rating = 0.0f))
            })


}