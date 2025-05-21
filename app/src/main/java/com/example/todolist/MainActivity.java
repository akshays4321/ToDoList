package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.todolist.APIs.ViewModel;
import com.example.todolist.Adapters.NotesAdapter;
import com.example.todolist.Models.Notes;
import com.example.todolist.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ViewModel viewModel;
    NotesAdapter notesAdapter = new NotesAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ViewModel.class);

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DataInsertActivity.class);
                intent.putExtra("type", "add");
                startActivityForResult(intent, 1);
            }
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(notesAdapter);

        viewModel.getAllNotes().observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                notesAdapter.submitList(notes);
            }
        });
        
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction==ItemTouchHelper.RIGHT){
                    viewModel.delete(notesAdapter.getNotes(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                }else if(direction==ItemTouchHelper.LEFT){
                    Intent intent = new Intent(MainActivity.this, DataInsertActivity.class);
                    intent.putExtra("title", notesAdapter.getNotes(viewHolder.getAdapterPosition()).getTitle().toString());
                    intent.putExtra("disp", notesAdapter.getNotes(viewHolder.getAdapterPosition()).getDisp().toString());
                    intent.putExtra("id", notesAdapter.getNotes(viewHolder.getAdapterPosition()).getId());
                    intent.putExtra("type", "update");
                    startActivityForResult(intent, 2);
                }
            }
        }).attachToRecyclerView(binding.recyclerView);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            String title = data.getStringExtra("title");
            String disp = data.getStringExtra("disp");

            Notes notes = new Notes(title, disp);
            viewModel.insert(notes);
            Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show();

        }else if(requestCode==2){

            String title = data.getStringExtra("title");
            String disp = data.getStringExtra("disp");

            Notes notes = new Notes(title, disp);
            notes.setId(data.getIntExtra("id", 0));
            viewModel.update(notes);
            Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show();
            
        }
    }
}