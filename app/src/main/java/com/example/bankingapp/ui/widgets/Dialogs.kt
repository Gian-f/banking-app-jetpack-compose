package com.example.bankingapp.ui.widgets

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankingapp.util.bitmapToUri
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowErrorSheet(
    message: String,
    onDismiss: () -> Unit,
) {
    val state = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        state.expand()
    }
    if (state.isVisible) {
        ModalBottomSheet(
            sheetState = state,
            onDismissRequest = {
                scope.launch { state.hide() }
                onDismiss.invoke()
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = message,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        onClick = {
                            scope.launch { state.hide() }
                            onDismiss.invoke()
                        }) {
                        Text(text = "OK")
                    }
                }
            }
        )
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BiometricBottomSheet(
    sheetState: SheetState,
    onAccept: () -> Unit,
    onDismiss: () -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        sheetState.expand()
    }
    if (sheetState.isVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                onDismiss.invoke()
            }) {
            Column(
                modifier = Modifier.height(230.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Você deseja ativar a autenticação biométrica?",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    onClick = {
                        onAccept.invoke()
                    }) {
                    Text(
                        text = "Sim, desejo ativar!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        onDismiss.invoke()
                    }) {
                    Text(
                        text = "Depois",
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetWithVerticalActions(
    isVisible: Boolean = false,
    onDismiss: () -> Unit,
    onImageSelected: (Uri?) -> Unit,
    context: Context,
) {

    val galleryLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            onImageSelected(uri)
        }

    val cameraLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
            onImageSelected(bitmap?.let { bitmapToUri(context, it) })
        }

    val cameraPermission =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                cameraLauncher.launch(null)
            } else {
                // Tratar o caso em que a permissão foi negada.
            }
        }

    val state = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    LaunchedEffect(isVisible) {
        if (isVisible) {
            scope.launch {
                state.show()
            }
        }
    }

    if (state.isVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch { state.hide() }
                onDismiss()
            }
        ) {
            val items = listOf(
                Icons.Outlined.PhotoLibrary to "Galeria",
                Icons.Outlined.CameraAlt to "Câmera",
            )
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Column(Modifier.navigationBarsPadding()) {
                    items.forEach { item ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(32.dp),
                            modifier = Modifier
                                .clickable {
                                    when (item.second) {
                                        "Galeria" -> galleryLauncher.launch("image/*")
                                        "Câmera" -> cameraPermission.launch(Manifest.permission.CAMERA)
                                    }
                                }
                                .fillMaxWidth()
                                .padding(horizontal = 32.dp, vertical = 18.dp),
                        ) {
                            Icon(item.first, null)
                            Text(item.second)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ConfirmDialog(
    dialogState: MutableState<Boolean> = mutableStateOf(false),
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {},
    title: String = "Atenção!",
    message: String,
    confirmButtonText: String = "Confirmar",
    cancelButtonText: String = "Cancelar"
) {
    if (dialogState.value) {
        AlertDialog(
            onDismissRequest = {
                dialogState.value = false
            },
            title = {
                Text(text = title)
            },
            text = {
                Text(
                    text = message,
                    fontSize = TextUnit(16f, TextUnitType.Sp)
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    dialogState.value = false
                    onConfirm()
                }) {
                    Text(confirmButtonText)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    dialogState.value = false
                    onCancel()
                }) {
                    Text(cancelButtonText)
                }
            }
        )
    }
}