package com.example.chocotest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chocotest.db.dao.ChocoDataDao
import com.example.chocotest.db.entity.ChocoDataEntity

@Database(entities = [ChocoDataEntity::class], version = 1)
abstract class ChocoDatabase : RoomDatabase() {
    abstract fun getChocoDataDao(): ChocoDataDao
}