package com.abinar.apps.android.driver.domain.usecase

import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.models.UserAuth
import com.abinar.apps.android.driver.domain.repository.UserAuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class SignUpUserAuthUseCase(private val userAuthRepository: UserAuthRepository) {
    suspend fun execute(userAuth: UserAuth): Flow<Response<Boolean>> {
        return userAuthRepository.register(userAuth = userAuth)
    }
}