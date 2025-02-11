package com.abinar.apps.android.driver.domain.repository

import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.models.UserAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

/**
 * The repository provides work with the user of the [UserAuth] class
 */
@ExperimentalCoroutinesApi
interface UserAuthRepository {
    /**
     * The function returns a [Boolean] value of the user's authorization attempt
     * @param userAuth - accepts email and password as by [UserAuth] class for authorization
     */
    suspend fun login(userAuth: UserAuth): Flow<Response<Boolean>>

    /**
     * Function returns [Boolean] value of new user registration attempt
     * @param userAuth - accepts login and password by [UserAuth] class for registration
     */
    suspend fun register(userAuth: UserAuth): Flow<Response<Boolean>>

    /**
     * The function returns a [Boolean] value whether the user is logged in
     */
    suspend fun checkSignedIn(): Flow<Response<Boolean>>

    /**
     * The function returns the [String] uid of the current user
     */
    suspend fun getCurrentUserUid(): Flow<Response<String>>

    /**
     * The function returns the [Boolean] value of deleting the current user
     */
    suspend fun deleteCurrentUser(): Flow<Response<Boolean>>

    /**
     * The function returns the [Boolean] value of an attempt to recover the password from the email address
     * note: the user's email will receive instructions for resetting the password
     * @param email - user's email as a [String]
     */
    suspend fun restorePasswordByEmail(email: String): Flow<Response<Boolean>>

    /**
     * The function returns a [Boolean] value of an attempt to change the user's email
     * To change your email address, you need to authenticate the user
     * @param userAuth - login / password user value as [UserAuth]
     * @param newEmail - new email address
     */
    suspend fun changeEmail(userAuth: UserAuth, newEmail: String): Flow<Response<Boolean>>

    /**
     * The function returns a [Boolean] value of an attempt to change the user's password
     * To change your password, you need to authenticate the user
     * @param userAuth - login / password user value as [UserAuth]
     * @param newPassword - new password
     */
    suspend fun changePassword(userAuth: UserAuth, newPassword: String): Flow<Response<Boolean>>

    /**
     * The function returns a [Boolean] value of the user's logout attempt
     */
    suspend fun signOut(): Flow<Response<Boolean>>
}