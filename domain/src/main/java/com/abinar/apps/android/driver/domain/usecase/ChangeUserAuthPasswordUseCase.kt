package com.abinar.apps.android.driver.domain.usecase

import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.models.UserAuth
import com.abinar.apps.android.driver.domain.repository.UserAuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class ChangeUserAuthPasswordUseCase(private val userAuthRepository: UserAuthRepository) {
    suspend fun execute(userAuth: UserAuth, newPassword: String): Flow<Response<Boolean>> {
        return userAuthRepository.changePassword(userAuth = userAuth, newPassword = newPassword)
    }
}