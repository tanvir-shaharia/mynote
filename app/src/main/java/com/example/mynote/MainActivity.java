package com.example.mynote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mynote.RecylerView.NoteAdapter;
import com.example.mynote.Room.DAO;
import com.example.mynote.Room.Note;
import com.example.mynote.Room.NoteDatabase;
import com.example.mynote.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    NoteDatabase noteDatabase;

    ActivityMainBinding binding;

    NoteAdapter adapter;


    List<Note> noteList;
    Note note;
    long date=System.currentTimeMillis();

    @Override
    protected void onStart() {
        super.onStart();

            binding.save.setOnClickListener(view -> {
                String title= binding.title.getText().toString();
                String desc= binding.descriotion.getText().toString();
                note=new Note(0,title,desc,date);
                noteDatabase.getDao().insert(note);
                noteList.add(note);
                binding.Recychlar.setAdapter(adapter);
                binding.title.setText("");
                binding.descriotion.setText("");
                Toast.makeText(this, "Sucess", Toast.LENGTH_SHORT).show();
                binding.linerlayout1.setVisibility(View.GONE);
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noteDatabase=NoteDatabase.getInstance(MainActivity.this);
        DAO userdao =noteDatabase.getDao();


        noteList=new ArrayList<>();
        noteList=NoteDatabase.getInstance(MainActivity.this).getDao().getall();
        adapter=new NoteAdapter(MainActivity.this,noteList);
        binding.Recychlar.setAdapter(adapter);



        binding.button.setOnClickListener(view -> {
            binding.linerlayout1.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void onBackPressed() {
        binding.linerlayout1.setVisibility(View.GONE);
        showAlert();
    }

    private void showAlert() {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Exit");
        builder.setIcon(R.drawable.baseline_exit_to_app_24);
        builder.setMessage("Are you sure to Exit");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();


    }
}