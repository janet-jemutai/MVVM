package com.jemutai.mvvmstudent.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val studentClass: String
)
