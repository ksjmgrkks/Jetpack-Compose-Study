package com.kks.readerapp.screens.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.kks.readerapp.model.MUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {
    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("signInWithEmailAndPassword", "task.isSuccessful: ${task.result}")
                            home()
                        } else {
//                            Log.d(
//                                "signInWithEmailAndPassword",
//                                "!task.isSuccessful: ${task.result.toString()}"
//                            )
                        }
                    }
            } catch (e: Exception) {
                Log.d("signInWithEmailAndPassword", "catch: ${e.message}")
            }

        }

    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        home: () -> Unit) {
        if (_loading.value == false) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val displayName = task.result?.user?.email?.split('@')?.get(0)
                        createUser(displayName)
                        home()
                    }else {
//                        Log.d("signInWithEmailAndPassword", "createUserWithEmailAndPassword: ${task.result}")
                    }
                    _loading.value = false
                }
        }
    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = MUser(userId = userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            quote = "",
            profession = "",
            id = null).toMap()

        FirebaseFirestore.getInstance().collection("users")
            .add(user)
    }
}