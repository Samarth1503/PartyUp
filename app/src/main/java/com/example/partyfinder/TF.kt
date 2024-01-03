package com.example.partyfinder

import android.content.Context
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.partyfinder.ui.theme.PartyFinderTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


@Composable
fun TF(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .height(808.dp)
            .width(393.dp)
            .background(color = colorResource(id = R.color.black)),
        contentAlignment = Alignment.Center
    ) {
        RegisterPage(context)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(context: Context) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val auth = Firebase.auth

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

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = colorResource(id = R.color.SubliminalText),
                unfocusedLabelColor = colorResource(id = R.color.SubliminalText),
                focusedTextColor = colorResource(id = R.color.primary),
                unfocusedTextColor = colorResource(id = R.color.primary),
                focusedBorderColor = colorResource(id = R.color.primary),
                unfocusedBorderColor = colorResource(id = R.color.primary)
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = colorResource(id = R.color.SubliminalText),
                unfocusedLabelColor = colorResource(id = R.color.SubliminalText),
                focusedTextColor = colorResource(id = R.color.primary),
                unfocusedTextColor = colorResource(id = R.color.primary),
                focusedBorderColor = colorResource(id = R.color.primary),
                unfocusedBorderColor = colorResource(id = R.color.primary)
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                // Handle registration
                registerUser(auth, username, password, context)
            },
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.DarkBG)),
            border = BorderStroke(1.dp, colorResource(id = R.color.CallWidgetBorder))
        ) {
            Text("Register", color = colorResource(id = R.color.primary))
        }
    }
}

private fun registerUser(auth: FirebaseAuth, email: String, password: String, context: Context) {
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Registration successful
                val user = auth.currentUser
                // You can perform additional actions after successful registration
                Toast.makeText(context, "Registered", Toast.LENGTH_SHORT).show()
            } else {
                // Registration failed
                val exception = task.exception
                exception?.let {
                    // Handle the exception, log, or show an error message
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
}




@Preview
@Composable
fun PreviewTF(){
    PartyFinderTheme {
        TF()
    }
}


