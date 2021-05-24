package com.example.notforgot2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "myNotes")
data class Note(
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val title: String,
    val subtitle: String,
    val description: String,
    val deadline: String,
    val priority: String,
    val check: Boolean
)