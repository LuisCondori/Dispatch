package com.abinar.apps.android.driver.presentation.latestMessages.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abinar.apps.android.driver.domain.models.Message
import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.models.UserDetails
import com.abinar.apps.android.driver.domain.models.UserDetailsPublic
import com.abinar.apps.android.driver.domain.usecase.GetCurrentUserDetailsUseCase
import com.abinar.apps.android.driver.domain.usecase.GetCurrentUserUidUseCase
import com.abinar.apps.android.driver.domain.usecase.GetLatestMessagesUseCase
import com.abinar.apps.android.driver.domain.usecase.GetUserDetailsPublicOnUidUseCase
import com.abinar.apps.android.driver.presentation.latestMessages.LatestMessagesContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class LatestMessagesViewModel @Inject constructor(
    private val getCurrentUserDetailsUseCase: GetCurrentUserDetailsUseCase,
    private val getLatestMessagesUseCase: GetLatestMessagesUseCase,
    private val getUserDetailsPublicOnUidUseCase: GetUserDetailsPublicOnUidUseCase,
    private val getCurrentUserUidUseCase: GetCurrentUserUidUseCase
) : ViewModel(), LatestMessagesContract.LatestMessagesViewModel {

    private val _userDetails = MutableLiveData(UserDetails())
    val userDetails: LiveData<UserDetails> = _userDetails

    private val _progressBarLoadUserDetails = MutableLiveData<Boolean>()
    val progressBarLoadUserDetails: LiveData<Boolean> = _progressBarLoadUserDetails

    private val _loadCurrentUserDetailsSuccess = MutableLiveData<Response<Boolean>>()
    val loadCurrentUserDetailsSuccess: LiveData<Response<Boolean>> = _loadCurrentUserDetailsSuccess

    private val _latestMessagesList = MutableLiveData<ArrayList<Message>>()
    val latestMessagesList: LiveData<ArrayList<Message>> = _latestMessagesList

    private val _progressBarLoadLatestMessagesList = MutableLiveData<Boolean>()
    val progressBarLoadLatestMessagesList: LiveData<Boolean> = _progressBarLoadLatestMessagesList

    private val _loadLatestMessagesList = MutableLiveData<Response<Boolean>>()
    val loadLatestMessagesList: LiveData<Response<Boolean>> = _loadLatestMessagesList

    private val _currentUserUid = MutableLiveData<String>()
    val currentUserUid: LiveData<String> = _currentUserUid

    override fun getCurrentUserDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrentUserDetailsUseCase.execute().collect { result ->
                when (result) {
                    is Response.Loading -> _progressBarLoadUserDetails.postValue(true)
                    is Response.Fail -> {
                        _progressBarLoadUserDetails.postValue(false)
                        _loadCurrentUserDetailsSuccess.postValue(Response.Fail(e = result.e))
                    }
                    is Response.Success -> {
                        _progressBarLoadUserDetails.postValue(false)
                        _loadCurrentUserDetailsSuccess.postValue(Response.Success(data = true))
                        this@LatestMessagesViewModel._userDetails.postValue(result.data)
                        getLatestMessages(currentUserUid = result.data.uid)
                    }
                }
            }
        }
    }

    override fun getLatestMessages(currentUserUid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getLatestMessagesUseCase.execute(fromUserUid = currentUserUid).collect { result ->
                when (result) {
                    is Response.Loading -> _progressBarLoadLatestMessagesList.postValue(true)
                    is Response.Fail -> {
                        _progressBarLoadLatestMessagesList.postValue(false)
                        _loadLatestMessagesList.postValue(Response.Fail(e = result.e))
                    }
                    is Response.Success -> {
                        _progressBarLoadLatestMessagesList.postValue(false)
                        _loadLatestMessagesList.postValue(Response.Success(data = true))
                        this@LatestMessagesViewModel._latestMessagesList.postValue(result.data)
                    }
                }
            }
        }
    }

    override fun getUserDetailsPublicOnUid(uid: String): Flow<UserDetailsPublic> = flow {
        getUserDetailsPublicOnUidUseCase.execute(uid = uid).collect { result ->
            when (result) {
                is Response.Success -> emit(result.data)
                else -> {}
            }
        }
    }

    override fun latestMessagesListClear() {
        _latestMessagesList.value?.clear()
    }
}