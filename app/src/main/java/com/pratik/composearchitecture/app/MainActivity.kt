package com.pratik.composearchitecture.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pratik.composearchitecture.core.ui.theme.ComposeArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main entry point for the application.
 *
 * This activity sets up the Compose theme and hosts the main application composable.
 *
 * @author Pratik Behera
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeArchitectureTheme {
                ComposeArchitectureRoot()
            }
        }
    }
}
