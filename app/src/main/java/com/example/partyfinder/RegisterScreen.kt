package com.example.partyfinder

import LoginViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.partyfinder.Navigation.PartyUpRouterSam
import com.example.partyfinder.Navigation.Screens
import com.example.partyfinder.ui.theme.PartyFinderTheme

@Composable
fun RegisterPage(loginViewModel : LoginViewModel = viewModel()) {
    Box(
        modifier = Modifier
            .height(808.dp)
            .width(393.dp)
            .background(color = colorResource(id = R.color.black)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 36.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

//            Image(painterResource(id = R.drawable.networkingone_blue))
            Text(
                text = "Create An Account",
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(id = R.color.primary)
            )

            Spacer(modifier = Modifier.height(32.dp))

            CustomOutlinedTextField(labelValue = "Email",
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") },
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.EmailChanged(it))
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            PasswordTextFieldComponent(labelValue = "Password",
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.PasswordChanged(it))
                }
            )

            Spacer(modifier = Modifier.height(4.dp))

            CustomCheckboxComponent(value = stringResource(id = R.string.terms_and_conditions),
                onTextSelected = {
                                 PartyUpRouterSam.navigateTo(Screens.TermsAndConditionsScreen)
                },
                onCheckedChange = {
                    loginViewModel.onEvent(UIEvent.PrivacyPolicyCheckBoxClicked(it))
                }
            )

            ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                PartyUpRouterSam.navigateTo(Screens.TermsAndConditionsScreen)
            })

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { loginViewModel.onEvent(UIEvent.RegisterButtonClicked) },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.DarkBG)),
                border = BorderStroke(1.dp, colorResource(id = R.color.CallWidgetBorder))
            ) {
                Text("Register", color = colorResource(id = R.color.primary))
            }
        }
        if(loginViewModel.registrationInProgress.value) {
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun PreviewRegisterPage(){
    PartyFinderTheme {
        RegisterPage()
    }
}