package com.example.todolistkt.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    var title: String,
    var date: Long
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int? = null
}