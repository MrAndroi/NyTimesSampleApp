package com.sami.features.auth.register.domain.usecase

import com.sami.core.datastore.models.UserDataModel
import com.sami.core.datastore.usecase.UpdateAppDataUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class RegisterUserUseCaseTest {

    private val checkIfUserExistUseCase: CheckIfUserExistUseCase = mock()
    private val updateAppDataUseCase: UpdateAppDataUseCase = mock()
    private val registerUserUseCase = RegisterUserUseCase(checkIfUserExistUseCase, updateAppDataUseCase)

    @Test
    fun `Given a non-existing user, When invoke RegisterUserUseCase, Then it returns true and updates data`() = runBlocking {
        // Arrange
        val newUser = UserDataModel("newuser@example.com", "password")

        whenever(checkIfUserExistUseCase(newUser.email)).thenReturn(false)
        whenever(updateAppDataUseCase(newUser)).thenReturn(Unit)

        // Act
        val result = registerUserUseCase(newUser)

        // Assert
        assertEquals(true, result)
    }

    @Test
    fun `Given an existing user, When invoke RegisterUserUseCase, Then it returns false and doesn't update data`() = runBlocking {
        // Arrange
        val existingUser = UserDataModel("existinguser@example.com", "password")

        whenever(checkIfUserExistUseCase(existingUser.email)).thenReturn(true)

        // Act
        val result = registerUserUseCase(existingUser)

        // Assert
        assertEquals(false, result)
    }
}
