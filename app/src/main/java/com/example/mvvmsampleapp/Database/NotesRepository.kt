package com.example.mvvmsampleapp.Database

import androidx.lifecycle.LiveData
import com.example.mvvmsampleapp.Models.Note

class NotesRepository(private val notesDao: NoteDao) {

     val allNotes:LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note){
        notesDao.insert(note)
    }
    suspend fun delete(note: Note){
        notesDao.delete(note)
    }
    suspend fun update(note: Note){
        notesDao.update(note.id,note.title,note.note)
    }
}