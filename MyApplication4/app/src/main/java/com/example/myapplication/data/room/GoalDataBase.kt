package com.example.myapplication.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [GoalEntity::class],
    version = 1
)
abstract class GoalDataBase: RoomDatabase() {
    abstract val goalDao: GoalDao
    companion object {
        const val DATABASE_NAME = "goal_database"
    }
}
