package com.semnazi.composecleanarch2.domain.repository

import com.semnazi.composecleanarch2.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}