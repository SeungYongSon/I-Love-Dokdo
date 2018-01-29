package com.dokdo.transcreation.ilovedokdo.Weather;


import android.os.AsyncTask;
import android.util.Log;

import com.dokdo.transcreation.ilovedokdo.R;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import static com.dokdo.transcreation.ilovedokdo.MainActivity.img_wea;
import static com.dokdo.transcreation.ilovedokdo.MainActivity.wea_content;
import static com.dokdo.transcreation.ilovedokdo.MainActivity.wea_title;
import static com.dokdo.transcreation.ilovedokdo.WeatherActivity.context;
import static com.dokdo.transcreation.ilovedokdo.WeatherActivity.recyclerView;


public class Weather{

    static public Document doc = null;
    static public  List<Weat> items=new ArrayList<>();
    static public  Weat[] item;
    static public Weat ni, ni2;
    static public RecyclerViewAdapter rva;
    static boolean mainA = false;

    public static void WhatIsWeather(boolean now){
        GetXMLTask task = new GetXMLTask();
        task.execute("http://www.weather.go.kr/wid/queryDFSRSS.jsp?zone=4794033000");

        if (now) mainA = true;
        else mainA = false;
    }

    //private inner class extending AsyncTask
    public static class GetXMLTask extends AsyncTask<String, Void, Document> {

        @Override
        protected Document doInBackground(String... urls) {
            URL url;
            try {
                url = new URL(urls[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder(); //XML문서 빌더 객체를 생성
                doc = db.parse(new InputSource(url.openStream())); //XML문서를 파싱한다.
                doc.getDocumentElement().normalize();

            } catch (Exception e) {
                Log.e("Passing Error!!!", "*** 날씨 파싱에 문제 생김 !!! ****");
            }
            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {

            String Time;
            String Temp;
            String Weather;
            String Day;

            String s = "";
            //data태그가 있는 노드를 찾아서 리스트 형태로 만들어서 반환
            NodeList nodeList = doc.getElementsByTagName("data");
            //data 태그를 가지는 노드를 찾음, 계층적인 노드 구조를 반환
            item = new Weat[nodeList.getLength()];

            for(int i = 0; i< nodeList.getLength(); i++){

                Node node = nodeList.item(i); //data엘리먼트 노드
                Element fstElmnt = (Element) node;
                NodeList timeList  = fstElmnt.getElementsByTagName("hour");
                Time = timeList.item(0).getChildNodes().item(0).getNodeValue();

                NodeList tempList  = fstElmnt.getElementsByTagName("temp");
                Temp = tempList.item(0).getChildNodes().item(0).getNodeValue();

                NodeList dayList  = fstElmnt.getElementsByTagName("day");
                Day = dayList.item(0).getChildNodes().item(0).getNodeValue();

                NodeList weaList = fstElmnt.getElementsByTagName("wfKor");
                Weather = weaList.item(0).getChildNodes().item(0).getNodeValue();

                if(!mainA) {
                    if(Integer.parseInt(Day) == 0){
                        Day = "어제";
                    } else if(Integer.parseInt(Day) == 1){
                        Day = "오늘";
                    }else{
                        Day = "내일";
                    }
                    item[i] = new Weat(Time + ":00", Temp, Weather, Day);
                    items.add(item[i]);
                }else {
                    Calendar cal = Calendar.getInstance();
                    int time = Integer.parseInt(Time);
                    int ntime = cal.get(Calendar.HOUR);

                    for(int j = 3; j <= 24;){
                        if(j<= ntime && j*3 > ntime){
                            if(j == time){
                                if(Integer.parseInt(Day) == 1){
                                    ni = new Weat(Weather);
                                }else {
                                    ni2 = new Weat(Weather);
                                }
                                Log.e("****현재 날씨 파싱****", Time + " " + Temp + " " + Day  + " " + Weather);
                            }
                        }
                        j *= 3;
                    }

                }
                Log.d("****날씨 파싱****", Time + " " + Temp + " " + Day  + " " + Weather);
            }
            if(!mainA){
                rva = new RecyclerViewAdapter(context,items, R.layout.content_weather);
                recyclerView.setAdapter(rva);
            }else {
                wea_title.setText(ni.getWeather());
                wea_content.setText("어제 이 시각에는 "+ ni2.getWeather());
                img_wea.setImageResource(ni.getImage());
            }
            super.onPostExecute(doc);
        }
    }//end inner class - GetXMLTask
}
