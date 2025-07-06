package com.eventplanner.room_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


// EventDao.kt
@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event)

    @Update
    suspend fun update(event: Event)

    @Delete
    suspend fun delete(event: Event)

    @Query("SELECT * FROM event_table WHERE date = :date ORDER BY time ASC")
    fun getEventsByDate(date: String): LiveData<List<Event>>

    @Query("SELECT * FROM event_table WHERE date >= :currentDate ORDER BY date, time ASC")
    fun getUpcomingEvents(currentDate: String): LiveData<List<Event>>
}