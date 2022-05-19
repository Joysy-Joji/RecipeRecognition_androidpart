package com.example.reciperecognition;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Custom_view_recipe2 extends BaseAdapter {
    String [] recipes,url2,desc,ing;
    private Context context;
    public Custom_view_recipe2(Context applicationContext, String[] recipes, String[] url) {
        this.context=applicationContext;
        this.recipes=recipes;
        this.url2=url;


    }

    @Override
    public int getCount() {
        return recipes.length;
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
            gridView=inflator.inflate(R.layout.custom_view_recipes2,null);

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

        String urle=url2[i];


        Picasso.with(context).load(urle). into(im);

        return gridView;


    }
}

