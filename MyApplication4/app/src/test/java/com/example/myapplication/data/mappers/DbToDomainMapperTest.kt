package com.example.myapplication.data.mappers

import com.example.myapplication.data.room.GoalEntity
import com.example.myapplication.domain.models.Goal
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

internal class DbToDomainMapperTest {

    @Test
    fun `GoalEntity is correctly mapped to Goal`() = runTest {
        // Given
        val goalEntity = GoalEntity(
            id = 1,
            name = "Test Goal",
            targetAmount = 1000,
            currentAmount = 500
        )

        // When
        val goal = goalEntity.toDomain()

        // Then
        val expectedGoal = Goal(
            id = 1,
            name = "Test Goal",
            targetAmount = 1000,
            currentAmount = 500
        )
        assertEquals(expectedGoal, goal)
    }

    @Test
    fun `GoalEntity list is correctly mapped to Goal list`() = runTest  {
        // Given
        val goalEntities = listOf(
            GoalEntity(
                id = 1,
                name = "Test Goal 1",
                targetAmount = 1000,
                currentAmount = 500
            ),
            GoalEntity(
                id = 2,
                name = "Test Goal 2",
                targetAmount = 2000,
                currentAmount = 1000
            )
        )

        // When
        val goals = goalEntities.toDomain()

        // Then
        val expectedGoals = listOf(
            Goal(
                id = 1,
                name = "Test Goal 1",
                targetAmount = 1000,
                currentAmount = 500
            ),
            Goal(
                id = 2,
                name = "Test Goal 2",
                targetAmount = 2000,
                currentAmount = 1000
            )
        )
        assertEquals(expectedGoals, goals)
    }
}
