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

public class ThirdActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText = findViewById(R.id.editTextNumber2);
        editText.setText(getIntent().getStringExtra("numero"));

//        Toast.makeText(getApplicationContext(), "SecondActivity: onCreate", Toast.LENGTH_SHORT).show();
    }

    public void goBackToSecond(View v) {
        Intent i = new Intent();
        i.putExtra("numero", this.editText.getText().toString());
        setResult(ThirdActivity.RESULT_OK, i);

        finish();
    }

    //    @Override
//    public void onStart() {
//        super.onStart();
//        Toast.makeText(getApplicationContext(), "ThirdActivity: onStart", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        Toast.makeText(getApplicationContext(), "ThirdActivity: onResume", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        Toast.makeText(getApplicationContext(), "ThirdActivity: onPause", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        Toast.makeText(getApplicationContext(), "ThirdActivity: onStop", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(getApplicationContext(), "ThirdActivity: onDestroy", Toast.LENGTH_SHORT).show();
//    }
}