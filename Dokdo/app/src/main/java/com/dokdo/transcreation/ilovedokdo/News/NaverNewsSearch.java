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
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Seungyong Son on 2018-03-05.
 */

public class NaverNewsSearch {
    public static String clientId = "rjtRU5HvF1scRp3vs8l1"; //애플리케이션 클라이언트 아이디값";
    public static String clientSecret = "EXBhUWvP5P"; //애플리케이션 클라이언트 시크릿값";
    private static ArrayList<NewsInfo> newsInfos = new ArrayList<>();

    public ArrayList<NewsInfo> newsSearch() throws ExecutionException, InterruptedException {
        // 스레드풀 생성
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        Callable<ArrayList<NewsInfo>> ca = new Callable<ArrayList<NewsInfo>>(){
            @Override
            public ArrayList<NewsInfo> call() throws Exception{
                try {
                    String text = URLEncoder.encode("독도", "UTF-8");
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

                    if (jsonArray != null) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            newsInfos.add(new NewsInfo(jsonArray.getJSONObject(i).getString("title"), jsonArray.getJSONObject(i).getString("link"),
                                    jsonArray.getJSONObject(i).getString("description")));

                            // split()로 필요없는 쓰레기 문자들 걸러내기
                            newsInfos.get(i).setTitle(Arrays.toString(newsInfos.get(i).getTitle().split("<b>|</b>|&quot;|&lt;|&gt;")));
                            newsInfos.get(i).setDescription((Arrays.toString(newsInfos.get(i).getDescription().split("<b>|</b>|&quot;|&lt;|&gt;"))));

                            Log.d("Naver Data Result [" + i + "]", newsInfos.get(i).getTitle() + " " + newsInfos.get(i).getLink() + " " + newsInfos.get(i).getDescription());
                        }
                        return newsInfos;
                    }
                } catch (Exception e) {
                    Log.e("***NAVER***", String.valueOf(e));
                }
                return null;
            }
        };
        Future<ArrayList<NewsInfo>> future = executorService.submit(ca);
        executorService.shutdown();
        return future.get();
    }
}
