package com.example.powerseva.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

data class ComplaintDetail(
    val complaintId: String,
    val area: String,
    val ward: String,
    val problemType: String,
    val description: String,
    val status: String,
    val supportCount: Int,
    val reportedDate: String
)

@Composable
fun ComplaintDetailScreen(navController: NavHostController) {

    val complaint = remember {
        ComplaintDetail(
            complaintId = "MZP1001",
            area = "Kazi Mohammadpur",
            ward = "Ward 12",
            problemType = "Power Cut",
            description = "Complete power cut since last night. No electricity in entire area.",
            status = "Pending",
            supportCount = 18,
            reportedDate = "25 Jan 2026, 10:30 AM"
        )
    }

    val statusColor = when (complaint.status) {
        "Pending" -> Color.Red
        "In Progress" -> Color(0xFFFF9800)
        "Resolved" -> Color(0xFF4CAF50)
        else -> Color.Gray
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(16.dp)
    ) {

        Text(
            text = "Complaint Details",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                DetailRow("Complaint ID", complaint.complaintId)
                DetailRow("Area", complaint.area)
                DetailRow("Ward", complaint.ward)
                DetailRow("Problem Type", complaint.problemType)
                DetailRow("Reported On", complaint.reportedDate)

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(6.dp))

                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF5F5F5)
                    )
                ) {
                    Text(
                        text = complaint.description,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(12.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Status: ${complaint.status}",
                        fontSize = 15.sp,
                        color = statusColor
                    )
                    Text(
                        text = "Supported by ${complaint.supportCount}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE8F5E9)
            )
        ) {
            Column(modifier = Modifier.padding(14.dp)) {

                Text(
                    text = "Has the power been restored?",
                    fontSize = 15.sp,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Button(
                        onClick = { 
                            // Go back to list
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("YES")
                    }

                    Button(
                        onClick = { 
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("NO")
                    }
                }
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Text(
            text = value,
            fontSize = 14.sp
        )
    }
}
