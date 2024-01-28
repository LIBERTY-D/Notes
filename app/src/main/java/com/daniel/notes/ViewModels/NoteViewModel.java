package com.daniel.notes.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.daniel.notes.Models.Note;
import com.daniel.notes.Repositories.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {



    private NoteRepository repository;
    private LiveData<List<Note>> notes;
    public NoteViewModel(@NonNull Application application) {
        super(application);

        repository =  new NoteRepository(application);
        notes = repository.getAllNotes();
    }
    public  void insert(Note note){
        repository.insertNote(note);
    }

    public  void update(Note note){
       repository.updateNote(note);
    }

    public  void delete(Note note){
         repository.deleteNote(note);
    }

    public  LiveData<List<Note>>getAllNotes(){
        return  notes;
    }

}
