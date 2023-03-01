package com.example.mynote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mynote.Room.DAO;
import com.example.mynote.Room.Note;
import com.example.mynote.Room.NoteDatabase;
import com.example.mynote.databinding.ActivityViewBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ViewActivity extends AppCompatActivity {
    ActivityViewBinding binding;
    Intent intent;
    NoteDatabase noteDatabase;
    long dt;
    DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        intent=getIntent();
        String title= intent.getStringExtra("title");
        String dis=intent.getStringExtra("dis");
        dt=intent.getLongExtra("dt",01122222);
        Date date =new Date(dt);
        @SuppressLint("SimpleDateFormat") DateFormat dt =new SimpleDateFormat("dd/MM/yyyy hh:mm a ");
        binding.dateTextview.setText(dt.format(date));

        binding.titleTextview.setText(title);
        binding.disTextview.setText(dis);
        noteDatabase =NoteDatabase.getInstance(ViewActivity.this);
        dao=noteDatabase.getDao();
        binding.editFlot.setOnClickListener(view -> {
            binding.liniyerEdit.setVisibility(View.VISIBLE);
            binding.editT.setText(title);
            binding.editDisc.setText(dis);

        });

    }

    @Override
    protected void onStart() {
        binding.saveUp.setOnClickListener(view -> {
           String t=binding.editT.getText().toString();
           String d = binding.editDisc.getText().toString();
            int id=intent.getIntExtra("id",1);
            Note note=new Note(id,t,d,dt);
            NoteDatabase.getInstance(ViewActivity.this).getDao().updateNote(note);
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
            binding.titleTextview.setText(t);
            binding.disTextview.setText(d);
            binding.liniyerEdit.setVisibility(View.GONE);
            startActivity(new Intent(ViewActivity.this,MainActivity.class));

        });

        super.onStart();
    }
}