package com.example.partyfinder.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import com.example.partyfinder.R

val Jura = FontFamily(
    Font(R.font.jura_regular),
    Font(R.font.jura_bold)
)

val Shackle = FontFamily(
    Font(R.font.shackle_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    displayLarge = TextStyle(
        fontFamily = Jura,
        fontWeight = FontWeight.Bold,
        fontSize = 64.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = Jura,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    )
)

