package com.example.todolist.APIs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todolist.Models.Notes;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private NotesRepo notesRepo;
    private LiveData<List<Notes>> noteList;


    public ViewModel(@NonNull Application application) {
        super(application);
        notesRepo = new NotesRepo(application);
        noteList = notesRepo.getAllData();
    }

    public void insert(Notes notes){
        notesRepo.insertData(notes);
    }
    public void update(Notes notes){
        notesRepo.updateData(notes);
    }
    public void delete(Notes notes){
        notesRepo.deleteData(notes);
    }
    public LiveData<List<Notes>> getAllNotes(){
        return noteList;
    }
}
