package com.abinar.apps.android.driver.domain.usecase

import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.repository.UserDetailsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class ChangeUserDetailsFullnameUseCase(private val userDetailsRepository: UserDetailsRepository) {
    suspend fun execute(newFullname: String): Flow<Response<Boolean>> {
        return userDetailsRepository.changeFullname(newFullname = newFullname)
    }
}