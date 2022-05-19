package com.example.reciperecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button next,register;
    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        next=(Button)findViewById(R.id.save);
        next.setOnClickListener(this);
        register=(Button)findViewById(R.id.register);
        register.setOnClickListener(this);
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.Password);
        username.setText("shadi");
        password.setText("123");

    }


    @Override
    public void onClick(View view) {
        if (view == next) {
int glg=0;
            String uname=username.getText().toString();
            String passw=password.getText().toString();
//            Toast.makeText(getApplicationContext(), "ooookkkkk", Toast.LENGTH_SHORT).show();
            if(uname.length()==0) {
                username.setText("Missing");
                glg++;
            }
            if (passw.length()==0) {
                    password.setText("Missing");
                    glg++;

            }
            if
                (glg==0){
                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":1234/userlogin_post1";
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                // response
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                        SharedPreferences.Editor ed = sh.edit();
                                        ed.putString("lid", jsonObj.getString("lid"));
                                        ed.commit();

                                        Toast.makeText(getApplicationContext(), "Login Succussfully", Toast.LENGTH_SHORT).show();
                                        Intent reg = new Intent(getApplicationContext(), homepage.class);
                                        startActivity(reg);
                                         }


                                    // }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
                                    }

                                }    catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        Map<String, String> params = new HashMap<String, String>();

                        String id=sh.getString("uid","");
                        params.put("uid",id);
//                params.put("mac",maclis);
                        params.put("name",uname);
                        params.put("password",passw);

                        return params;
                    }
                };

                int MY_SOCKET_TIMEOUT_MS=100000;

                postRequest.setRetryPolicy(new DefaultRetryPolicy(
                        MY_SOCKET_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(postRequest);

            }


        } else {
            Intent reg = new Intent(getApplicationContext(), RegisterUser.class);
            startActivity(reg);
        }
    }
}