package com.abinar.apps.android.driver.domain.repository

import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.models.UserDetails
import com.abinar.apps.android.driver.domain.models.UserDetailsPublic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

/**
 * The repository provides work with the [UserDetails] class
 */
@ExperimentalCoroutinesApi
interface UserDetailsRepository {
    /**
     * The function returns a [Boolean] value of attempt to save user data
     * @param userDetails - user's personal data to be saved as [UserDetails] class
     */
    suspend fun save(userDetails: UserDetails): Flow<Response<Boolean>>

    /**
     * The function returns the data of the current authorized user in the form of the [UserDetails] class
     */
    suspend fun getCurrentUser(): Flow<Response<UserDetails>>

    /**
     * The function returns a [Boolean] value of an attempt to delete the user's personal data
     */
    suspend fun deleteCurrentUser(): Flow<Response<Boolean>>

    /**
     * Function returns [Boolean] value of attempt to change photoProfileUrl in saved [UserDetails]
     * @param newImageUriStr - link to new user photo
     */
    suspend fun changeImageProfileUri(newImageUriStr: String): Flow<Response<Boolean>>

    /**
     * Function returns [Boolean] value of attempt to change fullname in saved [UserDetails]
     * @param newFullname - new user fullname
     */
    suspend fun changeFullname(newFullname: String): Flow<Response<Boolean>>

    /**
     * Function returns [Boolean] value of attempt to change email address in saved [UserDetails]
     * @param newEmail - new user email
     */
    suspend fun changeEmailAddress(newEmail: String): Flow<Response<Boolean>>

    /**
     * Function returns [Boolean] value of attempt to change password in saved [UserDetails]
     * @param newPassword - new user password
     */
    suspend fun changePassword(newPassword: String): Flow<Response<Boolean>>

    /**
     * Get all users list
     * @return [ArrayList]-[UserDetailsPublic]
     */
    suspend fun getUsersList(): Flow<Response<ArrayList<UserDetailsPublic>>>

    /**
     * Getting details of a specific user by uid
     * @return [UserDetailsPublic] model
     */
    suspend fun getUserDetailsPublicOnUid(uid: String): Flow<Response<UserDetailsPublic>>
}