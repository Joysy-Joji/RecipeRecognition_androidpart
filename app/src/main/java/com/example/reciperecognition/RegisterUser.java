package com.example.reciperecognition;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RegisterUser extends AppCompatActivity implements View.OnClickListener {
    EditText Name, Password1, Password2, email_id, number, User;
    TextView addressHouseName, addressCity, addressArea, addressPin;
    String name, password1, password2, mail, mnumber, username;
    EditText aHouseName, aCity, aArea, aPin;
    Button details;
    TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

//        textInputLayout = findViewById(R.id.name);
        Name = (EditText) findViewById(R.id.name);
        Password1 = (EditText) findViewById(R.id.password1);
        Password2 = (EditText) findViewById(R.id.password2);
        email_id = (EditText) findViewById(R.id.email);
        number = (EditText) findViewById(R.id.mobile_no);
        aHouseName = (EditText) findViewById(R.id.addressHouseName);
        aCity = (EditText) findViewById(R.id.addressCity);
        aArea = (EditText) findViewById(R.id.addressArea);
        aPin = (EditText) findViewById(R.id.addressPin);
        details = (Button) findViewById(R.id.detials);
        details.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {


//        String na = textInputLayout.getEditText().getText().toString();
        String na = Name.getText().toString();

            String email=email_id.getText().toString();


            String no=number.getText().toString();

            String hname=aHouseName.getText().toString();



            String city=aCity.getText().toString();

            String area=aArea.getText().toString();
            String pin= aPin.getText().toString();

        String passw=Password1.getText().toString();

                String cpassw=Password2.getText().toString();
        Toast.makeText(getApplicationContext(), "name-----" + cpassw, Toast.LENGTH_LONG).show();



        if(na.length()==0) {
                Name.setText("Missing");
            }
            else if (passw.length()==0) {
                Password1.setText("Missing");

            }
            else if (cpassw.length()==0) {
                Password2.setText("Missing");

            }
            else if (email.length()==0) {
                email_id.setText("Missing");

            }
            else if (no.length()==0) {
                number.setText("Missing");

            }

            else if (hname.length()==0) {
                aHouseName.setText("Missing");

            }
            else if (city.length()==0) {
                aCity.setText("Missing");

            }
            else if (area.length()==0) {
                aArea.setText("Missing");

            }
            else if (pin.length()==0) {
                aPin.setText("Missing");

            }

            else
                {
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":1234/userregister_post";
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
                                Toast.makeText(getApplicationContext(), "Registered Succussfully", Toast.LENGTH_SHORT).show();
                                Intent reg = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(reg);
                            }


                            // }
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
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                params.put("name",na);
                params.put("place",city);
                params.put("post",area);
                params.put("pin",pin);
                params.put("housename",hname);
                params.put("email",email);
                params.put("mobileno",no);


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

}}