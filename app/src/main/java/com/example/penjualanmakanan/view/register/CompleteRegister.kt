package com.example.penjualanmakanan.view.register

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.penjualanmakanan.ui.theme.secondaryColor
import com.example.penjualanmakanan.utils.InputBoxShape
import com.example.penjualanmakanan.utils.formatDate
import com.example.penjualanmakanan.utils.textFieldColor
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteRegister(modifier: Modifier = Modifier) {
    var dateOfBirth by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Laki-laki", "Perempuan")
    var birthPlace by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    val calendarState = rememberUseCaseState()

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true
        ),

        selection = CalendarSelection.Date {
            dateOfBirth = formatDate(it)

        }
    )
    Column {
        Column(modifier = Modifier.padding(20.dp)) {
            TextField(
                value = gender, onValueChange = { gender = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned {
                        textFieldSize = it.size.toSize()
                    },
                colors = TextFieldDefaults.textFieldColor(),
                shape = InputBoxShape.medium,
                singleLine = true,
                readOnly = true,
                placeholder = {
                    Text(text = "Jenis Kelamin",  color = secondaryColor)
                },

                trailingIcon = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
            ) {
                suggestions.forEachIndexed { _, item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                            )
                        },
                        onClick = {
                            gender = item
                            expanded = false
                        }
                    )
                }
            }
        }

        TextField(
            value = dateOfBirth, onValueChange = { dateOfBirth = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = TextFieldDefaults.textFieldColor(),
            shape = InputBoxShape.medium,
            singleLine = true,
            placeholder = {
                Text(text = "Tanggal Lahir",  color = secondaryColor)
            },

            trailingIcon = {
                IconButton(onClick = { calendarState.show() }) {
                    Icon(
                        imageVector = Icons.Outlined.DateRange,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            ),
        )

        TextField(
            value = birthPlace,
            onValueChange = { birthPlace = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            colors = TextFieldDefaults.textFieldColor(),
            shape = InputBoxShape.medium,
            singleLine = true,
            placeholder = {
                Text(text = "Tempat Lahir",  color = secondaryColor)
            },

            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)

        )

        TextField(
            value = address, onValueChange = { address = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp, bottom = 20.dp),
            colors = TextFieldDefaults.textFieldColor(),
            shape = InputBoxShape.medium,
            singleLine = true,
            placeholder = {
                Text(text = "Alamat Lengkap",  color = secondaryColor)
            },

            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
        )

    }
}