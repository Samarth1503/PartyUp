package com.example.partyfinder

import LoginViewModel
import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.partyfinder.ui.theme.PartyFinderTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import org.w3c.dom.Text


//public class TFClass : Activity() {
//
//    // [START declare_auth]
//    private lateinit var auth: FirebaseAuth
//    // [END declare_auth]
//
//    public override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // [START initialize_auth]
//        // Initialize Firebase Auth
//        auth = Firebase.auth
//        // [END initialize_auth]
//    }
//
//    // [START on_start_check_user]
//    public override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        if (currentUser != null) {
//            reload()
//        }
//    }
//    // [END on_start_check_user]
//
//    private fun createAccount(email: String, password: String) {
//        // [START create_user_with_email]
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "createUserWithEmail:success")
//                    val user = auth.currentUser
//                    updateUI(user)
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                    Toast.makeText(
//                        baseContext,
//                        "Authentication failed.",
//                        Toast.LENGTH_SHORT,
//                    ).show()
//                    updateUI(null)
//                }
//            }
//        // [END create_user_with_email]
//    }
//
//    private fun signIn(email: String, password: String) {
//        // [START sign_in_with_email]
//        auth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "signInWithEmail:success")
//                    val user = auth.currentUser
//                    updateUI(user)
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "signInWithEmail:failure", task.exception)
//                    Toast.makeText(
//                        baseContext,
//                        "Authentication failed.",
//                        Toast.LENGTH_SHORT,
//                    ).show()
//                    updateUI(null)
//                }
//            }
//        // [END sign_in_with_email]
//    }
//
//    private fun sendEmailVerification() {
//        // [START send_email_verification]
//        val user = auth.currentUser!!
//        user.sendEmailVerification()
//            .addOnCompleteListener(this) { task ->
//                // Email Verification sent
//            }
//        // [END send_email_verification]
//    }
//
//    private fun updateUI(user: FirebaseUser?) {
//    }
//
//    private fun reload() {
//    }
//
//    companion object {
//        private const val TAG = "EmailPassword"
//    }
//}

@Composable
fun TF(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(808.dp)
            .width(393.dp)
            .background(color = colorResource(id = R.color.black)),
        contentAlignment = Alignment.Center
    ) {
        RegisterPage()
    }
}


@Composable
fun RegisterPage(LoginViewModel : LoginViewModel = viewModel()) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(horizontal = 36.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Register",
            style = MaterialTheme.typography.headlineSmall,
            color = colorResource(id = R.color.primary)
        )

        Spacer(modifier = Modifier.height(44.dp))

        CustomOutlinedTextField( labelValue = "Email",
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "User Icon")},
            onTextSelected = {
                LoginViewModel.onEvent(UIEvent.EmailChanged(it))
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        PasswordTextFieldComponent( labelValue = "Password",
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password")},
            onTextSelected = {
                LoginViewModel.onEvent(UIEvent.PasswordChanged(it))
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { LoginViewModel.onEvent(UIEvent.RegisterButtonClicked) },
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.DarkBG)),
            border = BorderStroke(1.dp, colorResource(id = R.color.CallWidgetBorder))
        ) {
            Text("Register", color = colorResource(id = R.color.primary))
        }
    }
}

//fun createAccount(email: String, password: String) {
//    val auth = Firebase.auth
//    auth.createUserWithEmailAndPassword(email, password)
//        .addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                // Registration successful
//                val user = auth.currentUser
//                // You can perform additional actions after successful registration
//                // Handle the exception, log, or show an error message
//                Log.d(TAG,"Registered")
//            } else {
//                // Registration failed
//                val exception = task.exception
//                exception?.let {
//                    // Handle the exception, log, or show an error message
//                    Log.d(TAG,"Not registered")
//                }
//            }
//        }
//}


@Preview
@Composable
fun PreviewTF(){
    PartyFinderTheme {
        TF()
    }
}


