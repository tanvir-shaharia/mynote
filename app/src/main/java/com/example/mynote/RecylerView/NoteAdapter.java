package com.example.mynote.RecylerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynote.R;
import com.example.mynote.Room.Note;
import com.example.mynote.Room.NoteDatabase;
import com.example.mynote.ViewActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.Note_Viewholder> {

    Context context;
    List<Note> noteList;

    public NoteAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public Note_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Note_Viewholder(LayoutInflater.from(context).inflate(R.layout.note_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Note_Viewholder holder, int position) {
        Note note=noteList.get(position);

        Date date =new Date(note.getDate());
        @SuppressLint("SimpleDateFormat") DateFormat dt =new SimpleDateFormat("dd/MM/yyyy hh:mm a ");


        holder.title.setText(note.getTitle());
        holder.dis.setText(note.getDescription());
        holder.date_text.setText(dt.format(date));
        holder.delete.setImageResource(R.drawable.baseline_delete_24);

        holder.delete.setOnClickListener(view -> {
             deletealert(context,note);

        });

        holder.itemView.setOnClickListener(view -> {

            Intent intent=new Intent(context, ViewActivity.class);
            intent.putExtra("dt",note.getDate());
            intent.putExtra("id",note.getID());
            intent.putExtra("title",note.getTitle());
            intent.putExtra("dis",note.getDescription());
            context.startActivity(intent);

        });

    }

    private void deletealert(Context context, Note note) {

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Delete")
                .setIcon(R.drawable.baseline_delete_24)
                .setMessage("Are you sure to Delete?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NoteDatabase.getInstance(context).getDao().delete(note);
                        Intent intent=new Intent(context,context.getClass());
                        context.startActivity(intent);

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create().show();

    }


    @Override
    public int getItemCount() {
        return noteList.size();
    }


    class Note_Viewholder extends RecyclerView.ViewHolder {

        public TextView title,dis,date_text;
        public ImageView delete;

        public Note_Viewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title2);
            dis=itemView.findViewById(R.id.dis1);
            date_text=itemView.findViewById(R.id.date_text);
            delete=itemView.findViewById(R.id.delete);
        }
    }
}
