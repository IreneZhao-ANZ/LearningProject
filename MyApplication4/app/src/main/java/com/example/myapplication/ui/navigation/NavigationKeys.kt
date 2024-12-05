package com.example.myapplication.ui.navigation

import dev.enro.core.NavigationKey
import kotlinx.parcelize.Parcelize

@Parcelize
class OperationDoneDestination : NavigationKey.SupportsPush


@Parcelize
data object HomeDestination : NavigationKey.SupportsPush


@Parcelize
data class EditDestination(
    val id: Int
) : NavigationKey.SupportsPush


@Parcelize
data class DetailDestination(
    val id: Int
) : NavigationKey.SupportsPush
