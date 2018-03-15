package com.dokdo.transcreation.ilovedokdo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dokdo.transcreation.ilovedokdo.News.NaverNewsSearch;
import com.dokdo.transcreation.ilovedokdo.News.NewsInfo;

import java.util.ArrayList;

@SuppressLint("SetJavaScriptEnabled")
public class NewsWebActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static ArrayList<NewsInfo> newsInfos = new ArrayList<NewsInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://m.search.naver.com/search.naver?where=m_news&ie=utf8&sm=mns_hty&query=%EB%8F%85%EB%8F%84");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // 네이버 Data API 로 뉴스를 파싱하고 ArrayLIst<NewsInfo> 로 되있는 newsInfos 에다가 넣음
        // 추후 newsInfo 를 통해 뉴스 자료들을 리사이클러뷰로 보여줄 예정
        NaverNewsSearch nns = new NaverNewsSearch();
        try {
            newsInfos.addAll(nns.newsSearch());
            Log.d("NewsActivity Result [" + 0 + "]", newsInfos.get(0).getTitle() + " " + newsInfos.get(0).getLink() + " " + newsInfos.get(0).getDescription());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
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
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this, SayActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(this, DepActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(Intent.ACTION_SEND);

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
}
