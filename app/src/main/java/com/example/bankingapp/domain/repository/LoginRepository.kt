package com.example.bankingapp.domain.repository

import com.example.bankingapp.data.remote.dto.request.LoginRequest
import com.example.bankingapp.data.remote.dto.response.GenericResponse

class LoginRepository : BaseRepository() {

    suspend fun authenticate(loginRequest: LoginRequest) {

    }
}