package com.example.myapplication.data
import com.example.myapplication.data.room.GoalDao
import com.example.myapplication.domain.mappers.toDb
import com.example.myapplication.domain.models.Goal
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test




import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`


internal class GoalRepositoryTest {

    private lateinit var goalDao: GoalDao
    private lateinit var goalRepository: GoalRepository

    @Before
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

        assertEquals(listOf(goals), result)
    }

    @Test
    fun flowOfGoal() = runTest {
        val goalOne = Goal(1, "Goal 1", 1000, 500)
        val noGoal = Goal(0, "", 0, 0)
        `when`(goalDao.getGoal(1)).thenReturn(flowOf(goalOne.toDb()))
        `when`(goalDao.getGoal(3)).thenReturn(flowOf(noGoal.toDb()))

        val resultOne = goalRepository.flowOfGoal(1).toList()
        val resultTwo = goalRepository.flowOfGoal(3).toList()

        assertEquals(listOf(goalOne), resultOne)
        assertEquals(listOf(noGoal), resultTwo)
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
