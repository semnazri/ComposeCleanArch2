package com.semnazi.composecleanarch2.data.api

import com.semnazi.composecleanarch2.domain.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}