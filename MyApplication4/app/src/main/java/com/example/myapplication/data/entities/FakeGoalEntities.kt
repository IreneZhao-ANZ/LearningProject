package com.example.myapplication.data.entities

//Entities when we are testing

object FakeGoalEntities {
    val rainyDay: GoalEntity = GoalEntity(
        id = 1,
        name = "Rainy Day Fund",
        targetAmount = 1000,
        currentAmount = 100
    )
    val buyAndOwnAHome: GoalEntity = GoalEntity(
        id = 2,
        name = "Buy and Own a Home",
        targetAmount = 100000,
        currentAmount = 10000
    )
}

