package com.example.todolist.APIs;

import android.content.Context;

import androidx.core.util.Pools;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.todolist.Models.Notes;

@Database(entities = Notes.class, version = 1)
public abstract class NoteDatabase extends RoomDatabase {


    public abstract NoteDao noteDao();
    private static NoteDatabase instance ;

    public static synchronized NoteDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
