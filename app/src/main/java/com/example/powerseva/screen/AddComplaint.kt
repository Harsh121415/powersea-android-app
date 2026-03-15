package com.example.powerseva.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.powerseva.R
import java.text.SimpleDateFormat
import java.util.*

data class Complaint(
    val area: String,
    val ward: String,
    val problemType: String,
    var supportCount: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddComplaint(navController: NavHostController) {

    var area by remember { mutableStateOf("") }
    var ward by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val problemTypes = listOf(
        "Power Cut",
        "Voltage Fluctuation",
        "Transformer Issue",
        "Wire Fault",
        "Open wire on roads",
        "Spiking from wire"
    )

    var selectedProblem by remember { mutableStateOf(problemTypes[0]) }
    var dropdownExpanded by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val dateTime = remember {
        SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
            .format(Date())
    }

    val imagePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            imageUri = it
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {

        Text(
            text = "Add Electricity Complaint",
            style = MaterialTheme.typography.headlineSmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            OutlinedTextField(
                value = area,
                onValueChange = { area = it },
                label = { Text("Area") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = ward,
                onValueChange = { ward = it },
                label = { Text("Ward") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenuBox(
                expanded = dropdownExpanded,
                onExpandedChange = { dropdownExpanded = !dropdownExpanded }
            ) {
                OutlinedTextField(
                    value = selectedProblem,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Problem Type") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(dropdownExpanded)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = dropdownExpanded,
                    onDismissRequest = { dropdownExpanded = false }
                ) {
                    problemTypes.forEach {
                        DropdownMenuItem(
                            text = { Text(it) },
                            onClick = {
                                selectedProblem = it
                                dropdownExpanded = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                maxLines = 2,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Date & Time : $dateTime",
                fontSize = 12.sp,
                color = Color.Gray,
                maxLines = 1
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF1F8E9)
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { imagePickerLauncher.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    if (imageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(imageUri),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                tint = Color(0xFF2E7D32),
                                modifier = Modifier.size(26.dp)
                            )
                            Text(
                                text = "Upload Image",
                                fontSize = 12.sp,
                                color = Color(0xFF2E7D32)
                            )
                        }
                    }
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        // After generating complaint, go back to list
                        navController.navigate("list")
                    }
                ) {
                    Text("Generate")
                }

                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        area = ""
                        ward = ""
                        description = ""
                        selectedProblem = problemTypes[0]
                        imageUri = null
                    }
                ) {
                    Text("Reset")
                }
            }
        }

        Text(
            text = "© All Rights Reserved | Bihar Government",
            fontSize = 11.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
