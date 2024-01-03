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
fun PrivacyPolicy() {

//        Policy
    val policySections = listOf(
        Pair(
            "Introduction",
            "At PartyUp, we are committed to protecting your personal information and your right to privacy. If you have any questions or concerns about our policy, or our practices with regards to your personal information, please contact us."
        ),
        Pair(
            "What Information Do We Collect?",
            "In Short: We only collect information that you provide to us.\n\nWe collect personal information that you voluntarily provide to us when you register on the PartyUp platform, express an interest in obtaining information about us or our products and services, when you participate in activities on the PartyUp platform or otherwise when you contact us.\nThe personal information we collect can include the following: Name, Email Address, Contact Data, and Gaming Preferences. All personal information that you provide to us must be true, complete and accurate, and you must notify us of any changes to such personal information."
        ),
        Pair(
            "Will Your Information be Shared with Anyone?",
            "In Short: We only share information with your consent, to comply with laws, to provide you with services, to protect your rights, or to fulfill business obligations."
        ),
        Pair(
            "How Long Do We Keep Your Information?",
            "In Short: We keep your information for as long as necessary to fulfill the purposes outlined in this privacy policy unless otherwise required by law."
        ),
        Pair(
            "How Do We Keep Your Information Safe?",
            "In Short: We aim to protect your personal information through a system of organizational and technical security measures."
        ),
        Pair(
            "Do We Collect Information from Minors?",
            "In Short: We do not knowingly collect data from or market to children under 18 years of age."
        ),
        Pair(
            "Your Privacy Rights",
            "In Short: You may review, change, or terminate your account at any time.\n\nIf you are a resident in the European Economic Area and you believe we are unlawfully processing your personal information, you also have the right to complain to your local data protection supervisory authority."
        ),
        Pair(
            "Updates to This Policy",
            "We may update this privacy policy from time to time. The updated version will be indicated by an updated “Revised” date and the updated version will be effective as soon as it is accessible."
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
                text = "Privacy Policy",
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
            items(policySections) { (heading, description) ->
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
fun PreviewPrivacyPolicy(){
    PartyFinderTheme {
        PrivacyPolicy()
    }
}
