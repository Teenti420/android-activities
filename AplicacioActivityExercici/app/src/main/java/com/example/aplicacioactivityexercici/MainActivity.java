package com.example.aplicacioactivityexercici;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText = findViewById(R.id.editTextNumber);
//        Toast.makeText(getApplicationContext(), "MainActivity: onCreate", Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        Toast.makeText(getApplicationContext(), "MainActivity: onStart", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        Toast.makeText(getApplicationContext(), "MainActivity: onResume", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        Toast.makeText(getApplicationContext(), "MainActivity: onPause", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        Toast.makeText(getApplicationContext(), "MainActivity: onStop", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(getApplicationContext(), "MainActivity: onDestroy", Toast.LENGTH_SHORT).show();
//    }

    public void launchSecondActivity(View v) {
        // Launch a new activity
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("numero", editText.getText().toString());
        startActivity(intent);
    }


}