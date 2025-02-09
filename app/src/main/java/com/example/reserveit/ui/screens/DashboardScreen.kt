package com.example.reserveit.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class ScheduleItem(
    val date: String,
    val title: String,
    val time: String,
    val location: String,
    val year: String
)

val sampleScheduleItems = listOf(
    ScheduleItem("JUNE 10", "Operating System", "8:00 AM - 10:00 AM", "SF 01", "1st Year"),
    ScheduleItem("JUNE 10", "Database Systems", "10:00 AM - 12:00 PM", "SF 02", "2nd Year"),
    ScheduleItem("JUNE 11", "Network Security", "9:00 AM - 11:00 AM", "SF 03", "3rd Year"),
    ScheduleItem("JUNE 11", "Software Engineering", "1:00 PM - 3:00 PM", "SF 04", "4th Year"),
    ScheduleItem("JUNE 12", "Algorithms", "8:00 AM - 10:00 AM", "SF 05", "1st Year"),
    ScheduleItem("JUNE 12", "Web Development", "10:00 AM - 12:00 PM", "SF 06", "2nd Year")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    var selectedCourse by remember { mutableStateOf("Information Communication Technology") }
    val courses = listOf(
        "Information Communication Technology",
        "Engineering Technology",
        "Bio System Technology"
    )

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Dashboard") },
                actions = {
                    IconButton(onClick = { /* Handle notifications */ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    selected = true,
                    onClick = { /* Handle navigation */ },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Schedule") },
                    selected = false,
                    onClick = { /* Handle navigation */ },
                    label = { Text("Schedule") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Calendar") },
                    selected = false,
                    onClick = { /* Handle navigation */ },
                    label = { Text("Calendar") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    selected = false,
                    onClick = { /* Handle navigation */ },
                    label = { Text("Settings") }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "TODAY'S SCHEDULE",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Dropdown Menu for Course Selection
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it }
            ) {
                OutlinedTextField(
                    value = selectedCourse,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    courses.forEach { course ->
                        androidx.compose.material3.DropdownMenuItem(
                            text = { Text(course) },
                            onClick = {
                                selectedCourse = course
                                expanded = false
                            }
                        )
                    }
                }
            }

            // Scrollable List of Schedule Cards
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(sampleScheduleItems) { item ->
                    ScheduleCard(scheduleItem = item)
                }
            }
        }
    }
}

@Composable
fun ScheduleCard(scheduleItem: ScheduleItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = scheduleItem.date,
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = scheduleItem.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = scheduleItem.time,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = scheduleItem.location,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = scheduleItem.year,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Button(
                onClick = { /* Handle check */ },
                modifier = Modifier.align(Alignment.Bottom)
            ) {
                Text("Check")
            }
        }
    }
}