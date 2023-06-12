package com.manage.eto_assignment.data.dao

import androidx.room.*
import com.manage.eto_assignment.data.model.LocalTask
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: LocalTask)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertAllTask(task: List<LocalTask>)

    @Query("Select * from task_table")
    fun getAllTask(): Flow<List<LocalTask>>

    @Update
    fun updateTask(task: LocalTask)
}