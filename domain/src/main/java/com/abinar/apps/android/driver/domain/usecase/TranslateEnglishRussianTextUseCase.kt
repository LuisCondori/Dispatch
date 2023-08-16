package com.abinar.apps.android.driver.domain.usecase

import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.repository.TranslateRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class TranslateEnglishRussianTextUseCase(private val translateRepository: TranslateRepository) {
    suspend fun execute(text: String): Flow<Response<String>> {
        return translateRepository.translateEnglishRussianText(text = text)
    }
}