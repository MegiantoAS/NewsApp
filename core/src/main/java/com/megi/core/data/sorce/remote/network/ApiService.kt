package com.megi.core.data.sorce.remote.network

import com.megi.core.data.sorce.remote.response.ResponseNews
import retrofit2.http.GET

interface ApiService {
    @GET("everything?q=bitcoin&apiKey=32f4ee585163476699d2b719cd0b5dcc")
    suspend fun getList(): ResponseNews
}