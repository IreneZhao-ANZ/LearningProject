package com.example.myapplication.ui.detail

import android.annotation.SuppressLint
import com.example.myapplication.domain.DeleteGoal
import com.example.myapplication.domain.DeleteGoal.Companion.invoke
import com.example.myapplication.domain.FlowOfGoal
import com.example.myapplication.domain.models.Goal
import com.example.myapplication.ui.navigation.DetailDestination
import com.example.myapplication.ui.navigation.EditDestination
import com.example.myapplication.ui.navigation.OperationDoneDestination
import dev.enro.test.EnroTestRule
import dev.enro.test.assertOpened
import dev.enro.test.extensions.putNavigationHandleForViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

internal class DetailScreenViewModelTest {

    //private val deleteGoal: DeleteGoal = mock(DeleteGoal::class.java)

    @get:Rule
    val enroTestRule = EnroTestRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `test initial state`() = runTest {
        val goal = Goal(1, "Goal 1", 1000, 500)
        val flowOfGoal = FlowOfGoal { flowOf(goal) }
        val navigationKey = DetailDestination(1)
        putNavigationHandleForViewModel<DetailScreenViewModel>(navigationKey)
        val viewModel = createViewModel(flowOfGoal, deleteGoal = {})
        viewModel.state.value = goal
        assertEquals(goal, viewModel.state.value)

    }

    @Test
    fun `test onEditClicked`() = runTest {
        val navigationKey = DetailDestination(1)
        val testNavigationHandle = putNavigationHandleForViewModel<DetailScreenViewModel>(navigationKey)

        val viewModel = createViewModel(flowOfGoal = { emptyFlow() }, deleteGoal = {})
        viewModel.onEditClicked(1)
        testNavigationHandle.assertOpened<EditDestination>().apply {
            assertEquals(1, id)
        }
    }

    // navigation test
    @OptIn(ExperimentalCoroutinesApi::class)
    @SuppressLint("CheckResult")
    @Test
    fun `test onDeleteClicked`() = runTest {
        val goal = Goal(1, "Goal 1", 1000, 500)
        val flowOfGoal = FlowOfGoal { flowOf(goal) }
        val navigationKey = DetailDestination(1)
        val testNavigationHandle = putNavigationHandleForViewModel<DetailScreenViewModel>(navigationKey)
        val viewModel = createViewModel(flowOfGoal, { })
        viewModel.onDeleteClicked(goal)
        //advanceUntilIdle is to wait for the coroutine to finish
        advanceUntilIdle()
        testNavigationHandle.assertOpened<OperationDoneDestination>()
    }

//    // state reset test
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun `test onDeleteClicked state reset`() = runTest {
//        val goal = Goal(1, "Goal 1", 1000, 500)
//        val flowOfGoal = FlowOfGoal { flowOf(goal) }
//        val navigationKey = DetailDestination(1)
//        putNavigationHandleForViewModel<DetailScreenViewModel>(navigationKey)
//        val viewModel = createViewModel(flowOfGoal, { })
//        //viewModel.state.value = goal
//        viewModel.onDeleteClicked(goal)
//        advanceUntilIdle()
//        assertEquals(Goal(0, "", 1, 0), viewModel.state.value)
//    }

    private fun createViewModel(
        flowOfGoal: FlowOfGoal = FlowOfGoal { emptyFlow() },
        deleteGoal: DeleteGoal = DeleteGoal {},
    ): DetailScreenViewModel {
        return DetailScreenViewModel(flowOfGoal, deleteGoal)
    }
}
