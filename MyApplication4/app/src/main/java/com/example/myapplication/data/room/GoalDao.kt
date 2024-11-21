package com.example.myapplication.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {
    @Upsert
    suspend fun upsertGoal(goal: GoalEntity)

    @Delete
    suspend fun deleteGoal(goal: GoalEntity)

    @Query("SELECT * FROM GoalEntity ORDER BY id ASC")
    fun getAllGoalsOrderById(): Flow<List<GoalEntity>>

    @Transaction
    @Query("SELECT * FROM GoalEntity")
    fun getAllGoals(): Flow<List<GoalEntity>>

    @Query("SELECT * FROM GoalEntity WHERE id = :id")
    fun getGoal(id: Int): Flow<GoalEntity?>

//    @Query("SELECT * FROM GoalRoom ORDER BY :sortType ASC")
//    fun sortGoals(sortType: SortType)
}
