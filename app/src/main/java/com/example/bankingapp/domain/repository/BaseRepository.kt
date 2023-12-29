package com.example.bankingapp.domain.repository

import android.util.Log
import retrofit2.Response

abstract class BaseRepository {
    suspend fun <T> safeApiCall(call: suspend () -> Response<T>): Result<T> {
        return runCatching {
            val response = call.invoke()
            if (response.isSuccessful) {
                response.body() ?: throw Exception("resposta vazia!")
            } else {
                throw Exception("Chamada de API falhou: ${response.errorBody()?.string()}")
            }
        }.onFailure {
            Log.e("SafeApiCall", "Serviço fora do ar: ${it.message}")
        }
    }

    suspend fun <T> safeDatabaseCall(call: suspend () -> T): Result<T> {
        return runCatching {
            call.invoke()
        }.onFailure {
            Log.e("DatabaseCall", "Falha ao buscar dados localmente: ${it.message}")
        }
    }
}