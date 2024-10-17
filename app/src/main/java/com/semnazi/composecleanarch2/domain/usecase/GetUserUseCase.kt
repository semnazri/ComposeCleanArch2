package com.semnazi.composecleanarch2.domain.usecase

import com.semnazi.composecleanarch2.domain.model.User
import com.semnazi.composecleanarch2.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// Make the class 'open' to allow mocking it in tests
open class GetUserUseCase(private val userRepository: UserRepository) {
    operator fun invoke(): Flow<List<User>> = flow {
        emit(userRepository.getUsers())
    }
}