package com.abinar.apps.android.driver.domain.usecase

import com.abinar.apps.android.driver.domain.models.FromToUser
import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.repository.MessageRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class DeleteLatestMessagesBothUsersUseCase(private val messageRepository: MessageRepository) {
    suspend fun execute(fromToUser: FromToUser): Flow<Response<Boolean>> {
        return messageRepository.deleteLatestMessageBothUsers(fromToUser = fromToUser)
    }
}