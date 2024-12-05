package com.example.myapplication.data
import com.example.myapplication.data.room.GoalDao
import com.example.myapplication.domain.mappers.toDb
import com.example.myapplication.domain.models.Goal
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`


internal class GoalRepositoryTest {

    private lateinit var goalDao: GoalDao
    private lateinit var goalRepository: GoalRepository

    @BeforeEach
    fun setUp() {
        goalDao = mock(GoalDao::class.java)
        goalRepository = GoalRepository(goalDao)
    }

    @Test
    fun flowOfGoals() = runTest {
        val goals = listOf(
            Goal(1, "Goal 1", 1000, 500),
            Goal(2, "Goal 2", 2000, 1000)
        )
        `when`(goalDao.getAllGoals()).thenReturn(flowOf(goals.map { it.toDb() }))

        val result = goalRepository.flowOfGoals().toList()

        assertEquals(goals, result)
    }

    @Test
    fun flowOfGoal() = runBlocking {
        val goal = Goal(1, "Goal 1", 1000, 500)
        `when`(goalDao.getGoal(1)).thenReturn(flowOf(goal.toDb()))

        val result = goalRepository.flowOfGoal(1).toList()

        assertEquals(goal, result)
    }

    @Test
    fun upsertGoal() = runBlocking {
        val goal = Goal(1, "Goal 1", 1000, 500)

        goalRepository.upsertGoal(goal)

        verify(goalDao, times(1)).upsertGoal(goal.toDb())
    }

    @Test
    fun deleteGoal() = runBlocking {
        val goal = Goal(1, "Goal 1", 1000, 500)

        goalRepository.deleteGoal(goal)

        verify(goalDao, times(1)).deleteGoal(goal.toDb())
    }
}
