package com.marcospb.justocallenge.data.remote

import com.marcospb.justocallenge.data.model.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val USER_NUM = 15

interface UsersApiService {


    @GET("api/")
    suspend fun getUserList(@Query("results") userNum: Int = USER_NUM): Response<UsersResponse>


}