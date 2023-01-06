package com.marcospb.justocallenge.domain.use_cases

import com.marcospb.justocallenge.data.model.UsersResponse
import retrofit2.Response


interface UserUseCases {
    suspend fun getUsersList(): Response<UsersResponse>
}