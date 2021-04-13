package com.sedaaggez.githubjobs.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sedaaggez.githubjobs.model.Job

@Dao
interface JobDao {

    @Insert
    suspend fun insertAll(vararg jobs: Job): List<Long>

    @Query("SELECT * FROM job")
    suspend fun getAllJobs(): List<Job>

    @Query("SELECT * FROM job WHERE id = :jobId")
    suspend fun getJob(jobId: Int): Job

    @Query("DELETE FROM job")
    suspend fun deleteAllJobs()

}