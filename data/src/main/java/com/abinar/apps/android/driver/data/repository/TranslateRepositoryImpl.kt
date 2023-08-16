package com.abinar.apps.android.driver.data.repository

import com.abinar.apps.android.driver.data.storage.TranslateStorage
import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.repository.TranslateRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class TranslateRepositoryImpl(private val translateStorage: TranslateStorage) :
    TranslateRepository {
    override suspend fun downloadLangRussianEnglishPack(): Flow<Response<Boolean>> {
        return translateStorage.downloadLangRussianEnglishPack()
    }

    override suspend fun translateRussianEnglishText(text: String): Flow<Response<String>> {
        return translateStorage.translateRussianEnglishText(text = text)
    }

    override suspend fun translateEnglishRussianText(text: String): Flow<Response<String>> {
        return translateStorage.translateEnglishRussianText(text = text)
    }

    override suspend fun languageIdentifier(text: String): Flow<Response<String>> {
        return translateStorage.languageIndentifier(text = text)
    }
}