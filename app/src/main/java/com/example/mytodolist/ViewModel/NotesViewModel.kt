// Package declaration
package com.example.mytodolist.ViewModel

// Import necessary Android and Jetpack components
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mytodolist.Database.NotesDatabase
import com.example.mytodolist.Model.Notes
import com.example.mytodolist.Repository.NotesRepository


// view model extrcat data from repository and sending it to  activity
// Define the NotesViewModel class that extends AndroidViewModel
class NotesViewModel(application: Application) : AndroidViewModel(application) {
    // Declare a repository variable of type NotesRepository
    private val repository: NotesRepository

    // Init block to initialize the repository
    init {
        // Get the DAO instance from the NotesDatabase singleton
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        // Initialize the repository with the DAO
        repository = NotesRepository(dao)
    }

    // Function to add notes by calling the repository's insertNotes method
    fun addNotes(notes: Notes) {
        repository.insertNotes(notes)
    }

    // Function to get all notes as LiveData by calling the repository's getAllNotes method
    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()

    fun getLowNotes(): LiveData<List<Notes>> = repository.getLowNotes()
    fun getHighNotes(): LiveData<List<Notes>> = repository.getHighNotes()
    fun getMediumNotes(): LiveData<List<Notes>> = repository.getMediumNotes()

    // Function to delete a note by ID by calling the repository's deleteNotes method
    fun deleteNotes(id:Int) {
        repository.deleteNotes(id)
    }

    // Function to update a note by calling the repository's updateNotes method
    fun updateNotes(notes: Notes) {
        repository.updateNotes(notes)
    }
}
