package com.dokdo.transcreation.ilovedokdo.News;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Seungyong Son on 2018-03-05.
 */

public class NaverNewsSearch {
    public static String clientId = "rjtRU5HvF1scRp3vs8l1"; //애플리케이션 클라이언트 아이디값";
    public static String clientSecret = "EXBhUWvP5P"; //애플리케이션 클라이언트 시크릿값";
    private static ArrayList<NewsInfo> newsInfos = new ArrayList<>();

    public static void NaverSearch() {
        // Thread로 웹서버에 접속
        new Thread() {
            public void run() {
                try {
                    String text = URLEncoder.encode("대덕소프트웨어마이스터고등학교", "UTF-8");
                    String apiURL = "https://openapi.naver.com/v1/search/news?query=" + text + "&display=100&start=1&sort=sim"; // json 결과
                    URL url = new URL(apiURL);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("X-Naver-Client-Id", clientId);
                    con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                    int responseCode = con.getResponseCode();
                    BufferedReader br;
                    if (responseCode == 200) { // 정상 호출
                        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    } else {  // 에러 발생
                        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                    }
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = br.readLine()) != null)
                        response.append(inputLine);
                    br.close();
                    JSONObject jsonObject;
                    JSONArray jsonArray;

                    jsonObject = new JSONObject(response.toString());
                    jsonArray = jsonObject.getJSONArray("items");

                    if(jsonArray != null) // 킹갓 원준이가 도와줌
                        for(int i = 0; i < jsonArray.length(); i++) {
                            newsInfos.add(new NewsInfo(jsonArray.getJSONObject(i).getString("title"), jsonArray.getJSONObject(i).getString("link"),
                                    jsonArray.getJSONObject(i).getString("description")));
                            Log.d("KIMWONJUN_IS_GOD [" + i + "]", newsInfos.get(i).getTitle() + " " + newsInfos.get(i).getLink() + " " + newsInfos.get(i).getDescription());
                        }

                } catch (Exception e) {
                    Log.e("***NAVER***", String.valueOf(e));
                }
            }
        }.start();
    }
}
