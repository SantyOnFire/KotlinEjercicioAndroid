package com.example.personalizatexto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.personalizatexto.ui.theme.PersonalizatextoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PersonalizatextoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TextCustomizer()
                }
            }
        }
    }
}

@Composable
fun TextCustomizer() {
    var inputText by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(Color.Black) }
    var selectedStyle by remember { mutableStateOf(FontWeight.Normal) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Escribe tu texto") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = inputText,
            color = selectedColor,
            fontSize = 24.sp,
            fontWeight = selectedStyle
        )

        Spacer(modifier = Modifier.height(20.dp))

        DropdownColorSelector { selectedColor = it }

        Spacer(modifier = Modifier.height(10.dp))

        DropdownStyleSelector { selectedStyle = it }
    }
}

@Composable
fun DropdownColorSelector(onColorSelected: (Color) -> Unit) {
    val colors = listOf(Color.Black, Color.Red, Color.Blue, Color.Green, Color(0xFFAB5151))
    val colorNames = listOf("Negro", "Rojo", "Azul", "Verde", "Rosa")

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("Seleccionar color") }

    Box {
        Button(onClick = { expanded = true }) {
            Text(selectedText)
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            colors.forEachIndexed { index, color ->
                DropdownMenuItem(
                    text = { Text(colorNames[index]) },
                    onClick = {
                        onColorSelected(color)
                        selectedText = colorNames[index]
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun DropdownStyleSelector(onStyleSelected: (FontWeight) -> Unit) {
    val styles = listOf(FontWeight.Normal, FontWeight.Bold, FontWeight.Light, FontWeight.ExtraBold)
    val styleNames = listOf("Normal", "Negrita", "Ligero", "Extra Negrita")

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("Seleccionar estilo") }

    Box {
        Button(onClick = { expanded = true }) {
            Text(selectedText)
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            styles.forEachIndexed { index, style ->
                DropdownMenuItem(
                    text = { Text(styleNames[index]) },
                    onClick = {
                        onStyleSelected(style)
                        selectedText = styleNames[index]
                        expanded = false
                    }
                )
            }
        }
    }
}

