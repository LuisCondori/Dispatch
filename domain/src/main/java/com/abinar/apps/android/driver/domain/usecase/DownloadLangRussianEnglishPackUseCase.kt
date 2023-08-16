package com.abinar.apps.android.driver.domain.usecase

import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.repository.TranslateRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class DownloadLangRussianEnglishPackUseCase(private val translateRepository: TranslateRepository) {
    suspend fun execute(): Flow<Response<Boolean>> {
        return translateRepository.downloadLangRussianEnglishPack()
    }
}