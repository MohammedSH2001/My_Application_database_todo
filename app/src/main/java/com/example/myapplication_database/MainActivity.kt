package com.example.myapplication_database

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.example.myapplication_database.ui.theme.MyApplication_databaseTheme

class MainActivity : ComponentActivity() {

    companion object {
        lateinit var db: PersonDatabase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        db = Room.databaseBuilder(applicationContext, PersonDatabase::class.java, "demo_db")
            .build()
        setContent {
            navgetorrPages()
        }


    }
}

@Composable
fun FirstDisp(modifier: Modifier = Modifier, navController: NavController) {
    val vm: PersonViewModel = viewModel()
    val inputname = remember { mutableStateOf("") }
    val selectedColor = remember { mutableStateOf(Color.Black) }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(value = inputname.value, onValueChange = { inputname.value = it },
            label = { Text(text = "Enter Your Name") }
        )
        Button(onClick = {
            vm.addPerson(inputname.value, id = 1,selectedColor.value.toArgb())

            navController.navigate("disply_My")


        }) {
            Text(text = "Add Person")

        }

        Row {
            Button(
                onClick = {
                    selectedColor.value = Color.Red
                    selectedColor.value.toArgb()

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedColor.value == Color.Red) Color.DarkGray else Color.Red
                )
            ) {
                Text(text = "")
            }
            Spacer(modifier = Modifier.width(22.dp))

            Button(
                onClick = {
                    selectedColor.value = Color.Blue
                    selectedColor.value.toArgb()
//                changeIcon = !changeIcon
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedColor.value == Color.Blue) Color.DarkGray else Color.Blue
                )
            ) {
                Text(text = "")
            }
            Spacer(modifier = Modifier.width(22.dp))
            Button(
                onClick = {
                    vm.personList
                    selectedColor.value.toArgb()

//                changeIcon = !changeIcon
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedColor.value == Color.Yellow) Color.DarkGray else Color.Yellow
                )
            ) {
                Text(text = "")
            }


        }


    }
//    LazyColumn(
//        modifier = Modifier,
//        horizontalAlignment = Alignment.Start,
//        verticalArrangement = Arrangement.Top
//    ) {
////        item {
////            Button(onClick = { vm.addPerson("random", 1,selectedColor.value.toArgb()) }) {
////                Text(text = "")
////            }
////        }
////        items(vm.personList) { person ->
////            Text(text = person.name, style = TextStyle(Color.Black))
////            Text(text = "${person.id}", style = TextStyle(Color.Black))
////            Button(onClick = { vm.deletePerson(person) }) {
////                Text(text = "Delete")
////            }
////
////        }
//    }

}


@Composable
fun navgetorrPages() {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = "first",
        builder = {
            composable(
                "disply_My",
                content = { disply_My(navController = navController) })
            composable("first", content = { FirstDisp(navController = navController) })


        }

    )
}




