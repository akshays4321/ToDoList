package com.example.todolist.APIs;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todolist.Models.Notes;

import java.util.List;

public class NotesRepo {

    public NoteDao noteDao;
    private LiveData<List<Notes>> noteList;

    public NotesRepo(Application application){
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        noteList = noteDao.getAllData();
    }


    //Insert task

    public void insertData(Notes notes){
        new InsertTask(noteDao).execute(notes);
    }

    public void updateData(Notes notes){
        new UpdateTask(noteDao).execute(notes);
    }

    public void deleteData(Notes notes){
        new DeleteTask(noteDao).execute(notes);
    }

    public LiveData<List<Notes>> getAllData(){
        return noteList;
    }



    private static class InsertTask extends AsyncTask<Notes, Void, Void>{
        private NoteDao noteDao;

        public InsertTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
    private static class UpdateTask extends AsyncTask<Notes, Void, Void>{
        private NoteDao noteDao;

        public UpdateTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
    private static class DeleteTask extends AsyncTask<Notes, Void, Void>{
        private NoteDao noteDao;

        public DeleteTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }


}
