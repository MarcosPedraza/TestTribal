package com.marcospb.testtribal.data.repository

import com.marcospb.testtribal.data.models.CategoryResponse
import com.marcospb.testtribal.data.models.DetailResponse
import com.marcospb.testtribal.data.remote.ChuckNorrisApi
import retrofit2.Response
import javax.inject.Inject

class ChuckNorrisRepository @Inject constructor(private val api: ChuckNorrisApi) {


    suspend fun getCategoryList(): Response<List<String>> = api.getCategoryList()

    suspend fun getDetail(category: String): Response<DetailResponse> = api.getDetail(category)

}