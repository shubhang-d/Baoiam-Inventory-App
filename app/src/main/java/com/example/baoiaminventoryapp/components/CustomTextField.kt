package com.example.baoiaminventoryapp.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun CustomTextField(label:String = "", passwordField:Boolean = false, modifier: Modifier) {
    val value = remember {
        mutableStateOf("")
    }
    val passwordVisibleStatus = remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value.value,
        label = { Text(text = label)},
        onValueChange = {value.value = it},
        modifier = modifier,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (passwordField) KeyboardType.Password else KeyboardType.Text,
            imeAction = ImeAction.Next,
        ),
        shape = RoundedCornerShape(20.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.LightGray,
            unfocusedContainerColor = Color.LightGray,
            disabledContainerColor = Color.LightGray,
            errorContainerColor = Color.Transparent,
            focusedBorderColor = Color.LightGray,
            unfocusedBorderColor = Color.LightGray,
            unfocusedLabelColor = Color.DarkGray,
            focusedLabelColor = Color.White,
        ),
        visualTransformation = if (passwordField && !passwordVisibleStatus.value) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        trailingIcon = {
            if (passwordField) {
                val image = if (passwordVisibleStatus.value) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                val description = if (passwordVisibleStatus.value) {
                    "Hide Password"
                } else {
                    "Show Password"
                }
                IconButton(onClick = {
                    passwordVisibleStatus.value = !passwordVisibleStatus.value
                }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            }
        }
    )
}