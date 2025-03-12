package com.dportenis.dphome

import WifiOff
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState
import com.plusmobileapps.konnectivity.Konnectivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App(
    openMaps: () -> Unit
) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    MaterialTheme {

        if (showBottomSheet) {
            BottomSheet(
                scope = scope,
                sheetState = sheetState,
                onDismiss = { showBottomSheet = false },
                openMaps = { openMaps() }
            )
        }

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { showBottomSheet = true },
                    containerColor = Color.DarkGray
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                val konnectivity = remember { Konnectivity() }
                val isConnected by konnectivity.isConnectedState.collectAsState()

                val state = rememberWebViewState("https://viewer.dphome.mx/?page=1")

                if (isConnected) {
                    WebView(
                        modifier = Modifier.fillMaxSize(),
                        state = state
                    )
                } else {
                    Icon(
                        modifier = Modifier.height(120.dp).fillMaxWidth(),
                        imageVector = WifiOff,
                        contentDescription = null,
                        tint = Color.LightGray
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        text = "No hay conexión a Internet",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    scope: CoroutineScope,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    openMaps: () -> Unit
) {

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Buscar tiendas cerca de mi",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,
            )
            Row(
                modifier = Modifier.align(Alignment.End),
            ) {
                TextButton(
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.DarkGray,
                        containerColor = Color.Transparent
                    ),
                    onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onDismiss()
                            }
                        }
                    }
                ) {
                    Text("Cancelar")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray
                    ),
                    onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onDismiss()
                            }
                        }
                        openMaps()
                    }
                ) {
                    Text("Buscar")
                }
            }
        }
    }
}