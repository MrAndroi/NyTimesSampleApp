package com.sami.features.auth.register.domain.usecase

import com.sami.core.datastore.models.AppDataModel
import com.sami.core.datastore.models.UserDataModel
import com.sami.core.datastore.usecase.GetAppDataUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class CheckIfUserExistUseCaseTest {

    private val getAppDataUseCase: GetAppDataUseCase = mock()
    private val checkIfUserExistUseCase = CheckIfUserExistUseCase(getAppDataUseCase)

    @Test
    fun `Given an existing email, When invoke CheckIfUserExistUseCase, Then it returns true`() =
        runBlocking {
            // Arrange
            val existingEmail = "test@example.com"
            val user1 = UserDataModel(existingEmail, "password")
            val appData = AppDataModel(users = setOf(user1))

            whenever(getAppDataUseCase()).thenReturn(appData)

            // Act
            val result = checkIfUserExistUseCase(existingEmail)

            // Assert
            assertEquals(true, result)
        }

    @Test
    fun `Given a non-existing email, When invoke CheckIfUserExistUseCase, Then it returns false`() =
        runBlocking {
            // Arrange
            val nonExistingEmail = "unknown@example.com"
            val user1 = UserDataModel("test@example.com", "password")
            val appData = AppDataModel(users = setOf(user1))

            whenever(getAppDataUseCase()).thenReturn(appData)

            // Act
            val result = checkIfUserExistUseCase(nonExistingEmail)

            // Assert
            assertEquals(false, result)
        }
}
