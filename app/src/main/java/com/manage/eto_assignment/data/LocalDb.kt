package com.manage.eto_assignment.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.manage.eto_assignment.data.dao.TaskDao
import com.manage.eto_assignment.data.model.LocalTask

@Database(entities = [LocalTask::class], version = 1)
abstract class LocalDb : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        private var INSTANCE: LocalDb? = null
        fun getInstance(applicationContext: Context): LocalDb? {
            if (INSTANCE == null) {
                synchronized(LocalDb::class) {
                    INSTANCE =
                        Room.databaseBuilder(applicationContext, LocalDb::class.java, "TaskDb")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}