package com.abinar.apps.android.driver.domain.usecase

import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.repository.UserAuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class RestoreUserByEmailUseCase(private val userAuthRepository: UserAuthRepository) {
    suspend fun execute(email: String): Flow<Response<Boolean>> {
        return userAuthRepository.restorePasswordByEmail(email = email)
    }
}