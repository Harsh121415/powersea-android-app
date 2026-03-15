package com.example.powerseva.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.powerseva.screen.ComplaintItem

@Composable
fun ComplaintCardItem(
    complaint: ComplaintItem,
    onClick: () -> Unit
) {
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
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = complaint.problemType,
                fontSize = 16.sp,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Area: ${complaint.area}",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = complaint.status,
                    color = statusColor,
                    fontSize = 14.sp
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
