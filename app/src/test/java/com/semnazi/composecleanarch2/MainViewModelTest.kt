package com.semnazi.composecleanarch2

import com.semnazi.composecleanarch2.domain.usecase.GetUserUseCase
import com.semnazi.composecleanarch2.presentation.intent.MainIntent
import com.semnazi.composecleanarch2.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class MainViewModelTest {
    private lateinit var viewModel: MainViewModel
    private lateinit var getUserUseCase: GetUserUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        getUserUseCase = mock()
        viewModel = MainViewModel(getUserUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the Main dispatcher to its original state
    }

    @Test
    fun `when getUsers success then update state`() = runTest {
        // Mock the suspend function to return an empty list of users
        whenever(getUserUseCase.invoke()).thenReturn(flowOf(emptyList()))

        // Simulate the intent to load users
        viewModel.onIntent(MainIntent.LoadUsers)

        // Wait for coroutines to finish
        advanceUntilIdle()

        // Verify if the state was updated to an empty list of users
        assertTrue(viewModel.state.value.users.isEmpty())
    }
}