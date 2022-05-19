package com.example.reciperecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.nio.channels.Pipe;

public class recipe extends AppCompatActivity {


    TextView t1,t2,t3;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        t1=(TextView) findViewById(R.id.tv1);
        t2=(TextView) findViewById(R.id.tv2);
        t3=(TextView) findViewById(R.id.tv3);
        img=(ImageView)findViewById(R.id.img1);

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        t1.setText(sh.getString("recipes",""));
        t2.setText(sh.getString("ing",""));
        t3.setText(sh.getString("desc",""));

        Picasso.with(getApplicationContext()).load(sh.getString("url","")).into(img);

    }
}