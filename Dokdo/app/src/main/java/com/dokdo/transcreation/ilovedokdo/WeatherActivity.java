package com.dokdo.transcreation.ilovedokdo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import static com.dokdo.transcreation.ilovedokdo.Weather.Weather.*;


/**
 * Created by Seungyong Son on 2018-01-20.
 */


public class WeatherActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static public TextView test;
    static public RecyclerView recyclerView;
    static public  Context context;
    static public SwipeRefreshLayout swiperefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swiperefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        rva.RemoveData();
                        WhatIsWeather(false);
                        swiperefresh.setRefreshing(false);
                    }
                }
        );

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        WhatIsWeather(false);  // 여기 함수부분
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            rva.RemoveData();
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent = new Intent(this, NewsActivity.class);
            startActivity(intent);
            rva.RemoveData();
            finish();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this, SayActivity.class);
            startActivity(intent);
            rva.RemoveData();
            finish();
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(this, DepActivity.class);
            startActivity(intent);
            rva.RemoveData();
            finish();
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);

            intent.setType("text/plain");

            // Set default text message
            // 카톡, 이메일, MMS 다 이걸로 설정 가능
            //String subject = "문자의 제목";
            String text = "우리 모두 독도사랑 하즈아!!";
            //intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, text);

            // Title of intent
            Intent chooser = Intent.createChooser(intent, "친구에게 공유하기");
            startActivity(chooser);
        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://asked.kr/izen1231"));
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

