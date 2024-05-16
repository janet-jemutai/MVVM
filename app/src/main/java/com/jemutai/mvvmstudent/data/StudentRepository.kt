package com.jemutai.mvvmstudent.data

class StudentRepository(private val studentDao: StudentDao) {

    suspend fun insertStudent(student: Student) {
        studentDao.insertStudent(student)
    }

    suspend fun getAllStudents(): List<Student> {
        return studentDao.getAllStudents()
    }
}