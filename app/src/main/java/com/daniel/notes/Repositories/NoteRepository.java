package com.daniel.notes.Repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.daniel.notes.DAO.NoteDAO;
import com.daniel.notes.Models.Note;
import com.daniel.notes.Storage.NoteDataBase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    private NoteDAO noteDAO;

    private LiveData<List<Note>> notes;


    public  NoteRepository(Application application){
        NoteDataBase  noteDataBase =  NoteDataBase.getInstance(application);
        noteDAO = noteDataBase.noteDAO();
        notes = noteDAO.getAllNotes();

    }

    public  void insertNote(Note note){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                noteDAO.insert(note);
            }
        });
    }

    public  void updateNote(Note note){
       executorService.execute(new Runnable() {
        @Override
        public void run() {
                noteDAO.update(note);
        }
    });
    }

    public  void deleteNote(Note note){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                noteDAO.delete(note);
            }
        });
    }

    public  LiveData<List<Note>>getAllNotes(){
        return  notes;
    }



}


