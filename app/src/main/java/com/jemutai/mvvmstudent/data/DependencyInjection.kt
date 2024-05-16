package com.jemutai.mvvmstudent.data

import android.app.Application

class StudentApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { StudentRepository(database.studentDao()) }
}