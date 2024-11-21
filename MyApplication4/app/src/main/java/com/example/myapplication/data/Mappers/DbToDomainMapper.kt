package com.example.myapplication.data.Mappers

import com.example.myapplication.data.room.GoalEntity
import com.example.myapplication.domain.models.Goal

fun List<GoalEntity>.toDomain() = map {
    it.toDomain()
}

fun GoalEntity.toDomain() = Goal(
    id = id,
    name = name,
    targetAmount = targetAmount,
    currentAmount = currentAmount
)
