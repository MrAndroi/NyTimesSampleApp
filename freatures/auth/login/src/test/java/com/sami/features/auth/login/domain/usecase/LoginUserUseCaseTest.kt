package com.sami.features.auth.login.domain.usecase

import com.sami.core.datastore.models.AppDataModel
import com.sami.core.datastore.models.UserDataModel
import com.sami.core.datastore.usecase.GetAppDataUseCase
import com.sami.core.datastore.usecase.UpdateAppDataUseCase
import com.sami.features.auth.login.domain.model.LoginErrors
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LoginUserUseCaseTest {

    private lateinit var loginUserUseCase: LoginUserUseCase
    private lateinit var updateAppDataUseCase: UpdateAppDataUseCase
    private lateinit var getAppDataUseCase: GetAppDataUseCase

    @Before
    fun setup() {
        updateAppDataUseCase = mock()
        getAppDataUseCase = mock()
        loginUserUseCase = LoginUserUseCase(updateAppDataUseCase, getAppDataUseCase)
    }

    @Test
    fun `Given valid email and password, When invoke LoginUserUseCase, Then it returns NO_ERROR`() =
        runBlocking {
            // Arrange
            val email = "test@example.com"
            val password = "password"
            val user = UserDataModel(email, password)
            val appData = AppDataModel(users = setOf(user))
            val expectedErrors = LoginErrors.NO_ERROR

            whenever(getAppDataUseCase()).thenReturn(appData)
            loginUserUseCase = LoginUserUseCase(updateAppDataUseCase, getAppDataUseCase)

            // Act
            val actualErrors = loginUserUseCase(email, password)

            // Assert
            assertEquals(expectedErrors, actualErrors)
        }

    @Test
    fun `Given invalid email, When invoke LoginUserUseCase, Then it returns EMAIL_NOT_REGISTERED`() =
        runBlocking {
            // Arrange
            val email = "unknown@example.com"
            val password = "password"
            val appData = AppDataModel(users = emptySet())
            val expectedErrors = LoginErrors.EMAIL_NOT_REGISTERED

            whenever(getAppDataUseCase()).thenReturn(appData)
            loginUserUseCase = LoginUserUseCase(updateAppDataUseCase, getAppDataUseCase)

            // Act
            val actualErrors = loginUserUseCase(email, password)

            // Assert
            assertEquals(expectedErrors, actualErrors)
        }

    @Test
    fun `Given valid email and invalid password, When invoke LoginUserUseCase, Then it returns INVALID_PASSWORD`() =
        runBlocking {
            // Arrange
            val email = "test@example.com"
            val password = "password"
            val user = UserDataModel(email, password)
            val appData = AppDataModel(users = setOf(user))
            val expectedErrors = LoginErrors.INVALID_PASSWORD

            whenever(getAppDataUseCase()).thenReturn(appData)
            loginUserUseCase = LoginUserUseCase(updateAppDataUseCase, getAppDataUseCase)

            // Act
            val actualErrors = loginUserUseCase(email, password+"1")

            // Assert
            assertEquals(expectedErrors, actualErrors)
        }
}
