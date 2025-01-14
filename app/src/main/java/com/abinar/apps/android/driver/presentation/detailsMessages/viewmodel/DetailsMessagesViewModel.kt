package com.abinar.apps.android.driver.presentation.detailsMessages.viewmodel

import androidx.lifecycle.*
import com.abinar.apps.android.driver.domain.models.FromToUser
import com.abinar.apps.android.driver.domain.models.Message
import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.models.UserDetailsPublic
import com.abinar.apps.android.driver.domain.usecase.*
import com.abinar.apps.android.driver.presentation.detailsMessages.DetailsMessagesContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class DetailsMessagesViewModel @Inject constructor(
    private val getUserDetailsPublicOnUidUseCase: GetUserDetailsPublicOnUidUseCase,
    private val translateRussianEnglishTextUseCase: TranslateRussianEnglishTextUseCase,
    private val translateEnglishRussianTextUseCase: TranslateEnglishRussianTextUseCase,
    private val languageIdentifierUseCase: LanguageIdentifierUseCase,
    private val getCurrentUserUidUseCase: GetCurrentUserUidUseCase,
    private val saveMessageUseCase: SaveMessageUseCase,
    private val listenFromToUserMessagesUseCase: ListenFromToUserMessagesUseCase,
    private val deleteDialogBothUsersUseCase: DeleteDialogBothUsersUseCase,
    private val saveLatestMessageUseCase: SaveLatestMessageUseCase,
    private val deleteLatestMessagesBothUsersUseCase: DeleteLatestMessagesBothUsersUseCase
) : ViewModel(), DetailsMessagesContract.DetailsMessagesViewModel {

    val _companionUid = MutableLiveData<String>()
    val companionUid: LiveData<String> = _companionUid

    val _companionDetails = MutableLiveData<UserDetailsPublic>()
    val companionDetails: LiveData<UserDetailsPublic> = _companionDetails

    private val _currUserUid = MutableLiveData<String>()
    val currUserUid: LiveData<String> = _currUserUid

    override fun getUserDetailsPublicOnUid(uid: String): LiveData<Response<UserDetailsPublic>> =
        liveData(Dispatchers.IO) {
            try {
                getUserDetailsPublicOnUidUseCase.execute(uid = uid).collect { emit(it) }
            } catch (e: Exception) {
                emit(Response.Fail(e = e))
            }
        }

    override fun translateRussianEnglishText(text: String): LiveData<Response<String>> =
        liveData(Dispatchers.IO) {
            try {
                translateRussianEnglishTextUseCase.execute(text = text).collect { emit(it) }
            } catch (e: Exception) {
                emit(Response.Fail(e = e))
            }
        }

    override fun translateEnglishRussianText(text: String): LiveData<Response<String>> =
        liveData(Dispatchers.IO) {
            try {
                translateEnglishRussianTextUseCase.execute(text = text).collect { emit(it) }
            } catch (e: Exception) {
                emit(Response.Fail(e = e))
            }
        }

    override fun languageIdentifier(text: String): LiveData<Response<String>> =
        liveData(Dispatchers.IO) {
            try {
                languageIdentifierUseCase.execute(text = text).collect { emit(it) }
            } catch (e: Exception) {
                emit(Response.Fail(e = e))
            }
        }

    override fun getCurrentUserUid() {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrentUserUidUseCase.execute().collect { result ->
                when (result) {
                    is Response.Loading -> {}
                    is Response.Fail -> {}
                    is Response.Success -> this@DetailsMessagesViewModel._currUserUid.postValue(
                        result.data
                    )
                }
            }
        }
    }

    override fun saveMessage(message: Message): LiveData<Response<Boolean>> =
        liveData(Dispatchers.IO) {
            try {
                saveMessageUseCase.execute(message = message).collect { emit(it) }
            } catch (e: Exception) {
                emit(Response.Fail(e = e))
            }
        }

    override fun saveLatestMessage(message: Message) {
        viewModelScope.launch(Dispatchers.IO) {
            saveLatestMessageUseCase.execute(message = message).collect {}
        }
    }

    override fun listenFromToUserMessages(fromToUser: FromToUser): LiveData<Response<Message>> =
        liveData(Dispatchers.IO) {
            try {
                listenFromToUserMessagesUseCase.execute(fromToUser = fromToUser)
                    .collect { emit(it) }
            } catch (e: Exception) {
                emit(Response.Fail(e = e))
            }
        }

    override fun deleteDialogBothUsers(fromToUser: FromToUser) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteDialogBothUsersUseCase.execute(fromToUser = fromToUser).collect { result ->
                when (result) {
                    is Response.Success -> deleteLatestMessageBothUsers(fromToUser = fromToUser)
                    else -> {}
                }
            }
        }
    }

    override fun deleteLatestMessageBothUsers(fromToUser: FromToUser) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteLatestMessagesBothUsersUseCase.execute(fromToUser = fromToUser).collect {}
        }
    }
}