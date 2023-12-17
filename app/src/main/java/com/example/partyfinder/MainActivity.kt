package com.example.partyfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.partyfinder.ui.theme.PartyFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PartyFinderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                        ProfileScreen()

                }
            }
        }
    }
}

@Composable
fun CreateGamerCallApp(){
    CreateGamerCallScreen()
}
@Composable
fun CreateGamerCallScreen(modifier: Modifier=Modifier.fillMaxSize()){
    CreateGamerCallTopBar()
}

@Preview
@Composable
fun CreateGamerCallAppPreview(){
    CreateGamerCallScreen()
}


@Composable
fun CreateGamerCallTopBar(modifier: Modifier=Modifier){
    Surface(modifier=modifier.height(50.dp), color = colorResource(id = R.color.black)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "New Gamer Call", fontSize = 16.sp, color = colorResource(id = R.color.primary))
            Spacer(modifier = modifier.weight(1f))
            Image(painter = painterResource(id = R.drawable.remove_icon), contentDescription =null )
        }
    }
}



