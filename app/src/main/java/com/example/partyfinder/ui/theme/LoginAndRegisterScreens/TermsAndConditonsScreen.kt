package com.example.partyfinder.ui.theme.LoginAndRegisterScreens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.partyfinder.R
import com.example.partyfinder.ui.theme.PartyFinderTheme


@Composable
fun TermsAndConditons(closeTermsScreen:()->Unit) {

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
        ),
        Pair(
            "*Privacy Policy*",
            "At PartyUp, we are committed to protecting your personal information and your right to privacy. If you have any questions or concerns about our policy, or our practices with regards to your personal information, please contact us."
        ),
        Pair(
            "What Information Do We Collect?",
            "We only collect information that you provide to us.\n\nWe collect personal information that you voluntarily provide to us when you register on the PartyUp platform, express an interest in obtaining information about us or our products and services, when you participate in activities on the PartyUp platform or otherwise when you contact us.\nThe personal information we collect can include the following: Name, Email Address, Contact model, and Gaming Preferences. All personal information that you provide to us must be true, complete and accurate, and you must notify us of any changes to such personal information."
        ),
        Pair(
            "Will Your Information be Shared with Anyone?",
            "We only share information with your consent, to comply with laws, to provide you with services, to protect your rights, or to fulfill business obligations."
        ),
        Pair(
            "How Long Do We Keep Your Information?",
            "We keep your information for as long as necessary to fulfill the purposes outlined in this privacy policy unless otherwise required by law."
        ),
        Pair(
            "How Do We Keep Your Information Safe?",
            "We aim to protect your personal information through a system of organizational and technical security measures."
        ),
        Pair(
            "Do We Collect Information from Minors?",
            "We do not knowingly collect data from or market to children under 18 years of age."
        ),
        Pair(
            "Your Privacy Rights",
            "You may review, change, or terminate your account at any time.\n\nIf you are a resident in the European Economic Area and you believe we are unlawfully processing your personal information, you also have the right to complain to your local data protection supervisory authority."
        ),
        Pair(
            "Updates to This Policy",
            "We may update this privacy policy from time to time. The updated version will be indicated by an updated “Revised” date and the updated version will be effective as soon as it is accessible."
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .fillMaxHeight()
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
                    .clickable { closeTermsScreen() }
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
                    text = "# "+heading,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
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
        TermsAndConditons(closeTermsScreen = {})
    }
}
