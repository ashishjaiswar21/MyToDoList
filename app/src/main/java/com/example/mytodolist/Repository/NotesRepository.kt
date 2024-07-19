package com.example.mytodolist.Repository

import androidx.lifecycle.LiveData
import com.example.mytodolist.Dao.NotesDao
import com.example.mytodolist.Model.Notes

// Dao aur repositiry ke becech connection
// reposity dao ka functionality lega aur aage viewmodel ko bhejega

class NotesRepository(val dao:NotesDao) {

    // Function to get all notes from the database
    fun getAllNotes(): LiveData<List<Notes>> {
        // Return the list of notes wrapped in LiveData from DAO
        return dao.getNotes()
    }

    fun getLowNotes(): LiveData<List<Notes>> = dao.getLowNotes()
    fun getHighNotes(): LiveData<List<Notes>> = dao.getHighNotes()
    fun getMediumNotes(): LiveData<List<Notes>> = dao.getMediumNotes()


    // Function to insert a note into the database
    fun insertNotes(notes: Notes) {
        // Call the insertNotes method from DAO to insert a note
        dao.insertNotes(notes)
    }

    // Function to delete a note from the database by its ID
    fun deleteNotes(id: Int) {
        // Call the deleteNotes method from DAO to delete a note by ID
        dao.deleteNotes(id)
    }

    // Function to update an existing note in the database
    fun updateNotes(notes: Notes) {
        // Call the updateNotes method from DAO to update the note
        dao.updateNotes(notes)
    }
}