package com.daniel.notes.Storage;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.daniel.notes.DAO.NoteDAO;
import com.daniel.notes.Models.Note;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Note.class},version = 1)
public abstract  class NoteDataBase extends RoomDatabase {

    private  static NoteDataBase instance;

    public  abstract NoteDAO noteDAO();


    public  static  synchronized  NoteDataBase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDataBase.class,"note_database").
                    addCallback(roomCallBack).
                    fallbackToDestructiveMigration().
                    build();
        }

        return  instance;
    }

    public  static  RoomDatabase.Callback roomCallBack = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            NoteDAO noteDAO = instance.noteDAO();
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    noteDAO.insert(new Note("Title 1","desc one","2024-12-01"));
                    noteDAO.insert(new Note("Title 2","desc one","2024-12-01"));
                    noteDAO.insert(new Note("Title 3","desc one","2024-12-01"));
                    noteDAO.insert(new Note("Title 4","desc one","2024-12-01"));
                }
            });
        }
    };



//    private static  populateDatabase

}
