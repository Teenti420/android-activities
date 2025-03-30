package com.example.aplicacioactivityexercici;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    private String numero;
    private TextView tv;

    ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == RESULT_OK) {
                                numero = result.getData().getStringExtra("numero");
                                tv.setText(numero);
                            }
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        numero = getIntent().getStringExtra("numero");

        tv = findViewById(R.id.textView);
        tv.setText(numero);

//        Toast.makeText(getApplicationContext(), "SecondActivity: onCreate", Toast.LENGTH_SHORT).show();
    }

    public void launchThirdActivity(View v) {
        Intent i = new Intent(this, ThirdActivity.class);
        i.putExtra("numero", this.numero);
        activityResultLauncher.launch(i);
    }

    public void goBackToMain(View v) {
        finish();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        Toast.makeText(getApplicationContext(), "SecondActivity: onStart", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        Toast.makeText(getApplicationContext(), "SecondActivity: onResume", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        Toast.makeText(getApplicationContext(), "SecondActivity: onPause", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        Toast.makeText(getApplicationContext(), "SecondActivity: onStop", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(getApplicationContext(), "SecondActivity: onDestroy", Toast.LENGTH_SHORT).show();
//    }
}