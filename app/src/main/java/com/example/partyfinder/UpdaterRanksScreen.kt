package com.example.partyfinder

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.partyfinder.ui.theme.PartyFinderTheme

@Composable
fun UpdateRanksScreen(modifier:Modifier=Modifier){
Surface(modifier=modifier.fillMaxSize(), color = colorResource(id = R.color.black)) {

    var gameName1 by remember{ mutableStateOf("") }
    var gameName2 by remember{ mutableStateOf("") }
    var gameName3 by remember{ mutableStateOf("") }
    var rank1 by remember { mutableStateOf("") }
    var rank2 by remember { mutableStateOf("") }
    var rank3 by remember { mutableStateOf("") }

    Column(modifier=Modifier.verticalScroll(rememberScrollState(),true)) {
        UpdateRanksScreenTopBar()
        UpdateRankWidget("Game 1 ",gameName1,{gameName1=it},rank1,{rank1=it})
        UpdateRankWidget("Game 2 ",gameName2,{gameName2=it},rank2,{rank2=it})
        UpdateRankWidget("Game 3 ",gameName3,{gameName3=it},rank3,{rank3=it})

        Row(modifier= Modifier
            .padding(dimensionResource(id = R.dimen.main_padding))
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center

        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier=Modifier.shadow(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.tertiary_40)),
            ) {
                Text(
                    text = "Update Ranks",
                    style = MaterialTheme.typography.titleSmall
                )
            }

        }
    }
}

}
@Preview
@Composable
fun PreviewUpdateRanksScreen(){

    PartyFinderTheme{
        UpdateRanksScreen()
    }
}


@Composable
fun UpdateRanksScreenTopBar(modifier:Modifier=Modifier){
    Row(modifier= modifier
        .height(dimensionResource(id = R.dimen.top_bar_height))
        .background(color = colorResource(id = R.color.DarkBG))
        .padding(start = dimensionResource(id = R.dimen.main_padding))
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.icons8_left_arrow_48),
            contentDescription ="Back arrow",
            modifier= Modifier
                .height(dimensionResource(id = R.dimen.top_bar_back_icon_size))
                .width(dimensionResource(id = R.dimen.top_bar_back_icon_size))
        )

        Spacer(modifier = Modifier.width(60.dp))
        Text(
            text = "Update Ranks",
            modifier = Modifier.align(alignment = Alignment.CenterVertically),
            color = colorResource(id = R.color.primary),
            style = MaterialTheme.typography.titleMedium,
        )




    }
}



@Composable
fun UpdateRankWidget(
    gameNo:String,
    gameName:String,
    onValueChanged:(String)->Unit,
    rank:String,
    onRankValueChanged:(String)->Unit,
    modifier: Modifier=Modifier){
    Card (
        modifier=modifier
            .padding(
                start=dimensionResource(id = R.dimen.main_padding),
                end=dimensionResource(id = R.dimen.main_padding),
                top = dimensionResource(id = R.dimen.main_padding),
                bottom = 10.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.DarkBG))

    ){
            Column(modifier= Modifier
                .padding(dimensionResource(id = R.dimen.main_padding))
                .fillMaxWidth()
            ) {
                Text(
                    text = gameNo,
                    color = colorResource(id = R.color.primary),
                    style = MaterialTheme.typography.titleSmall,
                    modifier=modifier.padding(bottom = 16.dp))

                OutlinedTextField(
                    value = gameName,
                    onValueChange =onValueChanged,
                    label = { Text(text = "Game Name", style = MaterialTheme.typography.bodyMedium)},
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    colors=OutlinedTextFieldDefaults.colors(
                        unfocusedLabelColor = colorResource(id = R.color.primary),
                        focusedTextColor = colorResource(id = R.color.primary),
                        unfocusedTextColor = colorResource(id = R.color.primary),
                        focusedLabelColor = colorResource(id = R.color.primary),
                        focusedBorderColor = colorResource(id = R.color.primary)
                    )
                )
                OutlinedTextField(
                    value = rank,
                    onValueChange =onRankValueChanged,
                    label = { Text(text = "Rank", style = MaterialTheme.typography.bodyMedium)},
                    modifier=Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    colors=OutlinedTextFieldDefaults.colors(
                        unfocusedLabelColor = colorResource(id = R.color.primary),
                        focusedTextColor = colorResource(id = R.color.primary),
                        unfocusedTextColor = colorResource(id = R.color.primary),
                        focusedLabelColor = colorResource(id = R.color.primary),
                        focusedBorderColor = colorResource(id = R.color.primary)
                    )
                )
            }
    }
}