package com.eventplanner.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.eventplanner.room_db.DatabaseProvider
import com.eventplanner.model.Event
import com.eventplanner.room_db.EventDao
import kotlinx.coroutines.launch

class EventViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: EventRepository
    private val eventDao: EventDao

    init {
        val db = DatabaseProvider.Companion.getDatabase(application)
        eventDao = db.eventDao()
        repository = EventRepository(eventDao)
    }

    fun getEventsByDate(date: String): LiveData<List<Event>> = repository.getEventsByDate(date)
    fun getUpcomingEvents(currentDate: String): LiveData<List<Event>> = repository.getUpcomingEvents(currentDate)

    fun insert(event: Event) = viewModelScope.launch { repository.insert(event) }
    fun update(event: Event) = viewModelScope.launch { repository.update(event) }
    fun delete(event: Event) = viewModelScope.launch { repository.delete(event) }
}