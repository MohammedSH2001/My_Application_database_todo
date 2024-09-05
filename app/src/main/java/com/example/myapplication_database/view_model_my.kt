package com.example.myapplication_database

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PersonViewModel : ViewModel() {

    var personList  by mutableStateOf(emptyList<PersonEntity>())


    init {
        viewModelScope.launch {
            MainActivity.db.dao().getPerson().collect {
                personList = it
            }
        }
    }


    fun addPerson(name : String,id :Int,myColo:Int){
        viewModelScope.launch {
            MainActivity.db.dao().addPerson(PersonEntity(name = name, myColo = myColo))

        }
    }



    fun deletePerson(person: PersonEntity) {
        viewModelScope.launch {
            MainActivity.db.dao().deletePerson(person)
        }
    }



}