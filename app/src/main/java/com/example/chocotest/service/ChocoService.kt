package com.example.chocotest.service

import com.example.chocotest.service.model.DramaModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface ChocoService {
    companion object {
        const val CHOCO_DATA_URL = "http://www.mocky.io/v2/5a97c59c30000047005c1ed2"
        const val CHOCO_BASE_URL = "http://www.mocky.io/v2/"
    }

    @GET
    fun getData(@Url url: String) : Single<DramaModel>
}