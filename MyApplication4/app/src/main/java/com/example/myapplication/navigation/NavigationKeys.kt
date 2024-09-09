package com.example.myapplication.navigation

import dev.enro.core.NavigationKey
import kotlinx.parcelize.Parcelize

@Parcelize
class OperationDoneDestination : NavigationKey.SupportsPush
//data class OperationDoneDestination(
//    val blah: String,
//) : NavigationKey.SupportsPush


@Parcelize
class HomeDestination : NavigationKey.SupportsPush
//data class HomeDestination(
//    val blah: String,
//) : NavigationKey.SupportsPush



@Parcelize
class EditDestination : NavigationKey.SupportsPush
//data class EditDestination(
//    val goalName: String,
//    val targetAmount: Int,
//    val currentAmount: Int,
//) : NavigationKey.SupportsPush


@Parcelize
class DetailDestination : NavigationKey.SupportsPush
//data class DetailDestination(
//    val goalName: String,
//    val targetAmount: Int,
//    val currentAmount: Int,
//) : NavigationKey.SupportsPush
