package com.ylabz.goswift.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.ylabz.goswift.ui.utils.NavScreen
import com.ylabz.goswift.ui.utils.navigateTo

@Composable
fun ToGoAddTest() {
    Text("Hi")
}

@Composable
fun ToGoAdd() {
    var text by remember { mutableStateOf(TextFieldValue("text")) }

    Column() {
        Text("Add This")

        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text("Label") }
        )

        Button(
            modifier = Modifier.padding(16.dp),
            elevation = 5.dp,
            onClick = {
                navigateTo(NavScreen.Home)
            }) {
            Text(text = "Add", modifier = Modifier.padding(16.dp))
        }
    }
}

@Preview
@Composable
fun PreviewToGoAdd() {
    ToGoAdd()
}
