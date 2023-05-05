package com.kks.readerapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.firebase.firestore.FirebaseFirestore
import com.kks.readerapp.ui.theme.ReaderAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReaderApp()

        }
    }
}

@Composable
fun ReaderApp() {
    ReaderAppTheme {
        val db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        user["firstName"] = "Harry"
        user["lastName"] = "Kim"

        Surface(color = MaterialTheme.colors.background,
                modifier = Modifier.fillMaxSize()) {
                db.collection("users")
                    .add(user)
                    .addOnSuccessListener {
                        Log.d("[kks]", "FireStore Success: ${it.id}")
                    }.addOnFailureListener {
                        Log.d("[kks]", "FireStore Failure: ${it.message}")
                    }


               Column(verticalArrangement = Arrangement.Center,
                     horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "빌드 성공!")
               }
        }
    }
}