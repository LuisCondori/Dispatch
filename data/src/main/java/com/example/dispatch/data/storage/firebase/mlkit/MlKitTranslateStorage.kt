package com.example.dispatch.data.storage.firebase.mlkit

import com.example.dispatch.data.storage.TranslateStorage
import com.example.dispatch.domain.models.Response
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
class MlKitTranslateStorage : TranslateStorage {
    override suspend fun downloadLangRussianEnglishPack(): Flow<Response<Boolean>> = callbackFlow {
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.RUSSIAN)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()
        val russianEnglishTranslator = Translation.getClient(options)

        russianEnglishTranslator.downloadModelIfNeeded()
            .addOnSuccessListener {
                trySend(Response.Success(data = true))
            }.addOnFailureListener { e ->
                trySend(Response.Fail(e = e))
            }

        awaitClose { this.cancel() }
    }
}