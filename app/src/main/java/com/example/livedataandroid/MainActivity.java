package com.example.livedataandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.livedataandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
    MainActivityViewModel viewModel;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.buttonStart.setOnClickListener(this::onClick);
        binding.buttonStop.setOnClickListener(this::onClick);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.getSeconds().observe(this, new Observer<Integer>() {
            public void onChanged(Integer integer) {
                binding.numeroTxt.setText(String.valueOf(integer));
            }
        });

        viewModel.getFinished().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean)
            {
                if(aBoolean)
                {
                    Toast.makeText(getApplicationContext(),"Finished!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void onClick(View view)
    {
        if(view == binding.buttonStart)
        {
            if(binding.editTextTime.getText().length() < 4)
            {
                Toast.makeText(getApplicationContext(),"Wrong number", Toast.LENGTH_SHORT).show();
            }
            else
            {
                viewModel.timerValue.setValue(Long.parseLong(String.valueOf(binding.editTextTime.getText())));
                viewModel.startTimer();
            }
        }
        if(view == binding.buttonStop)
        {
            binding.numeroTxt.setText("0");
            viewModel.stopTimer();
        }
    }
}