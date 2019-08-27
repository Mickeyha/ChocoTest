package com.example.chocotest.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ChocoDataEntity (
    @SerializedName("drama_id")
    @field:[PrimaryKey(autoGenerate = false) ColumnInfo(name = "drama_id")]
    var id: Int = 0,

    @SerializedName("name")
    @field:[ColumnInfo(name = "name")]
    var name: String = "",

    @SerializedName("total_views")
    @field:[ColumnInfo(name = "total_views")]
    var totalViews: Long = 0,

    @SerializedName("created_at")
    @field:[ColumnInfo(name = "created_at")]
    var createdAt: String = "",

    @SerializedName("thumb")
    @field:[ColumnInfo(name = "thumb")]
    var thumb: String = "",

    @SerializedName("rating")
    @field:[ColumnInfo(name = "rating")]
    var rating: Float = 0f
)