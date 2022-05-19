package com.example.reciperecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class recipename extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
String [] recipes,url,desc,ing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipename);
        listView = (ListView) findViewById(R.id.lvs);
        SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

     //   Toast.makeText(getApplicationContext(),sh.getString("mydata",""),Toast.LENGTH_LONG).show();

        try {
            JSONArray jb=new JSONArray(sh.getString("mydata",""));

            url=new String[jb.length()];
            recipes =new String[jb.length()];
            desc=new String[jb.length()];
            ing =new String[jb.length()];

            for(int i=0;i<jb.length();i++)
            {
                JSONObject u=jb.getJSONObject(i);
                recipes[i]=u.getString("recipe");
                url[i]=u.getString("url");
                ing[i]=u.getString("ingredients");
                desc[i]=u.getString("desc");

                Log.d("urllllllllll---",ing[i]);
            }
listView.setAdapter(new Custom_view_recipes(getApplicationContext(),recipes,url,ing,desc));

        } catch (JSONException e) {
            e.printStackTrace();
        }




        listView.setOnItemClickListener(this);






    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor ed = sh.edit();
        ed.putString("url", url[i]);
        ed.putString("recipes", recipes[i]);
        ed.putString("ing", ing[i]);
        ed.putString("desc", desc[i]);
        ed.commit();
        Intent reg = new Intent(getApplicationContext(), recipe.class);
        startActivity(reg);
    }
}