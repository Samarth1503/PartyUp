package com.example.partyfinder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.partyfinder.ui.theme.PartyFinderTheme

@Composable
fun FontTrials(modifier:Modifier=Modifier){
    Surface(modifier=modifier.fillMaxSize()) {
        Column (modifier=Modifier.padding(start = 100.dp)){

            Text(text = "Jura Fonts", fontSize = 35.sp,modifier=Modifier.padding(32.dp))

            Text(
                text = "Hello",
                style = MaterialTheme.typography.displayLarge
            )
            Text(
                text = "Hello",
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = "Hello",
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = "Hello",
                style = MaterialTheme.typography.headlineLarge)
            Text(
                text = "Hello",
                style = MaterialTheme.typography.headlineMedium)

            Text(
                text = "Hello",
                style = MaterialTheme.typography.headlineSmall)
            Text(
                text = "Hello",
                style = MaterialTheme.typography.titleLarge)
            Text(
                text = "Hello",
                style = MaterialTheme.typography.titleMedium)
            Text(
                text = "Hello",
                style = MaterialTheme.typography.titleSmall)


            Text(text = "Inter Fonts", fontSize = 35.sp,modifier=Modifier.padding(32.dp))



            Text(
                text = "Hello",
                style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "Hello",
                style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Hello",
                style = MaterialTheme.typography.bodySmall)

            Text(
                text = "Hello",
                style= MaterialTheme.typography.labelLarge)

            Text(
                text = "Hello",
                style= MaterialTheme.typography.labelMedium)
            Text(
                text = "Hello",
                style = MaterialTheme.typography.labelSmall)


        }

    }
}

@Preview
@Composable
fun FontTrialsPreview(){
    PartyFinderTheme {
        FontTrials()
    }
}