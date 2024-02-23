package com.example.partyfinder.ui.theme.LoginAndRegisterScreens


import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.partyfinder.R
import com.example.partyfinder.model.uiEvent.LoginUIEvent
import com.example.partyfinder.ui.theme.ButtonComponent
import com.example.partyfinder.ui.theme.ClickableLoginTextComponent
import com.example.partyfinder.ui.theme.CustomOutlinedTextField
import com.example.partyfinder.ui.theme.DividerTextComponent
import com.example.partyfinder.ui.theme.ForgotPasswordComponent
import com.example.partyfinder.ui.theme.PartyFinderTheme
import com.example.partyfinder.ui.theme.PasswordTextFieldComponent
import com.example.partyfinder.ui.theme.ViewModels.LoginViewModel
import com.example.partyfinder.ui.theme.ViewModels.RegistrationViewModel

@SuppressLint("SetTextI18n","InflateParams", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LogInPage( registrationViewModel : RegistrationViewModel,
    loginViewModel: LoginViewModel,
    navigateToRegisterScreen: () -> Unit,
    onLogInClicked: () -> Unit,
) {
    val emailState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }
    val snackbarHostState = remember { SnackbarHostState() }



    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
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
                    labelValue = "EnterEmail",
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = "EmailIcon") },
                    onTextSelected = { emailState.value = TextFieldValue(it) }
                )
                Spacer(modifier = Modifier.height(8.dp))

                PasswordTextFieldComponent(
                    labelValue = "Password",
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
                    onTextSelected = { passwordState.value = TextFieldValue(it) }
                )

                Spacer(modifier = Modifier.height(8.dp))

                ForgotPasswordComponent(value = "Forgot Password?",
                    onTextSelected = { loginViewModel.onEvent(LoginUIEvent.ForgotPasswordClicked) })

                Spacer(modifier = Modifier.height(12.dp))

                ButtonComponent(
                    value = "Login",
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(emailState.value.text))
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(passwordState.value.text))
                        onLogInClicked()
                    },
                    isEnabled = true
                )

                DividerTextComponent()

                Spacer(modifier = Modifier.height(8.dp))

                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                    navigateToRegisterScreen()
                })
            }
            if (loginViewModel.loginInProgress.value) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = colorResource(id = R.color.primary)
                )
            }

            // For prompting acc creation
            LaunchedEffect(key1 = loginViewModel.loginIsSuccessful.value) {
                if (loginViewModel.loginIsSuccessful.value) {
                    Log.d(ContentValues.TAG, "Snackbar Prompted")
                    snackbarHostState.showSnackbar("Signed In!")
                }
            }
        }
    }
}



@Preview
@Composable
fun Preview(){
    PartyFinderTheme {
        LogInPage(
            loginViewModel = viewModel(),
            navigateToRegisterScreen = {},
            onLogInClicked = {},
            registrationViewModel = viewModel()
        )
    }
}