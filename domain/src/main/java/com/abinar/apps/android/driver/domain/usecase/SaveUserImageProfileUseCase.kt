package com.abinar.apps.android.driver.domain.usecase

import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.repository.UserImagesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class SaveUserImageProfileUseCase(private val userImagesRepository: UserImagesRepository) {
    suspend fun execute(newImageUriStr: String): Flow<Response<String>> {
        return userImagesRepository.saveImageProfile(newImageUriStr = newImageUriStr)
    }
}