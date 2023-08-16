package com.abinar.apps.android.driver.data.storage

import com.abinar.apps.android.driver.domain.models.Response
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
interface TranslateStorage {
    suspend fun downloadLangRussianEnglishPack(): Flow<Response<Boolean>>

    suspend fun translateRussianEnglishText(text: String): Flow<Response<String>>

    suspend fun translateEnglishRussianText(text: String): Flow<Response<String>>

    suspend fun languageIndentifier(text: String): Flow<Response<String>>
}