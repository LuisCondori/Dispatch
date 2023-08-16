package com.abinar.apps.android.driver.domain.usecase

import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.models.UserDetails
import com.abinar.apps.android.driver.domain.repository.UserDetailsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class SaveUserDetailsUseCase(private val userDetailsRepository: UserDetailsRepository) {
    suspend fun execute(userDetails: UserDetails): Flow<Response<Boolean>> {
        return userDetailsRepository.save(userDetails = userDetails)
    }
}