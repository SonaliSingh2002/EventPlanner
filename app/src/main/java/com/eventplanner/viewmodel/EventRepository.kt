package com.eventplanner.viewmodel

import androidx.lifecycle.LiveData
import com.eventplanner.model.Event
import com.eventplanner.room_db.EventDao

class EventRepository(private val dao: EventDao) {
    fun getEventsByDate(date: String): LiveData<List<Event>> = dao.getEventsByDate(date)
    fun getUpcomingEvents(currentDate: String): LiveData<List<Event>> = dao.getUpcomingEvents(currentDate)

    suspend fun insert(event: Event) = dao.insert(event)
    suspend fun update(event: Event) = dao.update(event)
    suspend fun delete(event: Event) = dao.delete(event)
}