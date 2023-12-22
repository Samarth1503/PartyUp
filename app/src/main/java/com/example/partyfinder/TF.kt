package com.example.partyfinder

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.partyfinder.ui.theme.JuraBold
import com.example.partyfinder.ui.theme.PartyFinderTheme


@Composable
fun TF() {
    Surface(modifier = Modifier) {
        DmTopBar()
        Column(modifier = Modifier
            .padding(top = dimensionResource(id = R.dimen.top_bar_height))
            .height(808.dp)
            .width(393.dp)
            .background(color = colorResource(id = R.color.black))
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item { SenderDM() }
                item { ReceiverDm() }
                item { SenderDM() }
                item { SenderDM() }
                item { SenderDM() }
                item { ReceiverDm() }
                item { SenderDM() }
                item { SenderDM() }
                item { ReceiverDm() }
                item { SenderDM() }
                item { SenderDM() }
                item { ReceiverDm() }
                item { SenderDM() }
                item { SenderDM() }
                item { ReceiverDm() }
                item { SenderDM() }
                item { SenderDM() }
                item { ReceiverDm() }
                item { SenderDM() }
                item { SenderDM() }
            }
            DmChatInput()
        }
    }
}






@Composable
fun Input(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(dimensionResource(id = (R.dimen.top_bar_height)))
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.DarkBG)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "TF",
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.white),
        )
        Image (
            painter = painterResource(id = R.drawable.send_4),
            contentDescription = "Send",
            modifier = Modifier
                .padding(20.dp, 0.dp, 10.dp, 0.dp)
                .size(40.dp),
            alignment = Alignment.CenterEnd
        )
    }
}



@Preview
@Composable
fun PreviewTF(){
    PartyFinderTheme {
        TF()
    }
}


