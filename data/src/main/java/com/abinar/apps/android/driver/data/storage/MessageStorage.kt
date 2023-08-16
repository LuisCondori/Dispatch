package com.abinar.apps.android.driver.data.storage

import com.abinar.apps.android.driver.domain.models.FromToUser
import com.abinar.apps.android.driver.domain.models.Message
import com.abinar.apps.android.driver.domain.models.Response
import kotlinx.coroutines.flow.Flow

interface MessageStorage {
    suspend fun save(message: Message): Flow<Response<Boolean>>

    suspend fun saveLatestMessage(message: Message): Flow<Response<Boolean>>

    suspend fun listenFromToUserMessages(fromToUser: FromToUser): Flow<Response<Message>>

    suspend fun deleteDialogBothUsers(fromToUser: FromToUser): Flow<Response<Boolean>>

    suspend fun deleteLatestMessageBothUsers(fromToUser: FromToUser): Flow<Response<Boolean>>

    suspend fun getLatestMessages(fromUserUid: String): Flow<Response<ArrayList<Message>>>
}