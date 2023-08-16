package com.abinar.apps.android.driver.data.repository

import com.abinar.apps.android.driver.data.storage.MessageStorage
import com.abinar.apps.android.driver.domain.models.FromToUser
import com.abinar.apps.android.driver.domain.models.Message
import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.repository.MessageRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class MessageRepositoryImpl(private val messageStorage: MessageStorage) : MessageRepository {
    override suspend fun save(message: Message): Flow<Response<Boolean>> {
        return messageStorage.save(message = message)
    }

    override suspend fun saveLatestMessage(message: Message): Flow<Response<Boolean>> {
        return messageStorage.saveLatestMessage(message = message)
    }

    override suspend fun listenFromToUserMessages(fromToUser: FromToUser): Flow<Response<Message>> {
        return messageStorage.listenFromToUserMessages(fromToUser = fromToUser)
    }

    override suspend fun deleteDialogBothUsers(fromToUser: FromToUser): Flow<Response<Boolean>> {
        return messageStorage.deleteDialogBothUsers(fromToUser = fromToUser)
    }

    override suspend fun deleteLatestMessageBothUsers(fromToUser: FromToUser): Flow<Response<Boolean>> {
        return messageStorage.deleteLatestMessageBothUsers(fromToUser = fromToUser)
    }

    override suspend fun getLatestMessages(fromUserUid: String): Flow<Response<ArrayList<Message>>> {
        return messageStorage.getLatestMessages(fromUserUid = fromUserUid)
    }
}