package com.example.reciperecognition;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONObject;

public class viewprofile extends AppCompatActivity {
    TextView td_name,td_email,td_phone,td_house,td_post,td_city,td_pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewprofile);
        td_name=(TextView) findViewById(R.id.tv1);
        td_house=(TextView) findViewById(R.id.tv3);
        td_post=(TextView) findViewById(R.id.tv4);
        td_city=(TextView) findViewById(R.id.tv5);
        td_pin=(TextView) findViewById(R.id.tv6);

        td_email=(TextView) findViewById(R.id.tv7);
        td_phone=(TextView) findViewById(R.id.tv8);





        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":1234/user_viewprofile";

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

                                String name=jsonObj.getString("name");
                                td_name.setText(name);
                                td_name.setTextColor(Color.BLACK);


                                String email=jsonObj.getString("email");
                                td_email.setText(email);
                                td_email.setTextColor(Color.BLACK);

                                String phn=jsonObj.getString("mobile_no");
                                td_phone.setText(phn);
                                td_phone.setTextColor(Color.BLACK);

                                String house=jsonObj.getString("house_name");
                                td_house.setText(house);
                                td_house.setTextColor(Color.BLACK);


                                String pin=jsonObj.getString("pin");
                                td_pin.setText(pin);
                                td_pin.setTextColor(Color.BLACK);

                                String place=jsonObj.getString("place");
                                td_city.setText(place);
                                td_city.setTextColor(Color.BLACK);

                                String post=jsonObj.getString("post");
                                td_post.setText(post);
                                td_post.setTextColor(Color.BLACK);





                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
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
            protected java.util.Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                java.util.Map<String, String> params = new java.util.HashMap<String, String>();


                params.put("lid", sh.getString("lid", ""));

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
}