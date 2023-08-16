package com.abinar.apps.android.driver.domain.usecase

import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.repository.UserAuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class GetCurrentUserUidUseCase(private val userAuthRepository: UserAuthRepository) {
    suspend fun execute(): Flow<Response<String>> {
        return userAuthRepository.getCurrentUserUid()
    }
}