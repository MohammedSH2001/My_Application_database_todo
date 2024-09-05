package com.example.myapplication_database

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity
data class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    var  id: Int = 0,
    var  name: String,
var myColo:Int
) {
}


@Dao
interface PersonDao {

    @Insert
    suspend fun addPerson(person: PersonEntity)

    @Query("select * from PersonEntity")
    fun getPerson(): Flow<List<PersonEntity>>

    @Delete
    suspend fun deletePerson(person: PersonEntity)
}


@Database(
    entities = [PersonEntity::class],
    version = 2
)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun dao(): PersonDao
}


