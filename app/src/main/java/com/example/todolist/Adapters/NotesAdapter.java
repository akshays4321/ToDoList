package com.example.todolist.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Models.Notes;
import com.example.todolist.R;

public class NotesAdapter extends ListAdapter<Notes, NotesAdapter.viewHolder> {



    private static final DiffUtil.ItemCallback<Notes> CALLBACK = new DiffUtil.ItemCallback<Notes>() {
        @Override
        public boolean areItemsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getDisp().equals(newItem.getDisp());
        }
    };

    public NotesAdapter() {
        super(CALLBACK);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_sample_view, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Notes notes = getItem(position);
        holder.title.setText(notes.getTitle());
        holder.disp.setText(notes.getDisp());

    }

    public Notes getNotes(int position){
        return getItem(position);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title, disp;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            disp = itemView.findViewById(R.id.disp);
        }
    }
}
