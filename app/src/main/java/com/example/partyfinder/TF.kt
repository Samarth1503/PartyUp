package com.example.partyfinder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.partyfinder.ui.theme.PartyFinderTheme


//Merger Comment
@Composable
fun TF(
    modifier: Modifier = Modifier,
    text:String) {
    Box(
        modifier = modifier
            .height(808.dp)
            .width(393.dp),
        contentAlignment = Alignment.Center
    ) {
                Text(text = text)

    }
}

@Preview
@Composable
fun PreviewTF(){
    PartyFinderTheme {
    TF(text = "HEllo")
    }
}

