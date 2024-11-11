package com.example.cgpa_calc

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.layout.Arrangement


import androidx.compose.ui.text.style.TextOverflow



@Composable
fun CgpaCalculatorApp() {
    val backgroundPainter = painterResource(id = R.mipmap.ic_tietbg2_foreground) // Replace with your image resource

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background Image with reduced opacity
        Image(
            painter = backgroundPainter,
            contentDescription = "Background",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.2f), // Adjust opacity here (0.1f for 10% opacity)
            contentScale = ContentScale.Crop
        )

        // Foreground content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Your UI content here
        }
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
    var cgpa by remember { mutableDoubleStateOf(0.0) }

    val courseCredits = mapOf(
        "Chemistry" to 4.0,
        "Programming - C" to 4.0,
        "Electrical and Electronics" to 4.5,
        "Environment" to 2.0,
        "Mathematics 1" to 3.5
    )
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
        val selectedCourses = listOf(selectedCourse1, selectedCourse2, selectedCourse3, selectedCourse4, selectedCourse5)
        val selectedGrades = listOf(selectedGrade1, selectedGrade2, selectedGrade3, selectedGrade4, selectedGrade5)
        var totalCredits = 0.0
        var totalGradePoints = 0.0

        for (i in selectedCourses.indices) {
            val course = selectedCourses[i]
            val grade = selectedGrades[i]
            val credit = courseCredits[course] ?: 0.0
            if (credit > 0 && grade in gradePoints) {
                totalCredits += credit
                totalGradePoints += credit * gradePoints[grade]!!
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
                    .size(80.dp)
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
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            CourseDropdown(selectedCourse1) { selectedCourse1 = it }
            Spacer(modifier= Modifier.width(16.dp) )
            GradeDropdown(selectedGrade1) { selectedGrade1 = it }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            CourseDropdown(selectedCourse2) { selectedCourse2 = it }
            Spacer(modifier= Modifier.width(16.dp) )
            GradeDropdown(selectedGrade2) { selectedGrade2 = it }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            CourseDropdown(selectedCourse3) { selectedCourse3 = it }
            Spacer(modifier= Modifier.width(16.dp) )
            GradeDropdown(selectedGrade3) { selectedGrade3 = it }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            CourseDropdown(selectedCourse4) { selectedCourse4 = it }
            Spacer(modifier= Modifier.width(16.dp) )
            GradeDropdown(selectedGrade4) { selectedGrade4 = it }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            CourseDropdown(selectedCourse5) { selectedCourse5 = it }
            Spacer(modifier= Modifier.width(16.dp) )
            GradeDropdown(selectedGrade5) { selectedGrade5 = it }
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
}}
@Composable
fun GradeDropdown(selectedGrade: String, onGradeSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val grades = listOf("A+", "A", "A-", "B", "B-", "C", "C-")

    Box {
        Column(
            modifier = Modifier
                .width(200.dp) // Set a fixed width for consistent size
                .background(Color(0xFFF2F2F2), RoundedCornerShape(8.dp))
                .padding(8.dp)
                .clickable { expanded = !expanded }
        ) {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = if (selectedGrade.isEmpty()) "Select your grade" else selectedGrade,
                    modifier = Modifier.weight(1f),
                )

                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop Down Arrow",
                    tint = Color.Gray
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                grades.forEach { grade ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = grade
                            )
                        },
                        onClick = {
                            onGradeSelected(grade)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun CourseDropdown(selectedCourse: String, onCourseSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Chemistry", "Programming - C", "Electrical and Electronics", "Environment", "Mathematics 1")

    Box {
        Column(
            modifier = Modifier
                .width(200.dp) // Set a fixed width for consistent size
                .background(Color(0xFFF2F2F2), RoundedCornerShape(8.dp))
                .padding(8.dp)
                .clickable { expanded = !expanded }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (selectedCourse.isEmpty()) "Select your course" else selectedCourse,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop Down Arrow",
                    tint = Color.Gray
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { course ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = course,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        onClick = {
                            onCourseSelected(course)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCgpaCalculatorApp() {
    CgpaCalculatorApp()
}
