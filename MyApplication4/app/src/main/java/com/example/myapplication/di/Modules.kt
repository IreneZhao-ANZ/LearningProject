package com.example.myapplication.di

import android.app.Application
import androidx.room.Room
import com.example.myapplication.data.GoalRepository
import com.example.myapplication.data.room.GoalDao
import com.example.myapplication.data.room.GoalDataBase
import com.example.myapplication.domain.DeleteGoal
import com.example.myapplication.domain.FlowOfGoal
import com.example.myapplication.domain.FlowOfGoals
import com.example.myapplication.domain.UpsertGoal
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// region Domain

/*FlowOfGoals*/
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideGoalDatabase(app: Application) = Room.databaseBuilder(
        app,
        GoalDataBase::class.java,
        GoalDataBase.DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun providesGoalDao(db: GoalDataBase): GoalDao {
        return db.goalDao
    }


}

/*FlowOfGoals*/
@Module
@InstallIn(SingletonComponent::class)
abstract class FlowOfGoalsModule {
    @Binds
    abstract fun bindFlowOfGoals(goalRepository: GoalRepository): FlowOfGoals
}

/*FlowOfGoal*/
@Module
@InstallIn(SingletonComponent::class)
abstract class FlowOfGoalModule {
    @Binds
    abstract fun bindFlowOfGoal(goalRepository: GoalRepository): FlowOfGoal
}

/*UpsertGoal*/
@Module
@InstallIn(SingletonComponent::class)
abstract class UpsertGoalModule {
    @Binds
    abstract fun bindUpsertGoal(goalRepository: GoalRepository): UpsertGoal
}
/*DeleteGoal*/
@Module
@InstallIn(SingletonComponent::class)
abstract class DeleteGoalModule {
    @Binds
    abstract fun bindDeleteGoal(goalRepository: GoalRepository): DeleteGoal
}
// endregion


// region Data

/*GoalRepository*/
@Module
@InstallIn(SingletonComponent::class)
object GoalRepositoryModule {
    @Singleton
    @Provides
    fun providesGoalRepository(goalDao: GoalDao): GoalRepository {
        return GoalRepository(goalDao)
    }
}

/*FakeGoalEntity*/

// endregion
