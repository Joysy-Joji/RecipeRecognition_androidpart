package com.example.reciperecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class resultprediction extends AppCompatActivity implements View.OnClickListener {
    TextView e1;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultprediction);
        e1=(TextView) findViewById(R.id.textView1);
        b=(Button) findViewById(R.id.button3);
        b.setOnClickListener(this);

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String res=sh.getString("ress","");

//        Toast.makeText(getApplicationContext(), ""+res, Toast.LENGTH_SHORT).show();
        e1.setText(res);
    }

    @Override
    public void onClick(View view) {
        Intent reg = new Intent(getApplicationContext(), recipename.class);
        startActivity(reg);
    }
}