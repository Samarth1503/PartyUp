package com.example.partyfinder

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.widget.Toast
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import com.example.partyfinder.model.Register.RegistrationViewModel
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.partyfinder.model.Register.RegisterUIEvent
import com.example.partyfinder.Navigation.PartyUpRouterSam
import com.example.partyfinder.Navigation.Screens
import com.example.partyfinder.ui.theme.PartyFinderTheme


@SuppressLint("StateFlowValueCalledInComposition", "SetTextI18n", "InflateParams")
@Composable
fun RegisterPage(registrationViewModel : RegistrationViewModel = viewModel()) {
    val context = LocalContext.current

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
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .padding(top = 60.dp, bottom = 60.dp)
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
                    painter = painterResource(id = R.drawable.partyup_logo),
                    contentDescription = "Register Logo",
                    modifier = Modifier
                        .size(120.dp)
                )
            }

            Text(
                text = "Create An Account",
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(id = R.color.primary)
            )

            Spacer(modifier = Modifier.height(32.dp))

            CustomOutlinedTextField(labelValue = "Email",
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") },
                onTextSelected = {
                    registrationViewModel.onEvent(RegisterUIEvent.EmailChanged(it))
                }
            )

            Spacer(modifier = Modifier.height(4.dp))

            PasswordTextFieldComponent(labelValue = "Password",
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
                onTextSelected = {
                    registrationViewModel.onEvent(RegisterUIEvent.PasswordChanged(it))
                } )

            Spacer(modifier = Modifier.height(4.dp))

            PasswordTextFieldComponent(labelValue = "Confirm Password",
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Confirm Password") },
                onTextSelected = {
                    registrationViewModel.onEvent(RegisterUIEvent.ConfirmPasswordChanged(it))
                } )

            if (registrationViewModel.confirmPasswordValidationStarted.value){
                Text(
                    text = "Password Do Not Match!", modifier = Modifier
                        .padding(4.dp, 2.dp, 0.dp, 0.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.Red,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Start
                    )
                )
            }

            CustomCheckboxComponent(value = stringResource(id = R.string.terms_and_conditions),
                onTextSelected = {
                    PartyUpRouterSam.navigateTo(Screens.TermsAndConditionsScreen)
                },
                onCheckedChange = {
                    registrationViewModel.onEvent(RegisterUIEvent.PrivacyPolicyCheckBoxClicked(it))
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ButtonComponent(value = "Register",
                onButtonClicked = { registrationViewModel.onEvent(RegisterUIEvent.RegisterButtonClicked) },
                isEnabled = registrationViewModel.policyStatusChecked.value)

            DividerTextComponent()

            Spacer(modifier = Modifier.height(8.dp))

            ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                PartyUpRouterSam.navigateTo(Screens.LoginScreen)
            })
        }

//        showing in-progress circle
        if(registrationViewModel.registrationInProgress.value) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = colorResource(id = R.color.primary) )
        }
//        for prompting acc creation
        LaunchedEffect(key1 = registrationViewModel.registrationSuccessful.value) {
            if (registrationViewModel.registrationSuccessful.value) {
                Log.d(TAG,"Toast Prompted")

                // Create a LayoutInflater instance
                val inflater = LayoutInflater.from(context)

                // Inflate the custom layout
                val layout = inflater.inflate(R.layout.custom_toast, null)

                // Find the TextView in the custom layout and set the text
                val textView = layout.findViewById<TextView>(R.id.text)
                textView.text = "Account Created!"

                // Create a new Toast
                val toast = Toast(context)

                // Set the custom view, duration, and show the Toast
                toast.view = layout
                toast.duration = Toast.LENGTH_SHORT
                toast.show()
            }
        }

////        Working one without customization
//        LaunchedEffect(key1 = registrationViewModel.registrationSuccessful.value) {
//            if (registrationViewModel.registrationSuccessful.value) {
//                Log.d(TAG,"Toast Prompted")
//                Toast.makeText(context, "Account Created!", Toast.LENGTH_SHORT).show()
//            }
//        }
//        redirecting aft prompt
        if (registrationViewModel.registrationSuccessful.value) {
            PartyUpRouterSam.navigateTo(Screens.LoginScreen)
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


