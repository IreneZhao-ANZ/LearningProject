package com.example.myapplication.data

import com.example.myapplication.data.mappers.toDomain
import com.example.myapplication.data.room.GoalDao
import com.example.myapplication.domain.DeleteGoal
import com.example.myapplication.domain.FlowOfGoal
import com.example.myapplication.domain.FlowOfGoals
import com.example.myapplication.domain.mappers.toDb
import com.example.myapplication.domain.UpsertGoal
import com.example.myapplication.domain.models.Goal
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GoalRepository @Inject constructor(
    private val goalDao: GoalDao
) : FlowOfGoal, FlowOfGoals, UpsertGoal, DeleteGoal {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun flowOfGoals(): Flow<List<Goal>> {
        return goalDao.getAllGoals().mapLatest {
            it.toDomain() }
    }

    override fun flowOfGoal(id: Int): Flow<Goal> {
        return goalDao.getGoal(id = id).map { it?.toDomain() ?: Goal(0, "", 0, 0) }
    }

    override suspend fun upsertGoal(goal: Goal) {
        goalDao.upsertGoal(goal.toDb())
    }

    override suspend fun deleteGoal(goal: Goal) {
        goalDao.deleteGoal(goal.toDb())
    }
}
