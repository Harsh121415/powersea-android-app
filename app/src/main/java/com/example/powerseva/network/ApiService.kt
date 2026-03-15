package com.example.powerseva.network

import com.example.powerseva.model.Complaint
import com.example.powerseva.model.User
import com.example.powerseva.model.LoginResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

interface ApiService {

    @GET("api/complaints")
    fun getComplaints(): Call<List<Complaint>>

    @POST("api/complaints")
    fun createComplaint(@Body complaint: Complaint): Call<Complaint>

    @POST("api/auth/login")
    fun login(@Body user: User): Call<LoginResponse>
}
