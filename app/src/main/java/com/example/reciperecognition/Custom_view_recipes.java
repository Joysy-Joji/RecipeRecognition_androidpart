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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Custom_view_recipes extends BaseAdapter {
    String [] recipes,url,desc,ing;
    private Context context;
    public Custom_view_recipes(Context appcontext,String[]recipes,String[]url,String[]desc,String[]ing)
    {

        this.context=appcontext;
        this.recipes=recipes;
        this.url=url;
        this.desc=desc;

        this.ing=ing;


    }


//    public Custom_view_recipes(Context appcontext,String[]recipes,String[]url,String[]desc,String[]ing)
//    {
//        int l=ing.length;
//        String[] str[l]
//        this.context=appcontext;
//        this.recipes=recipes;
//        this.url=url;
//        this.desc=desc;
//        for (int i=0;i<ing.length;i++) {
//
//            str[i] = ing[i].replaceAll("[^a-zA-Z0-9]", " ");
//        }
//        this.ing=ing;
//        Toast.makeText(appcontext.getApplicationContext(), "GG" +ing, Toast.LENGTH_SHORT).show();
//
//    }

    @Override
    public int getCount() {
        return ing.length;
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
            gridView=inflator.inflate(R.layout.custom_view_recipes,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView16);
        ImageView im=(ImageView) gridView.findViewById(R.id.imageView10);

        tv1.setTextColor(Color.BLACK);


        tv1.setText(recipes[i]);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String urle=url[i];


        Picasso.with(context).load(urle). into(im);

        return gridView;
    }
}
