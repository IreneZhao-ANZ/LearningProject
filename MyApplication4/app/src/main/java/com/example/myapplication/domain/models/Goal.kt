package com.example.myapplication.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Goal(
    val id: Int,
    val name: String,
    val targetAmount: Int,
    val currentAmount: Int
) : Parcelable
