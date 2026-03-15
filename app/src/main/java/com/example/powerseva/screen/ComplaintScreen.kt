package com.example.powerseva.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

data class ComplaintItem(
    val id: String,
    val area: String,
    val ward: String,
    val problemType: String,
    val status: String,
    val supportCount: Int
)

@Composable
fun ComplaintListScreen(navController: NavHostController) {

    val complaints = remember {
        listOf(
            ComplaintItem("MFP101", "Brahampura", "Ward 2", "Power Cut", "Pending", 6),
            ComplaintItem("MFP181", "Bhagwanpur", "Ward 1", "Voltage Fluctuation", "Pending", 3),
            ComplaintItem("MZP1003", "Mithanpura", "Ward 5", "Transformer Issue", "Pending", 12),
            ComplaintItem("MZP1004", "Motijheel", "Ward 15", "Wire Fault", "Resolved", 3)
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add") },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Text("+", fontSize = 24.sp, color = Color.White)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            Text(
                text = "My Complaints – Muzaffarpur",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(14.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(complaints) { complaint ->
                    ComplaintCard(complaint) {
                        navController.navigate("detail")
                    }
                }
            }
        }
    }
}

@Composable
fun ComplaintCard(complaint: ComplaintItem, onClick: () -> Unit) {

    val statusColor = when (complaint.status) {
        "Pending" -> Color.Red
        "In Progress" -> Color(0xFFFF9800)
        "Resolved" -> Color(0xFF4CAF50)
        else -> Color.Gray
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            Text(
                text = "Complaint ID: ${complaint.id}",
                fontSize = 12.sp,
                color = Color.Gray
            )

            Text(
                text = complaint.problemType,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "${complaint.area}, ${complaint.ward}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = complaint.status,
                    fontSize = 14.sp,
                    color = statusColor
                )

                Text(
                    text = "Supported by ${complaint.supportCount}",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
