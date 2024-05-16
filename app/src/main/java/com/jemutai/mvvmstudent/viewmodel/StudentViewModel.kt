package com.jemutai.mvvmstudent.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jemutai.mvvmstudent.data.Student
import com.jemutai.mvvmstudent.data.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StudentViewModel(private val repository: StudentRepository) : ViewModel() {

    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students: StateFlow<List<Student>> = _students

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun registerStudent(name: String, email: String, studentClass: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val student = Student(name = name, email = email, studentClass = studentClass)
            repository.insertStudent(student)
            _students.value = repository.getAllStudents()
            _isLoading.value = false
        }
    }

    fun loadStudents() {
        viewModelScope.launch {
            _students.value = repository.getAllStudents()
        }
    }
}