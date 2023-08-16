package com.abinar.apps.android.driver.domain.usecase

import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.repository.UserDetailsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class DeleteCurrentUserDetailsUseCase(private val userDetailsRepository: UserDetailsRepository) {
    suspend fun execute(): Flow<Response<Boolean>> {
        return userDetailsRepository.deleteCurrentUser()
    }
}