package com.example.pokemon.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    val name: String,
    val number: String,
    val type: String,
    val description: String,
    val photo: String,
    val color: Int,
) : Parcelable
