package com.dokdo.transcreation.ilovedokdo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dokdo.transcreation.ilovedokdo.Dictionary.FifthKnow;
import com.dokdo.transcreation.ilovedokdo.Dictionary.FirstKnow;
import com.dokdo.transcreation.ilovedokdo.Dictionary.FourthKnow;
import com.dokdo.transcreation.ilovedokdo.Dictionary.SecondKnow;
import com.dokdo.transcreation.ilovedokdo.Dictionary.ThirdKnow;

import static com.dokdo.transcreation.ilovedokdo.Weather.Weather.WhatIsWeather;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static TextView wea_title, wea_content;
    public static ImageView img_wea;

    public ViewPager viewDiPager, viewDokPager;
    MainDokdoImgSliderAdapter dokAdapter;

    int MAX_PAGE=5;
    Fragment cur_fragment = new Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewDokPager = (ViewPager) findViewById(R.id.view);
        dokAdapter = new MainDokdoImgSliderAdapter(this);
        viewDokPager.setAdapter(dokAdapter);

        viewDiPager = (ViewPager)findViewById(R.id.dictionary);
        viewDiPager.setAdapter(new adapter(getSupportFragmentManager()));
        viewDiPager.setCurrentItem(0);
        viewDiPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        break;

                    case 1:
                        break;

                    case 2:
                        break;

                    case 3:
                        break;
                    case 4:

                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        wea_title = (TextView) findViewById(R.id.wea_title);
        wea_content = (TextView) findViewById(R.id.wea_content);
        img_wea = (ImageView) findViewById(R.id.wea_image);
        WhatIsWeather(true);
    }

    public void onClick(View v) {

        switch (v.getId()){
            case R.id.say_cardview :
                Intent intent = new Intent(this, SayActivity.class);
                startActivity(intent);
                break;

        }
    }

    private long pressedTime;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if ( pressedTime == 0 ) {
                Toast.makeText(getApplicationContext(), "한번 더 누르면 종료됩니다", Toast.LENGTH_SHORT).show();
                pressedTime = System.currentTimeMillis();
            }
            else {
                int seconds = (int) (System.currentTimeMillis() - pressedTime);

                if ( seconds > 2000 ) {
                    pressedTime = 0;
                }
                else {
                    finish();
                }
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(this, NewsActivity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this, SayActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(this, DepActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);

            intent.setType("text/plain");

            // Set default text message
            // 카톡, 이메일, MMS 다 이걸로 설정 가능
            //String subject = "문자의 제목";
            String text = "I LOVE DOKDO 독도 홍보 앱 입니다!";
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

    private class adapter extends FragmentPagerAdapter {
        public adapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            if(position<0 || MAX_PAGE<=position)
                return null;
            switch (position){
                case 0:
                    cur_fragment = new FirstKnow();
                    break;
                case 1:
                    cur_fragment = new SecondKnow();
                    break;
                case 2:
                    cur_fragment=new ThirdKnow();
                    break;

                case 3:
                    cur_fragment = new FourthKnow();
                    break;
                case 4:
                    cur_fragment = new FifthKnow();
                    break;
            }
            return cur_fragment;
        }

        @Override
        public int getCount() {
            return MAX_PAGE;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
}
