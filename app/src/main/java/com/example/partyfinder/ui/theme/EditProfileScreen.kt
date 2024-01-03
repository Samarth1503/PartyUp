package com.example.partyfinder.ui.theme



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.partyfinder.R


@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview(){
    PartyFinderTheme {
     EditProfileScreen(
         navigateBack = {},
         gamerID = "Kaizoku",
         onGamerIDchanged = {},
         bio = "hello",
         onGamerBioChanged = {},
         onSaveChanges = {})
    }
}

@Composable
fun EditProfileScreen(
    modifier:Modifier=Modifier.fillMaxSize(),
    gamerID: String,
    onGamerIDchanged:(String)->Unit,
    bio:String,
    onGamerBioChanged:(String) ->Unit,
    onSaveChanges:()->Unit,
    navigateBack: () -> Unit
    ){



    Column(modifier = modifier
        .background(color = colorResource(id = R.color.black))
        .verticalScroll(
            rememberScrollState()
        )) {
        EditProfileScreenTopBar(navigateBack = navigateBack)
        EditProfileScreenBanner()
        EditProfileDataWidget(
            gamerID = gamerID,
            onValueChanged = onGamerIDchanged,
            bio = bio,
            onBioValueChanged = onGamerBioChanged,
            onSaveChanges=onSaveChanges)
    }

}

@Composable
fun EditProfileScreenTopBar(modifier:Modifier=Modifier,navigateBack:()->Unit){
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
                        .clickable { navigateBack() }
                )

            Spacer(modifier = Modifier.width(112.dp))
                Text(
                    text = "Profile",
                    modifier = Modifier.align(alignment = Alignment.CenterVertically),
                    color = colorResource(id = R.color.primary),
                    style = MaterialTheme.typography.titleMedium,
                )




        }
}


@Composable
fun EditProfileScreenBanner(modifier:Modifier=Modifier){
    Box(modifier =modifier.height(dimensionResource(id = R.dimen.Profile_Banner_Box_Height))){
        Surface(modifier= Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.Profile_banner_height)),
            color = colorResource(id = R.color.on_tertiary)
        ) {

        }
        Surface(
            modifier= Modifier
                .padding(12.dp)
                .height(36.dp)
                .width(36.dp)
                .align(Alignment.TopEnd),
            shape = RoundedCornerShape(50),
            color = colorResource(id = R.color.primary)
        )
        {
            Image(
                painter = painterResource(id = R.drawable.pngtreeblack_edit_icon_4422168),
                contentDescription =null,
                modifier=Modifier.padding(bottom = 4.dp, start = 1.dp))
        }
        Box(modifier=Modifier.align(Alignment.BottomStart)) {
            Image(
                painter = painterResource(id = R.drawable.luffy),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.main_padding))
                    .height(dimensionResource(id = R.dimen.profile_picture_height))
                    .width(dimensionResource(id = R.dimen.profile_picture_height))
                    .clip(RoundedCornerShape(50))

            )
            Surface(
                modifier= Modifier
                    .height(36.dp)
                    .width(36.dp)
                    .align(Alignment.TopEnd),
                shape = RoundedCornerShape(50),
                color = colorResource(id = R.color.primary)
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.pngtreeblack_edit_icon_4422168),
                    contentDescription =null,
                    modifier=Modifier.padding(bottom = 4.dp, start = 1.dp))
            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileDataWidget(
    gamerID:String,
    onValueChanged:(String)-> Unit,
    bio:String,
    onBioValueChanged:(String)->Unit,
    onSaveChanges: () -> Unit,
    modifier:Modifier=Modifier.padding(dimensionResource(id = R.dimen.main_padding))){
    Card(modifier= modifier
        .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.neutral_10))
    ) {
           OutlinedTextField(
               value = gamerID,
               onValueChange =onValueChanged,
               keyboardOptions = KeyboardOptions.Default.copy(
                   imeAction = ImeAction.Next
               ),
               modifier= Modifier
                   .padding(dimensionResource(id = R.dimen.main_padding))
                   .fillMaxWidth()
                   .border(
                       (BorderStroke(1.5.dp, colorResource(id = R.color.primary))),
                       RoundedCornerShape(5.dp)
                   ),
               label = { Text(text = "Gamer_ID")},
               colors = TextFieldDefaults.outlinedTextFieldColors(
                   cursorColor = colorResource(id = R.color.primary),
                   focusedTextColor = colorResource(id = R.color.primary),
                   unfocusedTextColor = colorResource(id = R.color.primary),
                   focusedLabelColor = colorResource(id = R.color.primary),
                   unfocusedLabelColor = colorResource(id = R.color.primary),
                   containerColor = colorResource(id = R.color.black),
                   focusedBorderColor = colorResource(id = R.color.black),
                   unfocusedBorderColor = colorResource(id = R.color.black))
               )
        TextField(
            value = bio,
            onValueChange =onBioValueChanged,
            label = { Text(text = "Bio")},
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            modifier= Modifier
                .padding(dimensionResource(id = R.dimen.main_padding))
                .fillMaxWidth()
                .height(400.dp)
                .drawBehind {
                    val borderSize = 4.dp.toPx()
                    drawLine(
                        color = Color.Cyan,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = borderSize
                    )
                }
                ,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.black),
                focusedTextColor = colorResource(id = R.color.primary),
                unfocusedTextColor = colorResource(id = R.color.primary),
                unfocusedLabelColor = colorResource(id = R.color.primary),
                focusedLabelColor = colorResource(id = R.color.primary),

            )
        )

        Row (modifier=Modifier.padding(end = dimensionResource(id = R.dimen.main_padding), bottom = dimensionResource(
            id = R.dimen.main_padding
        ))){
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { onSaveChanges()},
                modifier=Modifier.shadow(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.tertiary_40)),
            ) {
                Text(
                    text = "Save Changes",
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}




