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
        Single.zip(database.getChocoDataDao().getAll(),
            service.getData(ChocoService.CHOCO_DATA_URL).map { it.data },
            BiFunction<List<ChocoDataEntity>, List<ChocoDataEntity>, List<ChocoDataEntity>> { databaseList, serverList ->
                val missList = serverList.filterNot { serverData ->
                    databaseList.any { it.id == serverData.id }
                }
                if (!missList.isNullOrEmpty()) {
                    database.getChocoDataDao().insertAll(*missList.toTypedArray())
                }
                serverList
            })


    fun getDrama(): Single<List<ChocoDataEntity>> =
        Single.concat(
            database.getChocoDataDao().getAll().doOnSuccess { Timber.d("Load data from db success.") },
            syncDrama().doOnSuccess { Timber.d("Load data from remote success.") }
        )
            .filter { return@filter it.isNotEmpty() }
            .firstOrError()
}