@file:Suppress("DEPRECATION")

package com.example.partyfinder.ui.theme

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.partyfinder.model.login.LoginUIEvent
import com.example.partyfinder.model.login.LoginViewModel
import com.example.partyfinder.navigation.PartyUpRouterSam
import com.example.partyfinder.navigation.Screens
import com.example.partyfinder.R
import com.example.partyfinder.data.UserAccount
import com.example.partyfinder.model.register.RegistrationUIState
import kotlinx.coroutines.flow.MutableStateFlow

@SuppressLint("SetTextI18n", "InflateParams")
@Composable
fun LogInPage(loginViewModel: LoginViewModel = viewModel()) {
    val context = LocalContext.current
    val userAccount = UserAccount()

    val _registrationUIState = RegistrationUIState()

    Box(
        modifier = Modifier
            .height(808.dp) 
            .width(393.dp)
            .background(color = colorResource(id = R.color.black))
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 36.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .padding(top = 60.dp, bottom = 80.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Party\nUp!",
                    style = MaterialTheme.typography.displayMedium,
                    color = colorResource(id = R.color.primary)
                )
                Image(
                    painter = painterResource(id = R.drawable.register_logo),
                    contentDescription = "Register Logo",
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .size(128.dp)
                )
            }

            Text(
                text = "Login",
                style = MaterialTheme.typography.titleSmall,
                color = colorResource(id = R.color.SubliminalText)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Welcome Back",
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(id = R.color.primary)
            )

            Spacer(modifier = Modifier.height(32.dp))

            CustomOutlinedTextField(
                labelValue = if (userAccount.email.isEmpty()){ "Enter Email" } else { userAccount.email },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") },
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            PasswordTextFieldComponent(labelValue = "Password",
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            ForgotPasswordComponent(value = "Forgot Password?",
                onTextSelected = {loginViewModel.onEvent(LoginUIEvent.ForgotPasswordClicked)})

            Spacer(modifier = Modifier.height(12.dp))

            ButtonComponent(value = "Login",
                onButtonClicked = { loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked) },
                isEnabled = true)

            DividerTextComponent()

            Spacer(modifier = Modifier.height(8.dp))

            ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                PartyUpRouterSam.navigateTo(Screens.RegisterScreen)
            })
        }
        if(loginViewModel.loginInProgress.value) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = colorResource(id = R.color.primary) )
        }

//        for prompting acc creation
        LaunchedEffect(key1 = loginViewModel.loginIsSuccessful.value) {
            if (loginViewModel.loginIsSuccessful.value) {
                Log.d(ContentValues.TAG,"Toast Prompted")

                // Create a LayoutInflater instance
                val inflater = LayoutInflater.from(context)

                // Inflate the custom layout
                val layout = inflater.inflate(R.layout.custom_toast, null)

                // Find the TextView in the custom layout and set the text
                val textView = layout.findViewById<TextView>(R.id.text)
                textView.text = "Signed In!"

                // Create a new Toast
                val toast = Toast(context)

                // Set the custom view, duration, and show the Toast
                toast.view = layout
                toast.duration = Toast.LENGTH_SHORT
                toast.show()
            }
        }
    }
}



@Preview
@Composable
fun Preview(){
    PartyFinderTheme {
        LogInPage()
    }
}