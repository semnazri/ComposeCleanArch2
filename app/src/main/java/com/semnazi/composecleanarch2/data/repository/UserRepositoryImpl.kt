package com.semnazi.composecleanarch2.data.repository

import com.semnazi.composecleanarch2.data.api.ApiService
import com.semnazi.composecleanarch2.domain.model.User
import com.semnazi.composecleanarch2.domain.repository.UserRepository

class UserRepositoryImpl(private val apiService: ApiService): UserRepository {
    override suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }
}