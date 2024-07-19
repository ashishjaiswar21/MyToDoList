package com.example.mytodolist.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mytodolist.Dao.NotesDao
import com.example.mytodolist.Model.Notes
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

// Data base creation implementation
@Database(entities = [Notes::class], version = 1, exportSchema = false)
// Define an abstract class NotesDatabase that extends RoomDatabase
abstract class NotesDatabase : RoomDatabase() {
    // Abstract function to get the DAO (Data Access Object) for notes
    abstract fun myNotesDao(): NotesDao

    // Companion object to create a singleton instance of NotesDatabase
    companion object{
        // Volatile variable to hold the singleton instance
        @Volatile
        var INSTANCE:NotesDatabase? =null
        // Function to get the singleton instance of the database
        @OptIn(InternalCoroutinesApi::class)
        fun getDatabaseInstance(context:Context):NotesDatabase{
            // Temporary variable to hold the current instance
            val tempInstance = INSTANCE
            // If the instance is not null, return it
            if (tempInstance != null) {
                return tempInstance
            }
            // Synchronized block to ensure only one instance is created
            synchronized(this){
                // Create a new instance of the Room database
                val roomDatabaseInstance = Room.databaseBuilder(context,NotesDatabase::class.java,"Notes").allowMainThreadQueries().build()
                // Assign the new instance to the INSTANCE variable
                INSTANCE = roomDatabaseInstance
                // Return the new instance
                return roomDatabaseInstance
            }
        }
    }
}