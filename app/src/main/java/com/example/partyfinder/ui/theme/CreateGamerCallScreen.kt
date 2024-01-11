package com.example.partyfinder.ui.theme

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.partyfinder.R

@Composable
fun CreateGamerCallScreen(modifier: Modifier=Modifier){
        Surface(
            modifier=modifier.fillMaxSize(),
            color = colorResource(id = R.color.black)) {
            Column(modifier=Modifier) {
                        CreateGamerCallScreenTopBar()
                        CreateGamerCallContent()
            }
        }
}

@Preview
@Composable
fun PreviewCreateGamerCallScreen(){
    PartyFinderTheme {
        CreateGamerCallScreen()

    }
}



@Composable
fun CreateGamerCallScreenTopBar(modifier:Modifier=Modifier
    .height(dimensionResource(id = R.dimen.top_bar_height))){
    Row (modifier= modifier
        .background(color = colorResource(id = R.color.DarkBG))
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.main_padding),
            end = dimensionResource(id = R.dimen.main_padding)
        ),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "New Gamer Call",
            color= colorResource(id = R.color.primary),
            style = MaterialTheme.typography.titleMedium )

        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.remove_icon),
            contentDescription = null,
            modifier= Modifier
                .height(dimensionResource(id = R.dimen.top_bar_back_icon_size))
                .width(dimensionResource(id = R.dimen.top_bar_back_icon_size))
            )
    }
}


@Composable
fun CreateGamerCallContent(modifier:Modifier=Modifier){
    var GameName by remember {
        mutableStateOf("")
    }
    var NoOfGamers by remember {
        mutableStateOf("")
    }
    var CallDescription by remember {
        mutableStateOf("")
    }
    var CallDuration by remember {
        mutableStateOf("")
    }
    Column(modifier = modifier.padding(dimensionResource(id = R.dimen.main_padding))) {
            Row(modifier= Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp, top = 32.dp), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Enter Details",
                    color = colorResource(id = R.color.primary),
                    style = MaterialTheme.typography.titleLarge)
            }
        CreateCallTextField(value = GameName, onValueChanged = { GameName = it}, label ="Game Name" , isLastField =false )
        CreateCallTextField(value =NoOfGamers , onValueChanged = { NoOfGamers = it}, label ="No of Gamers" , isLastField =false )
        CreateCallTextField(value = CallDescription, onValueChanged = {CallDescription = it}, label ="Call of Description" , isLastField =false )
        CreateCallTextField(value = CallDuration, onValueChanged = { CallDuration = it}, label ="Call Duration" , isLastField =true)

        Row(modifier= Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(20.dp),
                modifier=Modifier.height(dimensionResource(id = R.dimen.generic_button_height)).width(124.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.on_tertiary))
            ) {
                    Text(text = "Post", style = MaterialTheme.typography.titleSmall)
            }
        }
    }
}

@Composable
fun CreateCallTextField(value:String,onValueChanged:(String)->Unit,label:String,isLastField:Boolean,modifier:Modifier=Modifier){

    if (isLastField){
        OutlinedTextField(
        value = value,
        onValueChange =onValueChanged,
        label = { Text(text = label, style = MaterialTheme.typography.bodyMedium)},
        modifier= Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        colors= OutlinedTextFieldDefaults.colors(
            unfocusedLabelColor = colorResource(id = R.color.primary),
            focusedTextColor = colorResource(id = R.color.primary),
            unfocusedTextColor = colorResource(id = R.color.primary),
            focusedLabelColor = colorResource(id = R.color.primary),
            focusedBorderColor = colorResource(id = R.color.primary)
        )
    )
    }
    else{
        OutlinedTextField(
            value = value,
            onValueChange =onValueChanged,
            label = { Text(text = label, style = MaterialTheme.typography.bodyMedium)},
            modifier= Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
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