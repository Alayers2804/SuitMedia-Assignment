package com.bangkit.suitmediatestapplication.data.api

import com.bangkit.suitmediatestapplication.data.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    fun getData(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
    ) : Call<ApiResponse>
}