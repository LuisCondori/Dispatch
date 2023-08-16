package com.abinar.apps.android.driver.domain.usecase

import com.abinar.apps.android.driver.domain.models.Message
import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.repository.MessageRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class GetLatestMessagesUseCase(private val messageRepository: MessageRepository) {
    suspend fun execute(fromUserUid: String): Flow<Response<ArrayList<Message>>> {
        return messageRepository.getLatestMessages(fromUserUid = fromUserUid)
    }
}