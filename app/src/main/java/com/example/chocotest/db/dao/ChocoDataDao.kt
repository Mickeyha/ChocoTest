package com.example.chocotest.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.chocotest.db.entity.ChocoDataEntity
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface ChocoDataDao {
    @Query("SELECT * FROM `ChocoDataEntity`")
    fun getAll(): Single<List<ChocoDataEntity>>

    @Query("SELECT * FROM `ChocoDataEntity` where name IN (:names)")
    fun get(names: List<String>): Single<List<ChocoDataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg chocoData: ChocoDataEntity)
}