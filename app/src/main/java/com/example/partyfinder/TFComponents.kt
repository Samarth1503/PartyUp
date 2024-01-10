package com.example.partyfinder

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.partyfinder.Data.Login.LoginUIEvent
import com.example.partyfinder.Data.Login.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(labelValue: String, leadingIcon: @Composable (() -> Unit)? = null,
                            onTextSelected: (String) -> Unit) {
    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        label = { Text(labelValue) },
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextSelected(it) },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = leadingIcon,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedLabelColor = colorResource(id = R.color.SubliminalText),
            unfocusedLabelColor = colorResource(id = R.color.SubliminalText),
            focusedTextColor = colorResource(id = R.color.primary),
            unfocusedTextColor = colorResource(id = R.color.primary),
            focusedBorderColor = colorResource(id = R.color.primary),
            unfocusedBorderColor = colorResource(id = R.color.primary),
            focusedLeadingIconColor = colorResource(id = R.color.primary)
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextFieldComponent(labelValue: String, leadingIcon: @Composable (() -> Unit)? = null,
                               onTextSelected: (String) -> Unit) {

    val localFocusManager = LocalFocusManager.current

    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        label = { Text(text = labelValue) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done ),
        singleLine = true,
        keyboardActions = KeyboardActions { localFocusManager.clearFocus() },
        maxLines = 1,
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it) },
        trailingIcon = {

            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            val description = if (passwordVisible.value) {
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }

        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None
        else PasswordVisualTransformation(),
        leadingIcon = leadingIcon,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedLabelColor = colorResource(id = R.color.SubliminalText),
            unfocusedLabelColor = colorResource(id = R.color.SubliminalText),
            focusedTextColor = colorResource(id = R.color.primary),
            unfocusedTextColor = colorResource(id = R.color.primary),
            focusedBorderColor = colorResource(id = R.color.primary),
            unfocusedBorderColor = colorResource(id = R.color.primary),
            focusedLeadingIconColor = colorResource(id = R.color.primary)
        )
    )
}

@Composable
fun CustomCheckboxComponent(value: String, onTextSelected: (String) -> Unit,
                            onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        val checkedState = remember {
            mutableStateOf(false)
        }

        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { isChecked ->
                checkedState.value = isChecked
                onCheckedChange.invoke(isChecked)
            },
            colors = colors(checkedColor = colorResource(id = R.color.primary), 
                checkmarkColor = colorResource(id = R.color.black))
        )


        ClickableTextComponent(value = value, onTextSelected)
    }
}

@Composable
fun ClickableLoginTextComponent(tryingToLogin: Boolean = true, onTextSelected: (String) -> Unit) {
    val initialText =
        if (tryingToLogin) "Already have an account? " else "Don’t have an account yet? "
    val loginText = if (tryingToLogin) "Login" else "Register"

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(colorResource(id = R.color.primary)
        )) {
            pushStringAnnotation(tag = initialText, annotation = initialText)
            append(initialText)
        }
        withStyle(style = SpanStyle(colorResource(id = R.color.white),
            textDecoration = TextDecoration.Underline
        )) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth(),
        style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
        text = annotatedString,
        onClick = { offset ->

            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")

                    if (span.item == loginText) {
                        onTextSelected(span.item)
                    }
                }
        },
    )
}

@Composable
fun ClickableTextComponent(value: String, onTextSelected: (String) -> Unit) {
    val initialText = "By continuing you accept our "
    val termsOfUseText = "Term of Use"
    val andText = " and "
    val privacyPolicyText = "Privacy Policy"

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(colorResource(id = R.color.primary)
        )) {
            pushStringAnnotation(tag = initialText, annotation = initialText)
            append(initialText)
        }
        withStyle(style = SpanStyle(colorResource(id = R.color.white),
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.Underline
        )) {
            pushStringAnnotation(tag = termsOfUseText, annotation = termsOfUseText)
            append(termsOfUseText)
        }
        withStyle(style = SpanStyle(colorResource(id = R.color.primary))) {
            pushStringAnnotation(tag = andText, annotation = andText)
            append(andText)
        }
        withStyle(style = SpanStyle(colorResource(id = R.color.white),
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.Underline
        )) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
    }

    ClickableText(text = annotatedString, onClick = { offset ->

        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                Log.d(value, "{${span.item}}")

                if ((span.item == termsOfUseText) || (span.item == privacyPolicyText)) {
                    onTextSelected(span.item)
                }
            }

    })
}

@Composable
fun ForgotPasswordComponent(value: String, onTextSelected: (Int) -> Unit) {

    val annotatedString = buildAnnotatedString {append(value)}

    ClickableText(text = annotatedString,
        onClick = onTextSelected,
        modifier = Modifier
            .padding(end = 4.dp)
            .fillMaxWidth(),
        style = MaterialTheme.typography.bodySmall.copy(
            color = colorResource(id = R.color.SubliminalText),
            textAlign = TextAlign.End)
    )
}

@Composable
fun ButtonComponent(value: String, onButtonClicked: () -> Unit, isEnabled: Boolean) {
    Button(
        modifier = Modifier
            .padding(16.dp, 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(0.dp)),
        onClick = {
            onButtonClicked.invoke()
        },
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.DarkBG),
            disabledContainerColor = colorResource(id = R.color.on_background)),
        border = BorderStroke(1.dp, colorResource(id = R.color.CallWidgetBorder)),
    ) {
        Text(
            text = value,
            color = colorResource(id = R.color.primary)
        )
    }
}

@Composable
fun DividerTextComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = colorResource(id = R.color.SubliminalText),
            thickness = 1.dp
        )

        Text(
            modifier = Modifier.padding(8.dp,4.dp,8.dp,8.dp),
            text = "or",
            style = MaterialTheme.typography.titleSmall,
            color = colorResource(id = R.color.white)
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = colorResource(id = R.color.SubliminalText),
            thickness = 1.dp
        )
    }
}
