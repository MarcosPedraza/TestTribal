package com.marcospb.testtribal.data.remote

import com.marcospb.testtribal.data.models.CategoryResponse
import com.marcospb.testtribal.data.models.DetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query




interface ChuckNorrisApi {


    @GET("/jokes/categories")
    suspend fun getCategoryList(): Response<List<String>>


    @GET("/jokes/random")
    suspend fun getDetail(@Query("category") category: String): Response<DetailResponse>


}