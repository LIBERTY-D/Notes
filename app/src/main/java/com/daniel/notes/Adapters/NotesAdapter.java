package com.daniel.notes.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daniel.notes.Events.OnItemListener;
import com.daniel.notes.Models.Note;
import com.daniel.notes.R;
import java.util.ArrayList;
import java.util.List;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder>{

    List<Note> notes = new ArrayList<>();
    private OnItemListener onItemListener;

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note  note=  notes.get(position);
        holder.title.setText(note.getTitle());
        holder.desc.setText(note.getDescription());
        holder.date.setText(note.getDate());

    }

    public  void setOnItemListener(OnItemListener listener){
        this.onItemListener =listener;
    }
    @Override
    public int getItemCount() {
        return notes.size();

    }

    public Note getNotes(int pos){
        return  notes.get(pos);
    }

    public  void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }
    public class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView title,desc,date;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int noPosition = RecyclerView.NO_POSITION;
                    if(onItemListener !=null && getAdapterPosition()!= noPosition){
                        onItemListener.onItemClick(notes.get(getAdapterPosition()));
                    }
                }
            });
        }

    }

}
