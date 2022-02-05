package com.vladbstrv.todolist.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vladbstrv.todolist.REPOSITORY
import com.vladbstrv.todolist.db.NoteDatabase
import com.vladbstrv.todolist.db.repository.NoteRealization
import com.vladbstrv.todolist.model.NoteModel

class StartViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDatabase() {
        val daoNote = NoteDatabase.getInstance(context).getNoteDao()
        REPOSITORY = NoteRealization(daoNote)
    }

    fun getAllNotes(): LiveData<List<NoteModel>> {
        return  REPOSITORY.allNotes
    }
}