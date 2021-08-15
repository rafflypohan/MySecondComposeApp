package com.rafflypohan.mymoviecomposeapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val imagePath: String,
    val rating: Double,
    val genre: String,
    val releaseDate: String,
    val duration: String
) : Parcelable
