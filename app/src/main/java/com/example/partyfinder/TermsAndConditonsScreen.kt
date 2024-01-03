package com.example.partyfinder


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import com.example.partyfinder.ui.theme.PartyFinderTheme


@Composable
fun TermsAndConditons() {

//        Terms
    val termsSections = listOf(
        Pair(
            "Acceptance of Terms",
            "By accessing and using PartyUp, you accept and agree to be bound by the terms and provision of this agreement."
        ),
        Pair(
            "Provision of Services",
            "You agree and acknowledge that PartyUp is entitled to modify, improve or discontinue any of its services at its sole discretion and without notice to you."
        ),
        Pair(
            "Privacy Policy",
            "Your use of PartyUp is subject to PartyUp's Privacy Policy. Please review our Privacy Policy, which also governs the site and informs users of our data collection practices."
        ),
        Pair(
            "Electronic Communications",
            "Visiting PartyUp or sending emails to PartyUp constitutes electronic communications. You consent to receive electronic communications and you agree that all agreements, notices, disclosures and other communications that we provide to you electronically, via email and on the Site, satisfy any legal requirement that such communications be in writing."
        ),
        Pair(
            "Your Account",
            "If you use this site, you are responsible for maintaining the confidentiality of your account and password and for restricting access to your computer, and you agree to accept responsibility for all activities that occur under your account or password."
        ),
        Pair(
            "Changes to Terms",
            "PartyUp reserves the right, in its sole discretion, to change the Terms under which PartyUp is offered. The most current version of the Terms will supersede all previous versions."
        )
    )


    Column(
        modifier = Modifier
            .height(808.dp)
            .width(393.dp)
            .background(color = colorResource(id = R.color.black)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.top_bar_height))
        ) {
            Text(
                text = "Terms & Conditions",
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(id = R.color.primary),
                modifier = Modifier
                    .align(Alignment.Center)
            )

            //        Close Icon
            Image(
                painter = painterResource(id = (R.drawable.close_blue)),
                contentDescription = "BackIcon",
                modifier = Modifier
                    .padding(0.dp, 2.dp, 20.dp, 0.dp)
                    .size(25.dp)
                    .align(Alignment.CenterEnd)
            )

        }

        // Bottom border
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(colorResource(id = R.color.SubliminalText))
        )

        LazyColumn(
            modifier = Modifier
        ) {
            items(termsSections) { (heading, description) ->
                Text(
                    text = "> "+heading,
                    style = MaterialTheme.typography.bodyLarge,
                    color = colorResource(id = R.color.primary),
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.main_padding),
                        dimensionResource(id = R.dimen.main_padding), dimensionResource(id = R.dimen.main_padding),0.dp
                    ))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = colorResource(id = R.color.SubliminalText),
                    modifier = Modifier.padding(start = 32.dp,
                        dimensionResource(id = R.dimen.main_padding)/2, dimensionResource(id = R.dimen.main_padding),0.dp
                    ))
            }
        }
    }
}


@Preview
@Composable
fun PreviewTermsAndConditons(){
    PartyFinderTheme {
        TermsAndConditons()
    }
}
