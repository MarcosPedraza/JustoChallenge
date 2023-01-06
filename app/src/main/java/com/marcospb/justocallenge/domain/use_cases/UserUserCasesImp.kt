package com.marcospb.justocallenge.domain.use_cases

import com.marcospb.justocallenge.data.model.UsersResponse
import com.marcospb.justocallenge.data.repository.UsersRepository
import retrofit2.Response
import javax.inject.Inject



class UserUserCasesImp @Inject constructor(private val repository: UsersRepository):UserUseCases {

    override suspend fun getUsersList(): Response<UsersResponse> {
        return repository.getUserList()
    }
}