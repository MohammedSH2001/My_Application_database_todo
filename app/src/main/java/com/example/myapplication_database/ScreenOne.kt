package com.example.myapplication_database

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun disply_My(navController: NavController, vm: PersonViewModel = viewModel()) {
    val personList = vm.personList

    Box(modifier = Modifier.height(900.dp)) {
        LazyColumn(
            modifier = Modifier.padding(top = 60.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            items(personList) { person ->
                val isChecked = remember { mutableStateOf(false) }
                val personColor = Color(person.myColo.toInt())

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Checkbox(
                            checked = isChecked.value,
                            onCheckedChange = { isChecked.value = it }
                        )

                        Button(onClick = { vm.deletePerson(person) }) {
                            Text(text = "Delete")
                        }
                        Spacer(modifier = Modifier.width(100.dp))

                        Text(text = "${person.id}", style = TextStyle(color = personColor))
                        Spacer(modifier = Modifier.width(70.dp))

                        Text(text = person.name, style = TextStyle(color =  personColor))


                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { navController.navigate("first") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Item")
        }

    }
}