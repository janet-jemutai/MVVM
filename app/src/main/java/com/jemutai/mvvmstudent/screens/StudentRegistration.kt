package com.jemutai.mvvmstudent.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jemutai.mvvmstudent.data.StudentApplication
import com.jemutai.mvvmstudent.viewmodel.StudentViewModel
import com.jemutai.mvvmstudent.viewmodel.StudentViewModelFactory

@Composable
fun StudentRegistrationScreen() {
    val context = LocalContext.current
    val application = context.applicationContext as StudentApplication
    val viewModel: StudentViewModel = viewModel(factory = StudentViewModelFactory(application.repository))

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var studentClass by remember { mutableStateOf("") }
    val students by viewModel.students.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = studentClass,
            onValueChange = { studentClass = it },
            label = { Text("Class") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.registerStudent(name, email, studentClass)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text("Register")
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (isLoading) {
            CircularProgressIndicator()
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Registered Students:")
        students.forEach { student ->
            Text("Name: ${student.name}, Email: ${student.email}, Class: ${student.studentClass}")
        }
    }
}