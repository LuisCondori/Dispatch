package com.abinar.apps.android.driver.data.storage

import com.abinar.apps.android.driver.domain.models.Response
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
interface UserImagesStorage {
    suspend fun saveImageProfile(newImageUriStr: String): Flow<Response<String>>

    suspend fun deleteImageProfile(): Flow<Response<Boolean>>
}