package com.marcospb.justocallenge.data.repository

import com.marcospb.justocallenge.data.model.UsersResponse
import com.marcospb.justocallenge.data.remote.UsersApiService
import retrofit2.Response
import javax.inject.Inject
import kotlin.contracts.Returns


class UsersRepository @Inject constructor(private val apiService: UsersApiService) {

    suspend fun getUserList(): Response<UsersResponse> {
        return apiService.getUserList()
    }


}