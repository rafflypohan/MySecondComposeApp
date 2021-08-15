package com.rafflypohan.mymoviecomposeapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rafflypohan.mymoviecomposeapp.R

private val light = Font(R.font.poppins_light, FontWeight.Light)
private val regular = Font(R.font.poppins_regular, FontWeight.Normal)
private val medium = Font(R.font.poppins_medium, FontWeight.Medium)
private val semibold = Font(R.font.poppins_semibold, FontWeight.SemiBold)
private val bold = Font(R.font.poppins_bold, FontWeight.Bold)

val fontFamily = FontFamily(fonts = listOf(light, regular, medium, semibold, bold))

// Set of Material typography styles to start with
val Typography = Typography(

    h1 = TextStyle(
      fontFamily = fontFamily,
      fontWeight = FontWeight.Bold
    ),

    h2 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
    ),

    body1 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
    )

)