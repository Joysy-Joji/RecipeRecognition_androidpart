package com.example.reciperecognition;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reciperecognition.databinding.ActivityHomepageBinding;

public class homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomepageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHomepage.toolbar);
        binding.appBarHomepage.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_homepage);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homepage, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_homepage);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.nav_home)
        {

        }
        if(id==R.id.nav_profile)
        {
            Intent reg = new Intent(getApplicationContext(), viewprofile.class);
            startActivity(reg);
        }
        if(id==R.id.nav_viewrecipe)
        {
            Intent reg = new Intent(getApplicationContext(), viewrecipe.class);
            startActivity(reg);
        }
        if(id==R.id.nav_searchrecipee)
        {
            Intent reg = new Intent(getApplicationContext(), searchrecipe.class);
            startActivity(reg);
        }
//        if(id==R.id.nav_complaint)
//        {
//            Intent reg = new Intent(getApplicationContext(),complaint.class);
//            startActivity(reg);
//        }
        if(id==R.id.ps)
        {
            Intent reg = new Intent(getApplicationContext(), product_store_view.class);
            startActivity(reg);
        }
        if(id==R.id.review)
        {
            Intent reg = new Intent(getApplicationContext(), review.class);
            startActivity(reg);
        }
        if(id==R.id.logout)
        {
            Intent reg = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(reg);
        }

        return false;
    }
}