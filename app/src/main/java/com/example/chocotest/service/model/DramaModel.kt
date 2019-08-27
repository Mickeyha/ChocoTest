package com.example.chocotest.service.model

import com.example.chocotest.db.entity.ChocoDataEntity
import com.google.gson.annotations.SerializedName

data class DramaModel (
    @SerializedName("data")
    var data: List<ChocoDataEntity>?
)