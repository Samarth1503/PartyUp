package com.example.partyfinder.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.partyfinder.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(modifier:Modifier= Modifier){
        Surface(modifier=modifier
            .fillMaxSize(),
            color = colorResource(id = R.color.white)
        ) {
            Column(modifier=Modifier) {
                    Box(modifier=Modifier.fillMaxWidth()) {
                        Text(
                            text="Party Up!",
                            color = colorResource(id = R.color.primary),
                            style=MaterialTheme.typography.displayLarge,
                            modifier= Modifier
                                .width(180.dp)
                                .align(Alignment.CenterStart)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.register_drawable_icon),
                            contentDescription = null,
                            modifier= modifier
                                .height(200.dp)
                                .width(300.dp)
                                .align(Alignment.CenterEnd))
                    }
                

            }
        }
}

@Preview
@Composable
fun PreviewRegisterScreen(){
    PartyFinderTheme {
        RegisterScreen()
    }
}