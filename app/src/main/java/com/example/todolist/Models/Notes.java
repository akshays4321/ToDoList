package com.example.todolist.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "my_notes")
public class Notes {
    String title, disp;

    @PrimaryKey(autoGenerate = true)
    int id ;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Notes(String title, String disp) {
        this.title = title;
        this.disp = disp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisp() {
        return disp;
    }

    public void setDisp(String disp) {
        this.disp = disp;
    }


}
