package com.example.partyfinder.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.partyfinder.R


val Jura =FontFamily(
    Font(R.font.jura_regular),
)

val JuraBold=FontFamily(
    Font(R.font.jura_bold)
)

val Shackle=FontFamily(
    Font(R.font.shackle_regular)

)

val Inter= FontFamily(
    Font(R.font.inter_regular)
)




// Set of Material typography styles to start with
val Typography = Typography(

    displayLarge = TextStyle(
        fontFamily = JuraBold,
        fontWeight = FontWeight.Bold,
        fontSize = 64.sp,
    ),
    displayMedium =TextStyle(
        fontFamily= JuraBold,
        fontWeight = FontWeight.Bold,
        fontSize = 52.sp
    ),
    displaySmall = TextStyle(
        fontFamily = JuraBold,
        fontWeight = FontWeight.Bold,
        fontSize = 44.sp
    ),
    headlineLarge =TextStyle(
        fontFamily = Jura,
        fontSize = 40.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Jura,
        fontSize = 36.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Jura,
        fontSize = 32.sp
    ),
    titleLarge = TextStyle(
        fontFamily = JuraBold,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = JuraBold,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    titleSmall = TextStyle(
        fontFamily = JuraBold,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Inter,
//        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Inter,
        fontSize = 12.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Inter,
        fontSize = 14.sp
    ),

    labelLarge = TextStyle(
        fontFamily = JuraBold,
        fontSize = 12.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    )

)



