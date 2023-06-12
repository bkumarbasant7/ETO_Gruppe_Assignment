package com.manage.eto_assignment.data.dao

import com.manage.eto_assignment.BuildConfig
import com.manage.eto_assignment.data.response.TaskResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface RemoteDao {
    companion object {
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(
                OkHttpClient.Builder().build()
            )
            .addConverterFactory(GsonConverterFactory.create()) // Replace with the appropriate converter for your data format
            .build()
        val client = retrofit.create<RemoteDao>()
    }

    @GET("/todos")
    suspend fun loadTask() : Response<TaskResponse>
}