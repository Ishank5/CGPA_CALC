package com.example.cgpa_calc

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CgpaCalculatorApp() {
    val redColor = Color(0xFFD50000)
    val grayColor = Color(0xFFF2F2F2)

    var selectedCourse1 by remember { mutableStateOf("") }
    var selectedCourse2 by remember { mutableStateOf("") }
    var selectedCourse3 by remember { mutableStateOf("") }
    var selectedCourse4 by remember { mutableStateOf("") }
    var selectedCourse5 by remember { mutableStateOf("") }
    var selectedGrade1 by remember { mutableStateOf("") }
    var selectedGrade2 by remember { mutableStateOf("") }
    var selectedGrade3 by remember { mutableStateOf("") }
    var selectedGrade4 by remember { mutableStateOf("") }
    var selectedGrade5 by remember { mutableStateOf("") }
    var cgpa by remember { mutableStateOf(0.0) }

    val credits = listOf(4.0, 4.5, 3.0, 3.5, 4.0)
    val gradePoints = mapOf(
        "A+" to 10.0,
        "A" to 10.0,
        "A-" to 9.0,
        "B" to 8.0,
        "B-" to 7.0,
        "C" to 6.0,
        "C-" to 5.0
    )

    fun calculateCGPA(): Double {
        val selectedGrades = listOf(selectedGrade1, selectedGrade2, selectedGrade3, selectedGrade4, selectedGrade5)
        var totalCredits = 0.0
        var totalGradePoints = 0.0

        for (i in selectedGrades.indices) {
            val grade = selectedGrades[i]
            if (grade in gradePoints) {
                totalCredits += credits[i]
                totalGradePoints += credits[i] * gradePoints[grade]!!
            }
        }
        return if (totalCredits == 0.0) 0.0 else totalGradePoints / totalCredits
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Logo Placeholder
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(color = redColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_tiet_foreground),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
        }

        // Branch Text
        Text(
            text = "Branch - EEC",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        // Dropdown Menus for Courses and Grades
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            CourseDropdown("Course 1", selectedCourse1) { selectedCourse1 = it }
            GradeDropdown("Grade 1", selectedGrade1) { selectedGrade1 = it }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            CourseDropdown("Course 2", selectedCourse2) { selectedCourse2 = it }
            GradeDropdown("Grade 2", selectedGrade2) { selectedGrade2 = it }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            CourseDropdown("Course 3", selectedCourse3) { selectedCourse3 = it }
            GradeDropdown("Grade 3", selectedGrade3) { selectedGrade3 = it }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            CourseDropdown("Course 4", selectedCourse4) { selectedCourse4 = it }
            GradeDropdown("Grade 4", selectedGrade4) { selectedGrade4 = it }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            CourseDropdown("Course 5", selectedCourse5) { selectedCourse5 = it }
            GradeDropdown("Grade 5", selectedGrade5) { selectedGrade5 = it }
        }

        // Calculate CGPA Button
        Button(
            onClick = {
                cgpa = calculateCGPA()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = redColor),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Calculate CGPA", color = Color.White, fontSize = 16.sp)
        }

        // Display CGPA
        Text(text = "CGPA: %.2f".format(cgpa), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)

        // Reset Button
        Button(
            onClick = {
                selectedCourse1 = ""
                selectedCourse2 = ""
                selectedCourse3 = ""
                selectedCourse4 = ""
                selectedCourse5 = ""
                selectedGrade1 = ""
                selectedGrade2 = ""
                selectedGrade3 = ""
                selectedGrade4 = ""
                selectedGrade5 = ""
                cgpa = 0.0
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = grayColor),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Reset", color = Color.Black, fontSize = 16.sp)
        }
    }
}

@Composable
fun GradeDropdown(label: String, selectedGrade: String, onGradeSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val grades = listOf("A+", "A", "A-", "B", "B-", "C", "C-")

    Column(
        modifier = Modifier
            .background(Color(0xFFF2F2F2), RoundedCornerShape(8.dp))
            .padding(8.dp)
            .clickable { expanded = !expanded }
    ) {
        Text(text = label, color = Color.Gray, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = if (selectedGrade.isEmpty()) "Select your grade" else selectedGrade)
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            grades.forEach { grade ->
                DropdownMenuItem(
                    text = { Text(text = grade) },
                    onClick = {
                        onGradeSelected(grade)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun CourseDropdown(label: String, selectedCourse: String, onCourseSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Course A", "Course B", "Course C", "Course D", "Course E") // Add actual courses

    Column(
        modifier = Modifier
            .background(Color(0xFFF2F2F2), RoundedCornerShape(8.dp))
            .padding(8.dp)
            .clickable { expanded = !expanded }
    ) {
        Text(text = label, color = Color.Gray, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = if (selectedCourse.isEmpty()) "Select your course" else selectedCourse)
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { course ->
                DropdownMenuItem(
                    text = { Text(text = course) },
                    onClick = {
                        onCourseSelected(course)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCgpaCalculatorApp() {
    CgpaCalculatorApp()
}
