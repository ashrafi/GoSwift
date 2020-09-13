package com.ylabz.goswift.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Switch
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import com.ylabz.goswift.model.GoToEvntViewModel
import com.ylabz.goswift.model.togo.GoToEvntDB.GoToEnvt
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter


@Composable
fun GoToEvtAdd() {
    var checked by remember { mutableStateOf(false) }

    Column {
        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
            }
        )
        if (checked) {
            var goToEvntText by remember { mutableStateOf(TextFieldValue("text")) }
            val toGoViewModel = viewModel<GoToEvntViewModel>()

            Column {
                Text("Add This")

                OutlinedTextField(
                    value = goToEvntText,
                    onValueChange = {
                        goToEvntText = it
                    },
                    label = { Text("Label") }
                )

                Row {
                    Button(
                        modifier = Modifier.padding(16.dp),
                        shape = MaterialTheme.shapes.small,
                        elevation = 5.dp,
                        onClick = {
                            checked = false
                            toGoViewModel.addToGoItem(GoToEnvt(goToEvntText.text, 0.0, 0.0, OffsetDateTime.now(), "Img"))
                        }) {
                        Text(text = "Add", modifier = Modifier.padding(16.dp))
                    }
                    Button(
                        modifier = Modifier.padding(16.dp),
                        elevation = 5.dp,
                        onClick = {
                            checked = false
                            toGoViewModel.deleteAll()
                        }) {
                        Text(text = "Delete All", modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun GoToEvtList() {
    val goToEvntViewModel = viewModel<GoToEvntViewModel>()
    val goToEvntList = goToEvntViewModel.getToGoInfo().collectAsState(initial = emptyList()).value
    Column {
        LazyColumnFor(items = goToEvntList, itemContent = { goTos ->
            Column() {
                Text(goTos.goToName)
                Text(goTos.date.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss Z")))
            }
        })
    }
}

@Preview
@Composable
fun PreviewToGoAdd() {
    GoToEvtList()
}
