package com.example.myapplication.data

import android.util.Log
import com.example.myapplication.data.Mappers.toDomain
import com.example.myapplication.data.room.GoalDao
import com.example.myapplication.domain.FlowOfGoal
import com.example.myapplication.domain.FlowOfGoals
import com.example.myapplication.domain.Mappers.toDb
import com.example.myapplication.domain.UpsertGoal
import com.example.myapplication.domain.models.Goal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

class GoalRepository(
    private val goalDao: GoalDao
) : FlowOfGoal, FlowOfGoals, UpsertGoal {

    /*fun getAllGoals() : List<Goal>

    fun getGoal(goalId: Int): Goal

    suspend fun upsertGoal(goal: GoalRoom)

    suspend fun deleteGoal(goalId: Int)*/

    /*   suspend fun sortGoals(sortType: SortType)
       private val _sortType = MutableStateFlow(SortType.ID)
       private val _state = MutableStateFlow(GoalState())
       fun onEvent(event: GoalEvent) {
           when (event) {
               is GoalEvent.GetAllGoals -> {
                   _state.value = _state.value.copy(goals = GoalDao.getAllGoals())
               }
               is GoalEvent.GetGoal -> {
                   _state.value = _state.value.copy(goals = GoalDao.getGoal(event.id))
               }
               is GoalEvent.Upsert -> {
                   _state.value = _state.value.copy(goals = GoalDao.getAllGoals())
               }
               is GoalEvent.DeleteGoal -> {
                   GoalDao.deleteGoal(event.id)
                   _state.value = _state.value.copy(goals = GoalDao.getAllGoals())
               }

               is GoalEvent.Delete -> TODO()
               GoalEvent.HideDialog -> TODO()
               is GoalEvent.Insert -> TODO()
               GoalEvent.ShowDialog -> TODO()
               is GoalEvent.SortGoals -> TODO()

           }
       }*/

    override fun flowOfGoals(): Flow<List<Goal>> {
        Log.d("flowOfGoals: ", "XD")
        return goalDao.getAllGoals().mapLatest {
            Log.d("goals: ", "state = $it")
            it.toDomain() }
    }

    override fun flowOfGoal(id: Int): Flow<Goal?> {
        return goalDao.getGoal(id = id).map { it?.toDomain() }
    }
//    suspend fun upsetGoal(goal: Goal) {
//        goalDao.upsertGoal(goal.toDb())
//    }

    override suspend fun upsertGoal(goal: Goal) {
        println("IreneLog-GoalRepository-flowOfUpsert: goal=${goal}")
        goalDao.upsertGoal(goal.toDb())
    }
}


// override fun flowOfGoal(goalId: Int) : Goal

// suspend fun createGoal(goal: Goal)

// suspend fun updateGoal(goal: Goal)

// suspend fun deleteGoal(goalId: Int)


// Set up Room database
// GoalDao
// GoalDatabase
// Navigate between screens with goal ID
// Migrate everything to flows + state flows



