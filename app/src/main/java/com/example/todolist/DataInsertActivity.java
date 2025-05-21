package com.example.todolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.todolist.Models.Notes;
import com.example.todolist.databinding.ActivityDataInsertBinding;

public class DataInsertActivity extends AppCompatActivity {

    ActivityDataInsertBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type = getIntent().getStringExtra("type");

        if(type.equals("update")){
            binding.addNotesBtn.setText("UPDATE");
            String title = getIntent().getStringExtra("title");
            String disp = getIntent().getStringExtra("disp");
            binding.etTitle.setText(title);
            binding.etDisp.setText(disp);

            binding.addNotesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(binding.etTitle.getText().toString().equals("")){
                        Toast.makeText(DataInsertActivity.this, "Please write title of your task", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(binding.etDisp.getText().toString().equals("")){
                        Toast.makeText(DataInsertActivity.this, "Please write description of your task", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.etTitle.getText().toString());
                    intent.putExtra("disp", binding.etDisp.getText().toString());
                    intent.putExtra("id", getIntent().getIntExtra("id", 0));
                    setResult(RESULT_OK, intent);
                    finish();

                }
            });
        }
        else {
            binding.addNotesBtn.setText("ADD");
            binding.addNotesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(binding.etTitle.getText().toString().equals("")){
                        Toast.makeText(DataInsertActivity.this, "Please write title of your task", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(binding.etDisp.getText().toString().equals("")){
                        Toast.makeText(DataInsertActivity.this, "Please write description of your task", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.etTitle.getText().toString());
                    intent.putExtra("disp", binding.etDisp.getText().toString());


                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DataInsertActivity.this, MainActivity.class));
        finish();
    }
}