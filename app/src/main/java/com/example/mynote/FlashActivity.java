package com.example.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.mynote.databinding.ActivityFlashBinding;

public class FlashActivity extends AppCompatActivity {
    ActivityFlashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFlashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(FlashActivity.this,MainActivity.class));
                finish();

            }
        },2000);
    }
}