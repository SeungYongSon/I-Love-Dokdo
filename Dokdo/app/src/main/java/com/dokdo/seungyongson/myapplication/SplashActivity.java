package com.dokdo.seungyongson.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.Toast;

/**
 * Created by Seungyong Son on 2018-01-20.
 */

public class SplashActivity extends Activity {

    /** 로딩 화면이 떠있는 시간(밀리초단위)  **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    /** 처음 액티비티가 생성될때 불려진다. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI && activeNetwork.isConnectedOrConnecting()) {
                // wifi 연결중
                startLoading();
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE && activeNetwork.isConnectedOrConnecting()) {
                // 모바일 네트워크 연결중
                startLoading();
            }
            else {
                // 네트워크 오프라인 상태.
                Toast.makeText(getApplicationContext(),"네트워크가 연결되지 않았습니다!!", Toast.LENGTH_SHORT).show();
                finish();
            }

        } else {
            // 네트워크 null.. 모뎀이 없는 경우??
            Toast.makeText(getApplicationContext(),"뭐야 실화야..?", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadMain();
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void LoadMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
