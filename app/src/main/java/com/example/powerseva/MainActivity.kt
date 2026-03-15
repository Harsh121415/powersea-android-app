package com.example.powerseva

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.powerseva.model.Complaint
import com.example.powerseva.network.RetrofitClient
import com.example.powerseva.screen.*
import com.example.powerseva.ui.theme.PowerSevaTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PowerSevaTheme {

                // 🔥 TEST RETROFIT CALL
                LaunchedEffect(Unit) {
                    RetrofitClient.api.getComplaints()
                        .enqueue(object : Callback<List<Complaint>> {

                            override fun onResponse(
                                call: Call<List<Complaint>>,
                                response: Response<List<Complaint>>
                            ) {
                                Log.d("API_SUCCESS", "Data: ${response.body()}")
                            }

                            override fun onFailure(
                                call: Call<List<Complaint>>,
                                t: Throwable
                            ) {
                                Log.e("API_ERROR", "Error: ${t.message}")
                            }
                        })
                }

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ) {

                    composable("splash") {
                        SplashScreen(navController)
                    }

                    composable("Register") {
                        RegistrationPage(navController)
                    }

                    composable("login") {
                        LoginScreen(navController)
                    }

                    composable("list") {
                        ComplaintListScreen(navController)
                    }

                    composable("add") {
                        AddComplaint(navController)
                    }

                    composable("detail") {
                        ComplaintDetailScreen(navController)
                    }
                }
            }
        }
    }
}