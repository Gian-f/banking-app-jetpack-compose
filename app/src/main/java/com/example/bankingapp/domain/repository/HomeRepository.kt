package com.example.bankingapp.domain.repository

import io.paperdb.Paper

class HomeRepository : BaseRepository() {

    suspend fun acceptBiometricAuth(): Result<Unit> {
        return safeDatabaseCall {
            Paper.book().write("acceptBiometric", true)
        }
    }

    suspend fun lastShownBiometricAuth(): Result<Unit> {
        return safeDatabaseCall {
            Paper.book().write("lastShown", System.currentTimeMillis())
        }
    }
}