package com.abinar.apps.android.driver.data.storage.firebase

import com.abinar.apps.android.driver.data.storage.UserDetailsStorage
import com.abinar.apps.android.driver.domain.models.Response
import com.abinar.apps.android.driver.domain.models.UserDetails
import com.abinar.apps.android.driver.domain.models.UserDetailsPublic
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
class FirebaseUserDetailsStorage : UserDetailsStorage {
    companion object {
        private val fAuth = FirebaseAuth.getInstance()
        private val fDatabase = FirebaseDatabase.getInstance()
    }

    override suspend fun save(userDetails: UserDetails): Flow<Response<Boolean>> = callbackFlow {
        trySend(Response.Loading())

        val uidCurrentUser = fAuth.currentUser?.uid.toString()
        val refCurrentUser = fDatabase.getReference("/users/$uidCurrentUser")

        refCurrentUser.setValue(userDetails)
            .addOnSuccessListener {
                trySend(Response.Success(data = true))
            }
            .addOnFailureListener { e ->
                trySend(Response.Fail(e = e))
            }

        awaitClose { this.cancel() }
    }

    override suspend fun getCurrentUser(): Flow<Response<UserDetails>> = callbackFlow {
        trySend(Response.Loading())

        val uidCurrentUser = fAuth.currentUser?.uid.toString()
        val refCurrentUser = fDatabase.getReference("/users/$uidCurrentUser")

        refCurrentUser.get().addOnSuccessListener { result ->
            val user: UserDetails? = result.getValue(UserDetails::class.java)
            if (user != null) {
                trySend(Response.Success(data = user))
            } else {
                trySend(Response.Fail(e = Exception("user null")))
            }
        }.addOnFailureListener { e ->
            trySend(Response.Fail(e = e))
        }

        awaitClose { this.cancel() }
    }

    override suspend fun deleteCurrentUser(): Flow<Response<Boolean>> = callbackFlow {
        trySend(Response.Loading())

        val uid = fAuth.currentUser?.uid.toString()
        val refCurrentUser = fDatabase.getReference("/users/$uid")

        refCurrentUser.removeValue().addOnSuccessListener {
            trySend(Response.Success(data = true))
        }.addOnFailureListener { e ->
            trySend(Response.Fail(e = e))
        }

        awaitClose { this.cancel() }
    }

    override suspend fun changeFullname(newFullname: String): Flow<Response<Boolean>> =
        callbackFlow {
            trySend(Response.Loading())

            val uidCurrentUser = fAuth.currentUser?.uid.toString()
            val refCurrentUser = fDatabase.getReference("/users/$uidCurrentUser")

            refCurrentUser.child("fullname").setValue(newFullname)
                .addOnSuccessListener {
                    trySend(Response.Success(data = true))
                }.addOnFailureListener { e ->
                    trySend(Response.Fail(e = e))
                }

            awaitClose { this.cancel() }
        }

    override suspend fun changeImageProfileUri(newImageUriStr: String): Flow<Response<Boolean>> =
        callbackFlow {
            trySend(Response.Loading())

            val uidCurrentUser = fAuth.currentUser?.uid.toString()
            val refCurrentUser = fDatabase.getReference("/users/$uidCurrentUser")

            refCurrentUser.child("photoProfileUrl").setValue(newImageUriStr)
                .addOnSuccessListener {
                    trySend(Response.Success(data = true))
                }.addOnFailureListener { e ->
                    trySend(Response.Fail(e = e))
                }

            awaitClose { this.cancel() }
        }

    override suspend fun changeEmailAddress(newEmail: String): Flow<Response<Boolean>> =
        callbackFlow {
            trySend(Response.Loading())

            val uidCurrentUser = fAuth.currentUser?.uid.toString()
            val refCurrentUser = fDatabase.getReference("/users/$uidCurrentUser")

            refCurrentUser.child("email").setValue(newEmail)
                .addOnSuccessListener {
                    trySend(Response.Success(data = true))
                }.addOnFailureListener { e ->
                    trySend(Response.Fail(e = e))
                }

            awaitClose { this.cancel() }
        }

    override suspend fun changePassword(newPassword: String): Flow<Response<Boolean>> =
        callbackFlow {
            trySend(Response.Loading())

            val uidCurrentUser = fAuth.currentUser?.uid.toString()
            val refCurrentUser = fDatabase.getReference("/users/$uidCurrentUser")

            refCurrentUser.child("password").setValue(newPassword)
                .addOnSuccessListener {
                    trySend(Response.Success(data = true))
                }.addOnFailureListener { e ->
                    trySend(Response.Fail(e = e))
                }

            awaitClose { this.cancel() }
        }

    override suspend fun getUsersList(): Flow<Response<ArrayList<UserDetailsPublic>>> =
        callbackFlow {
            trySend(Response.Loading())

            val refUsers = fDatabase.getReference("/users/")

            refUsers.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val listUsers = ArrayList<UserDetailsPublic>()

                    snapshot.children.forEach {
                        val user = it.getValue(UserDetails::class.java)
                        if (user != null) {
                            val userPublic = userDetailsToUserDetailsPublicModel(userDetails = user)
                            listUsers.add(userPublic)
                        }
                    }

                    trySend(Response.Success(data = listUsers))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(Response.Fail(e = error.toException()))
                }
            })

            awaitClose { this.cancel() }
        }

    override suspend fun getUserDetailsPublicOnUid(uid: String): Flow<Response<UserDetailsPublic>> =
        callbackFlow {
            trySend(Response.Loading())

            val refUserUid = fDatabase.getReference("/users/$uid")

            refUserUid.get().addOnSuccessListener { result ->
                val user: UserDetails? = result.getValue(UserDetails::class.java)
                if (user != null) {
                    val userPublic = userDetailsToUserDetailsPublicModel(userDetails = user)
                    trySend(Response.Success(data = userPublic))
                } else {
                    trySend(Response.Fail(e = Exception("user null")))
                }
            }.addOnFailureListener { e ->
                trySend(Response.Fail(e = e))
            }

            awaitClose { this.cancel() }
        }

    private fun userDetailsToUserDetailsPublicModel(userDetails: UserDetails): UserDetailsPublic {
        return UserDetailsPublic(
            uid = userDetails.uid,
            fullname = userDetails.fullname,
            email = userDetails.email,
            photoProfileUrl = userDetails.photoProfileUrl
        )
    }
}