package com.abinar.apps.android.driver.domain.usecase

import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.repository.UserImagesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class DeleteUserImageProfileUseCase(private val userImagesRepository: UserImagesRepository) {
    suspend fun execute(): Flow<Response<Boolean>> {
        return userImagesRepository.deleteImageProfile()
    }
}