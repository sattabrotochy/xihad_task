package com.example.xihad_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    TextView textView1,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        UserDataModel user = (UserDataModel) intent.getSerializableExtra("selected_user");


        textView1=findViewById(R.id.nameTextView1);
        textView2=findViewById(R.id.emailTextView2);
        textView1.setText(user.getTitle());
        textView2.setText(user.getBody());

    }
}