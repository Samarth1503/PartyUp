package com.example.partyfinder.ui.theme.GamersCallScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
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
import com.example.partyfinder.ui.theme.PartyFinderTheme

@Preview
@Composable
fun PreviewCreateGamerCallScreen(){
    var GameName by remember { mutableStateOf("") }
    var NoOfGamers by remember { mutableStateOf("") }
    var CallDescription by remember { mutableStateOf("") }
    var CallDuration by remember { mutableStateOf("") }
    PartyFinderTheme {
        CreateGamerCallScreen(
            createGamerCallContent = { CreateGamerCallContent(
                GameName = GameName,
                NoOfGamers =NoOfGamers ,
                CallDescription = CallDescription,
                CallDuration = CallDuration,
                onGameNameValueChange = {it -> GameName=it},
                onNoOfGamersValueChange ={it -> NoOfGamers=it} ,
                onCallDescriptionValueChange = {it -> CallDescription=it},
                onCallDurationValueChange ={it -> CallDuration=it},
                onPostButtonClick = {}
            ) },
            createGamerCallScreenTopBar = { CreateGamerCallScreenTopBar(onCloseButtonClick = { /*TODO*/ })}
        )

    }
}




@Composable
fun CreateGamerCallScreen(
    modifier: Modifier=Modifier,
    createGamerCallContent:@Composable ()->Unit,
    createGamerCallScreenTopBar:@Composable ()->Unit,){
    Surface(
        modifier=modifier.fillMaxSize(),
        color = colorResource(id = R.color.black)) {
        Column(modifier=Modifier) {
            createGamerCallScreenTopBar()
            Divider(modifier = Modifier
                .height(1.dp)
                .background(color = colorResource(id = R.color.primary))
            )
            createGamerCallContent()
        }
    }
}

@Composable
fun CreateGamerCallScreenTopBar(
    onCloseButtonClick:()->Unit,
    modifier:Modifier=Modifier
        .height(dimensionResource(id = R.dimen.top_bar_height))){
    Box (modifier= modifier
        .background(color = colorResource(id = R.color.black))
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.main_padding),
            end = dimensionResource(id = R.dimen.main_padding)
        ),
    ){
        Text(
            text = "New Gamer Call",
            color= colorResource(id = R.color.primary),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Center)
        )
        Image(
            painter = painterResource(id = R.drawable.remove_icon),
            contentDescription = null,
            modifier= Modifier
                .height(dimensionResource(id = R.dimen.top_bar_back_icon_size))
                .width(dimensionResource(id = R.dimen.top_bar_back_icon_size))
                .clickable { onCloseButtonClick() }
                .align(Alignment.CenterEnd)
            )
    }
}


@Composable
fun CreateGamerCallContent(
    modifier:Modifier=Modifier,
    GameName:String,
    NoOfGamers:String,
    CallDescription:String,
    CallDuration:String,
    onGameNameValueChange:(String)->Unit,
    onNoOfGamersValueChange:(String)->Unit,
    onCallDescriptionValueChange:(String)->Unit,
    onCallDurationValueChange:(String)->Unit,
    onPostButtonClick:()->Unit,
){

    val mainPadding = dimensionResource(id = R.dimen.main_padding)
    // Add this line to store the selected duration
    val selectedDuration = remember { mutableStateOf(CallDuration) }

    Column(modifier = modifier.padding(mainPadding)) {
        Row(modifier= Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp, top = 32.dp), horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Enter Details",
                color = colorResource(id = R.color.primary),
                style = MaterialTheme.typography.titleLarge)
        }
        CreateCallTextField(value = GameName, onValueChanged = onGameNameValueChange, label ="Game Name" , isLastField =false )
        CreateCallTextField(value = NoOfGamers , onValueChanged = onNoOfGamersValueChange, label ="No of Gamers" , isLastField =false )
        CreateCallTextField(value = CallDescription, onValueChanged = onCallDescriptionValueChange, label ="Call of Description" , isLastField =false )

        // Replace TextField with RadioButton group
        Column {
            Text(
                text = "Select Call Duration",
                color = colorResource(id = R.color.primary),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(
                    16.dp, mainPadding, 0.dp, mainPadding/2
                )
            )
            Row {
                listOf("1hr", "2hrs", "3hrs").forEach { duration ->
                    Row(Modifier
                        .padding(end = 8.dp)
                        .selectable(
                            selected = (duration == selectedDuration.value),
                            onClick = {
                                selectedDuration.value = duration
                                onCallDurationValueChange(duration.filter { it.isDigit() }) // Send only the numeric part
                            }
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (duration == selectedDuration.value),
                            onClick = {
                                selectedDuration.value = duration
                                onCallDurationValueChange(duration.filter { it.isDigit() }) // Send only the numeric part
                            }
                        )
                        Text(
                            text = duration,
                            color = colorResource(id = R.color.primary),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }
            }
        }

        Row(modifier= Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = onPostButtonClick,
                shape = RoundedCornerShape(20.dp),
                modifier= Modifier
                    .height(dimensionResource(id = R.dimen.generic_button_height))
                    .width(124.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.on_tertiary))
            ) {
                Text(text = "Post", style = MaterialTheme.typography.titleSmall)
            }
        }
    }
}


@Composable
fun CreateCallTextField(value:String,onValueChanged:(String)->Unit,label:String,isLastField:Boolean,modifier:Modifier=Modifier){

        OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label, style = MaterialTheme.typography.bodyMedium)},
        modifier= Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        keyboardOptions = if(isLastField) {
            KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        } else {
            KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedLabelColor = colorResource(id = R.color.primary),
            focusedTextColor = colorResource(id = R.color.primary),
            unfocusedTextColor = colorResource(id = R.color.primary),
            focusedLabelColor = colorResource(id = R.color.primary),
            focusedBorderColor = colorResource(id = R.color.primary)
        )
    )

}