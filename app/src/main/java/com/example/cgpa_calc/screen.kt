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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp).padding(top = 100.dp),
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
                contentDescription = "Image description",
                modifier = Modifier.size(100.dp).clip(CircleShape)
            )

        }

        // Branch Text
        Text(
            text = "Branch - EEC",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        // Dropdown Menus for Courses
        CourseDropdown("Course 1", selectedCourse1) { selectedCourse1 = it }
        CourseDropdown("Course 2", selectedCourse2) { selectedCourse2 = it }
        CourseDropdown("Course 3", selectedCourse3) { selectedCourse3 = it }
        CourseDropdown("Course 4", selectedCourse4) { selectedCourse4 = it }
        CourseDropdown("Course 5", selectedCourse5) { selectedCourse5 = it }

        // Calculate CGPA Button
        Button(
            onClick = { /* Add calculate CGPA logic here */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = redColor),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Calculate CGPA", color = Color.White, fontSize = 16.sp)
        }

        // Reset Button
        Button(
            onClick = {
                selectedCourse1 = ""
                selectedCourse2 = ""
                selectedCourse3 = ""
                selectedCourse4 = ""
                selectedCourse5 = ""
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
fun CourseDropdown(label: String, selectedCourse: String, onCourseSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Course A", "Course B", "Course C", "Course D", "Course E") // Add actual courses

    Column(modifier = Modifier
        .fillMaxWidth()
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
