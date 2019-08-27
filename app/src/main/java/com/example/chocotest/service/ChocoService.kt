package com.example.chocotest.service

import com.example.chocotest.entity.ChocoDataEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ChocoService {
    companion object {
        const val CHOCO_BASE_URL = "http://www.mocky.io/v2/5a97c59c30000047005c1ed2"
    }

    @GET
    fun getData() : Single<ChocoDataEntity>
}