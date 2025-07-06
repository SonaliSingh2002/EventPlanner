package com.eventplanner.room_db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_table")
data class Event(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val date: String,  // Format: yyyy-MM-dd
    val time: String   // Format: HH:mm
)