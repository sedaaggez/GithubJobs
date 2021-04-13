package com.sedaaggez.githubjobs.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(com.sedaaggez.githubjobs.model.Job::class), version = 1)
abstract class JobDatabase  : RoomDatabase() {

    abstract fun jobDao() : JobDao

    companion object {
        @Volatile private var instance : JobDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, JobDatabase::class.java, "jobdatabase"
        ).build()
    }
}