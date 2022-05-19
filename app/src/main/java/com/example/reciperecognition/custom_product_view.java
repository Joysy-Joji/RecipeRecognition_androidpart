package com.example.reciperecognition;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class custom_product_view extends BaseAdapter {
    String [] pname,sname,splace,pprice,imgg;
    private Context context;

    public custom_product_view (Context appcontext,String[]pname,String[]sname,String[]splace,String[]pprice,String[]imgg)
    {
        this.context=appcontext;
        this.pname=pname;
        this.sname=sname;
        this.splace=splace;
        this.imgg=imgg;
        this.pprice=pprice;


    }


    @Override
    public int getCount() {
        return pname.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.activity_custom_product_view,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView10);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView11);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView12);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView13);
        ImageView im=(ImageView) gridView.findViewById(R.id.imageView1);

        tv1.setTextColor(Color.BLACK);


        tv1.setText(pname[i]);
        tv2.setText(sname[i]);
        tv3.setText(splace[i]);
        tv4.setText(pprice[i]);



        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");
        String url="http://" + ip + ":1234"+imgg[i];
        Picasso.with(context).load(url). into(im);
//        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
//        String ip=sh.getString("ip","");

//        String urle=url[i];
//        Picasso.with(context).load(urle). into(im);

        return gridView;
    }
}
