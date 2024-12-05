package com.example.myapplication.ui.composable

import com.example.myapplication.domain.models.Goal

data class ClickableBoxData(
    val goal: Goal,
    val onClick: () -> Unit = {},
    val isEdit: Boolean = true,
)
