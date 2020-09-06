package com.ylabz.goswift.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import com.ylabz.goswift.model.ToGoViewModel
import com.ylabz.goswift.model.togo.ToGoDB.ToGo
import com.ylabz.goswift.ui.utils.NavScreen
import com.ylabz.goswift.ui.utils.navigateTo


@Composable
fun toGoView() {
    var checked by remember { mutableStateOf(false) }

    //Text("hi")
    Column {
        Text(
            modifier = Modifier.toggleable(value = checked, onValueChange = { checked = it }),
            text = checked.toString()
        )
        if (checked) {
            toGoAdd()
        }
        toGoList()
    }
}

@Composable
fun toGoList() {
    val toGoViewModel = viewModel<ToGoViewModel>()
    val toGoList = toGoViewModel.getToGoInfo().collectAsState(initial = emptyList()).value
    Column {
        LazyColumnFor(items = toGoList, itemContent = { toGos ->
            Text(toGos.togoName)
        })
    }

}

@Composable
fun toGoAdd() {
    var text by remember { mutableStateOf(TextFieldValue("text")) }
    val toGoViewModel = viewModel<ToGoViewModel>()

    Column {
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
                toGoViewModel.addToGoItem(ToGo("Ash1", 0.0, 0.0))
                navigateTo(NavScreen.Home)
            }) {
            Text(text = "Add", modifier = Modifier.padding(16.dp))
        }
    }
}

@Preview
@Composable
fun PreviewToGoAdd() {
    toGoView()
}
