package com.example.reciperecognition;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
public class viewrecipe extends AppCompatActivity
        implements View.OnClickListener{
    ImageView img;
    Button b1,b2;
    String path, atype, fname, attach, attatch1;
    byte[] byteArray = null;
    private String encodedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewrecipe);
        img=(ImageView) findViewById(R.id.imageView2);
        b1=(Button) findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        img.setOnClickListener(this); }
        @Override
        public void onClick(View view) {
        if (view== b2){
            Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cam, 101);
        }
        else if(view==img)
        {
showfilechooser(1);
        }
        else if(view==b1)
        {
            {
                SharedPreferences sh= PreferenceManager.
                        getDefaultSharedPreferences(getApplicationContext());

                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":1234/image_post";
                RequestQueue requestQueue = Volley.newRequestQueue
                        (getApplicationContext());
                StringRequest postRequest = new StringRequest
                        (Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").
                                            equalsIgnoreCase("ok")) {
                                        Toast.makeText(getApplicationContext(),
                                                "Image uploaded", Toast.LENGTH_SHORT).show();
                                        String res=jsonObj.getString("res");
//                                        String recep=jsonObj.getString("recep");
                                        SharedPreferences sh = PreferenceManager.
                                                getDefaultSharedPreferences(getApplicationContext());
                                        SharedPreferences.Editor ed = sh.edit();
                                        ed.putString("res", res);
                                        ed.putString("ingds", jsonObj.getString("ingds"));
                                        ed.putString("ress",jsonObj.getString("ress"));
                                        ed.putString("urls", jsonObj.getString("urls"));
                                        ed.putString("descs",jsonObj.getString("descs"));
                                        ed.putString("mydata",jsonObj.getString("mydata"));
                                        ed.commit();


                                        Intent reg = new Intent(getApplicationContext(),
                                                resultprediction.class);
                                        startActivity(reg);
                                    }
//

                                    // }
                                    else {
                                        Toast.makeText(getApplicationContext(),
                                                "Not found", Toast.LENGTH_LONG).show();
                                    }

                                }    catch (Exception e) {
                                    Toast.makeText(getApplicationContext(),
                                            "Error" + e.getMessage().toString(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(getApplicationContext(),
                                        "eeeee" + error.toString(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.
                                getDefaultSharedPreferences(getApplicationContext());
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("img",attach);



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

    }
    void showfilechooser(int string) {
        // TODO Auto-generated method stub
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //getting all types of files

        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.
                    createChooser(intent, "Select a File to Upload"), string);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(getApplicationContext(),
                    "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult
            (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ImageView bimg;
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                ////
                Uri uri = data.getData();
                try {
                    path = FileUtils.getPath(this, uri);
                    File fil = new File(path);
                    float fln = (float) (fil.length() / 1024);
                    atype = path.substring(path.lastIndexOf(".") + 1);
                    fname = path.substring(path.lastIndexOf("/") + 1);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                try {
                    File imgFile = new File(path);
                    if (imgFile.exists()) {
                        Bitmap myBitmap = BitmapFactory.
                                decodeFile(imgFile.getAbsolutePath());
                        img.setImageBitmap(myBitmap); }
                    File file = new File(path);
                    byte[] b = new byte[8192];
                    Log.d("bytes read", "bytes read");
                    InputStream inputStream = new FileInputStream(file);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int bytesRead = 0;
                    while ((bytesRead = inputStream.read(b)) != -1) {
                        bos.write(b, 0, bytesRead);
                    }
                    byteArray = bos.toByteArray();

                    String str = Base64.encodeToString
                            (byteArray, Base64.NO_WRAP);
                    attach = str;


                } catch (Exception e) {
                    Toast.makeText(this, "String :"
                            + e.getMessage().toString(),
                            Toast.LENGTH_LONG).show();
                }

                ///

            }
        }
//    }


//    @Override
//    protected void onActivityResult
//    (int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            ///


            try {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG,
                        100, baos); //bm is the bitmap object
                img.setImageBitmap(image);
                byte[] b = baos.toByteArray();
                encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

            } catch (Exception e) {
                Toast.makeText(this, "String :" + e.getMessage()
                        .toString(), Toast.LENGTH_LONG).show();
            }

            File file = new File(Environment.getExternalStorageDirectory(),
                    "abcdef.jpg");
            FileOutputStream fos;
            byte[] data1 = android.util.Base64.decode
                    (encodedImage, android.util.Base64.DEFAULT);
            try {
                fos = new FileOutputStream(file);
                fos.write(data1);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                // handle exception
                Toast.makeText(getApplicationContext(),
                        e.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                // handle exception
                Toast.makeText(getApplicationContext(),
                        e.getMessage(), Toast.LENGTH_SHORT).show();
            }


            byte[] byteArray = null;
            try {
                InputStream inputStream = new FileInputStream(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] b = new byte[2048 * 8];
                int bytesRead = 0;

                while ((bytesRead = inputStream.read(b)) != -1) {
                    bos.write(b, 0, bytesRead);
                }

                byteArray = bos.toByteArray();
            } catch (IOException e) {
                Toast.makeText(this,
                        "String :" + e.getMessage().toString(),
                        Toast.LENGTH_LONG).show();
            }
            String str = Base64.encodeToString(byteArray, Base64.DEFAULT);

//            encodedImage = str;
            attach = str;

            byte[] bt = android.util.Base64.decode
                    (str, android.util.Base64.DEFAULT);

            //byte bt[]=re.getBytes();
            Log.d("imag.....", bt.toString());
            Bitmap bmp = BitmapFactory.decodeByteArray
                    (bt, 0, bt.length);
            if (bmp != null) {
                img.setImageBitmap(bmp);
            }


        }

    }

}