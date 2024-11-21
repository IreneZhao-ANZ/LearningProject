package com.example.myapplication.ui.navigation

import dev.enro.core.NavigationKey
import kotlinx.parcelize.Parcelize

//data class GoalData(
//    val Text: String,
//    val TargetAmount: Int,
//    val CurrentAmount: Int,
//)

@Parcelize
class OperationDoneDestination : NavigationKey.SupportsPush
//data class OperationDoneDestination(
//    val blah: String,
//) : NavigationKey.SupportsPush


@Parcelize
data object HomeDestination : NavigationKey.SupportsPush
//data class HomeDestination(
//    val blah: String,
//) : NavigationKey.SupportsPush


@Parcelize
//class EditDestination : NavigationKey.SupportsPush
data class EditDestination(
    val id: Int
) : NavigationKey.SupportsPush

@Parcelize
//class DetailDestination : NavigationKey.SupportsPush
data class DetailDestination(
    val id: Int
) : NavigationKey.SupportsPush
