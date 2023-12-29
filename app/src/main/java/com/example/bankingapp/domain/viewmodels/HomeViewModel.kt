package com.example.bankingapp.domain.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankingapp.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.paperdb.Paper
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
) : ViewModel() {
    private val TAG = HomeViewModel::class.simpleName

    private val acceptedBiometry: Boolean? = Paper.book().read("acceptBiometric")
    val biometricAccepted = MutableStateFlow(acceptedBiometry)
    val lastDialogShow: Long? = Paper.book().read("lastShown")
    val lastShown = MutableStateFlow(lastDialogShow)

    fun acceptBiometricAuth() {
        if (acceptedBiometry == null) {
            viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
                Log.e(TAG, "$throwable")
            }) {
                repository.acceptBiometricAuth()
            }
        }
    }

    fun registerLastShown() {
        if (lastDialogShow == null) {
            viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
                Log.e(TAG, "$throwable")
            }) {
                repository.lastShownBiometricAuth()
            }
        }
    }
}