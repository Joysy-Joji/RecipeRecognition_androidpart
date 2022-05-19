package com.example.reciperecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

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

public class product_store_view extends AppCompatActivity implements TextWatcher {
    ListView l1;
    EditText e1;

    String[] pname, sname, pprice, splace,imgg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_store_view);
        l1 = (ListView) findViewById(R.id.lvs);
        e1 = (EditText) findViewById(R.id.editTextTextPersonName2);
        e1.addTextChangedListener(this);


//        e2 = (EditText) findViewById(R.id.editTextTextPersonName3);

//    }

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":1234/product_store";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            JSONObject jsonObj = new JSONObject(response);
                            String status = jsonObj.getString("status");
                            if (status.equalsIgnoreCase("ok")) {
                                JSONArray js= jsonObj.getJSONArray("data");

                                pname=new String[js.length()];
                                sname=new String[js.length()];
                                pprice=new String[js.length()];
                                splace =new String[js.length()];
                                imgg =new String[js.length()];



                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    pname[i]=u.getString("p_name");
                                    sname[i]=u.getString("s_name");
                                    pprice[i]=u.getString("price");
                                    splace[i]=u.getString("place");
                                    imgg[i]=u.getString("p_image");
//                                    Toast.makeText(getApplicationContext(), "img"+imgg[i], Toast.LENGTH_SHORT).show();
                                }
                                l1.setAdapter(new custom_product_view(getApplicationContext(),pname,pprice,sname,splace,imgg));

                            } else {
                                Toast.makeText(getApplicationContext(),  "No values", Toast.LENGTH_LONG);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error"+e.getMessage(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
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

                return params;
            }
        };


        requestQueue.add(postRequest);




    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":1234/search_product";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            JSONObject jsonObj = new JSONObject(response);
                            String status = jsonObj.getString("status");
                            if (status.equalsIgnoreCase("ok")) {
                                JSONArray js= jsonObj.getJSONArray("data");

                                pname=new String[js.length()];
                                sname=new String[js.length()];
                                pprice=new String[js.length()];
                                splace =new String[js.length()];
                                imgg =new String[js.length()];



                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    pname[i]=u.getString("p_name");
                                    sname[i]=u.getString("s_name");
                                    pprice[i]=u.getString("price");
                                    splace[i]=u.getString("place");
                                    imgg[i]=u.getString("p_image");
//                                    Toast.makeText(getApplicationContext(), "img"+imgg[i], Toast.LENGTH_SHORT).show();
                                }
                                l1.setAdapter(new custom_product_view(getApplicationContext(),pname,pprice,sname,splace,imgg));

                            } else {
                                Toast.makeText(getApplicationContext(),  "No values", Toast.LENGTH_LONG);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error"+e.getMessage(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
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
                params.put("product",e1.getText().toString());
                return params;
            }
        };


        requestQueue.add(postRequest);





    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}