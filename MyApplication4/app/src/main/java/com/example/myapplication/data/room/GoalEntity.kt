package com.example.myapplication.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GoalEntity(
    val name: String,
    val targetAmount: Int,
    val currentAmount: Int,
    val sortType: SortType = SortType.ID,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

)

