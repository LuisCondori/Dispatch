package com.abinar.apps.android.driver.data.repository

import com.abinar.apps.android.driver.data.storage.UserImagesStorage
import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.repository.UserImagesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class UserImagesRepositoryImpl(private val userImagesStorage: UserImagesStorage) :
    UserImagesRepository {
    override suspend fun saveImageProfile(newImageUriStr: String): Flow<Response<String>> {
        return userImagesStorage.saveImageProfile(newImageUriStr = newImageUriStr)
    }

    override suspend fun deleteImageProfile(): Flow<Response<Boolean>> {
        return userImagesStorage.deleteImageProfile()
    }
}