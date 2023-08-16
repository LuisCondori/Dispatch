package com.abinar.apps.android.driver.domain.usecase

import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.models.UserDetailsPublic
import com.abinar.apps.android.driver.domain.repository.UserDetailsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class GetUserDetailsPublicOnUidUseCase(private val userDetailsRepository: UserDetailsRepository) {
    suspend fun execute(uid: String): Flow<Response<UserDetailsPublic>> {
        return userDetailsRepository.getUserDetailsPublicOnUid(uid = uid)
    }
}