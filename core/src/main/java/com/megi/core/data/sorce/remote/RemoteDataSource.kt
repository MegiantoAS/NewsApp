package com.megi.core.data.sorce.remote

import android.util.Log
import com.megi.core.data.sorce.remote.network.ApiResponse
import com.megi.core.data.sorce.remote.network.ApiService
import com.megi.core.data.sorce.remote.response.ArticlesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource (private val apiService: ApiService) {

    suspend fun getListNews(): Flow<ApiResponse<List<ArticlesItem>>> {
        return flow {
            try {
                val response = apiService.getList()
                val listData = response.articles
                if (listData.isNotEmpty()){
                    emit(ApiResponse.Success(response.articles))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("DataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}