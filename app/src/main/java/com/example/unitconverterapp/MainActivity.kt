package com.example.unitconverterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverterapp.ui.theme.UnitConverterAppTheme
import kotlin.math.roundToInt
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var isInputExpanded by remember { mutableStateOf(false) }
    var isOutputExpanded by remember { mutableStateOf(false) }
    val outputConversionFactor = remember { mutableDoubleStateOf(1.0) }
    val inputConversionFactor = remember { mutableDoubleStateOf(1.0) }
    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        color = Color.Cyan
    )

    fun convertValue() {
        val input = inputValue.toDoubleOrNull() ?: 0.0
        outputValue =
            (((input * inputConversionFactor.doubleValue * 100) / outputConversionFactor.doubleValue).roundToInt() / 100.0).toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(text = "Unit Converter", style = customTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            convertValue()
        }, label = { Text(text = "Enter a Value") })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = { isInputExpanded = !isInputExpanded }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Arrow")
                }
                DropdownMenu(
                    expanded = isInputExpanded,
                    onDismissRequest = { isInputExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        inputUnit = "Centimeters"
                        inputConversionFactor.doubleValue = 0.01
                        isInputExpanded = false
                        convertValue()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        inputUnit = "Meters"
                        inputConversionFactor.doubleValue = 1.0
                        isInputExpanded = false
                        convertValue()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        inputUnit = "Feet"
                        inputConversionFactor.doubleValue = 0.3048
                        isInputExpanded = false
                        convertValue()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {
                        inputUnit = "Millimeters"
                        inputConversionFactor.doubleValue = 0.001
                        isInputExpanded = false
                        convertValue()
                    })
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box {
                Button(onClick = { isOutputExpanded = !isOutputExpanded }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Arrow")
                }
                DropdownMenu(
                    expanded = isOutputExpanded,
                    onDismissRequest = { isOutputExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        outputUnit = "Centimeters"
                        outputConversionFactor.doubleValue = 0.01
                        isOutputExpanded = false
                        convertValue()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        outputUnit = "Meters"
                        outputConversionFactor.doubleValue = 1.0
                        isOutputExpanded = false
                        convertValue()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        outputUnit = "Feet"
                        outputConversionFactor.doubleValue = 0.3048
                        isOutputExpanded = false
                        convertValue()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {
                        outputUnit = "Millimeters"
                        outputConversionFactor.doubleValue = 0.001
                        isOutputExpanded = false
                        convertValue()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: $outputValue $outputUnit", style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterAppTheme {
        UnitConverter()
    }
}
