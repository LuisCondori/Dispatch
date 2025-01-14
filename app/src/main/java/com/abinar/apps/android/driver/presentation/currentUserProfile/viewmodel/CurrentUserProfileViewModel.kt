package com.abinar.apps.android.driver.presentation.currentUserProfile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.models.UserAuth
import com.abinar.apps.android.driver.domain.models.UserDetails
import com.abinar.apps.android.driver.domain.usecase.*
import com.abinar.apps.android.driver.presentation.currentUserProfile.CurrentUserProfileContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class CurrentUserProfileViewModel @Inject constructor(
    private val signOutUserAuthUseCase: SignOutUserAuthUseCase,
    private val getCurrentUserDetailsUseCase: GetCurrentUserDetailsUseCase,
    private val saveUserImageProfileUseCase: SaveUserImageProfileUseCase,
    private val changeUserDetailsPhotoProfileUrlUseCase: ChangeUserDetailsPhotoProfileUrlUseCase,
    private val changeUserAuthEmailUseCase: ChangeUserAuthEmailUseCase,
    private val changeUserAuthPasswordUseCase: ChangeUserAuthPasswordUseCase,
    private val changeUserDetailsEmailUseCase: ChangeUserDetailsEmailUseCase,
    private val changeUserDetailsPasswordUseCase: ChangeUserDetailsPasswordUseCase,
    private val changeUserDetailsFullnameUseCase: ChangeUserDetailsFullnameUseCase,
) : ViewModel(), com.abinar.apps.android.driver.presentation.currentUserProfile.CurrentUserProfileContract.CurrentUserProfileViewModel {

    private val _cropImageView = MutableLiveData("")
    val cropImageView: LiveData<String> = _cropImageView

    val _userDetails = MutableLiveData<UserDetails>()
    val userDetails: LiveData<UserDetails> = _userDetails

    override fun signOutUserAuth(): LiveData<Response<Boolean>> = liveData(Dispatchers.IO) {
        try {
            signOutUserAuthUseCase.execute().collect { emit(it) }
        } catch (e: Exception) {
            emit(Response.Fail(e = e))
        }
    }

    override fun getCurrentUserDetails(): LiveData<Response<UserDetails>> =
        liveData(Dispatchers.IO) {
            try {
                getCurrentUserDetailsUseCase.execute().collect { emit(it) }
            } catch (e: Exception) {
                emit(Response.Fail(e = e))
            }
        }

    override fun saveUserImageProfile(imageUriCache: String): LiveData<Response<String>> =
        liveData(Dispatchers.IO) {
            try {
                saveUserImageProfileUseCase.execute(newImageUriStr = imageUriCache)
                    .collect { emit(it) }
            } catch (e: Exception) {
                emit(Response.Fail(e))
            }
        }

    override fun changeUserDetailsPhotoProfileUrl(imageUriStr: String): LiveData<Response<Boolean>> =
        liveData(Dispatchers.IO) {
            try {
                changeUserDetailsPhotoProfileUrlUseCase.execute(newImageUriStr = imageUriStr)
                    .collect { emit(it) }
            } catch (e: Exception) {
                emit(Response.Fail(e))
            }
        }

    override fun changeUserAuthEmail(
        userAuth: UserAuth,
        newEmail: String
    ): LiveData<Response<Boolean>> =
        liveData(Dispatchers.IO) {
            try {
                changeUserAuthEmailUseCase.execute(userAuth = userAuth, newEmail = newEmail)
                    .collect { emit(it) }
            } catch (e: Exception) {
                emit(Response.Fail(e = e))
            }
        }

    override fun changeUserDetailsEmail(newEmail: String): LiveData<Response<Boolean>> =
        liveData(Dispatchers.IO) {
            try {
                changeUserDetailsEmailUseCase.execute(newEmail = newEmail).collect { emit(it) }
            } catch (e: Exception) {
                emit(Response.Fail(e = e))
            }
        }

    override fun changeUserAuthPassword(
        userAuth: UserAuth,
        newPassword: String
    ): LiveData<Response<Boolean>> =
        liveData(Dispatchers.IO) {
            try {
                changeUserAuthPasswordUseCase.execute(
                    userAuth = userAuth,
                    newPassword = newPassword
                )
                    .collect { emit(it) }
            } catch (e: Exception) {
                emit(Response.Fail(e = e))
            }
        }

    override fun changeUserDetailsPassword(newPassword: String): LiveData<Response<Boolean>> =
        liveData(Dispatchers.IO) {
            try {
                changeUserDetailsPasswordUseCase.execute(newPassword = newPassword)
                    .collect { emit(it) }
            } catch (e: Exception) {
                emit(Response.Fail(e = e))
            }
        }

    override fun changeUserDetailsFullname(newFullname: String): LiveData<Response<Boolean>> =
        liveData(Dispatchers.IO) {
            try {
                changeUserDetailsFullnameUseCase.execute(newFullname = newFullname)
                    .collect { emit(it) }
            } catch (e: Exception) {
                emit(Response.Fail(e = e))
            }
        }

    override fun saveUserImageLiveData(imageUriStr: String) {
        if (imageUriStr.isNotEmpty()) {
            _cropImageView.value = imageUriStr
        }
    }

    override fun deleteUserImageLiveData() {
        _cropImageView.value = ""
    }
}