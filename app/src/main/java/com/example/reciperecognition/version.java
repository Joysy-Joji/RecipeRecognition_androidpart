package com.example.reciperecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class version extends AppCompatActivity implements View.OnClickListener {

    Button save;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);


                save=(Button)findViewById(R.id.save);
                name=(EditText) findViewById(R.id.editTextT1);
                name.setText("192.168.43.139");
                save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String ip=name.getText().toString();
        if(ip.length()==0){
            name.setText("Missing");
        }
        else {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor ed = sh.edit();
            ed.putString("ip", ip);
            ed.commit();
            Intent reg = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(reg);
        }
    }
}