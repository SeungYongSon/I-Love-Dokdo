package com.dokdo.seungyongson.myapplication.Weather;


import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.dokdo.seungyongson.myapplication.R;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import static com.dokdo.seungyongson.myapplication.WeatherActivity.context;
import static com.dokdo.seungyongson.myapplication.WeatherActivity.recyclerView;


public class Weather{

    static public Document doc = null;
    static public  List<Weat> items=new ArrayList<>();
    static public  Weat[] item;
    static public RecyclerViewAdapter rva;

    static public void WhatIsWeather(){
        GetXMLTask task = new GetXMLTask();
        task.execute("http://www.weather.go.kr/wid/queryDFSRSS.jsp?zone=4794033000");
    }

    //private inner class extending AsyncTask
    static public class GetXMLTask extends AsyncTask<String, Void, Document> {

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

                if(Integer.parseInt(Day) == 0){
                    Day = "어제";
                } else if(Integer.parseInt(Day) == 1){
                    Day = "오늘";
                }else{
                    Day = "내일";
                }

                NodeList weaList = fstElmnt.getElementsByTagName("wfKor");
                Weather = weaList.item(0).getChildNodes().item(0).getNodeValue();

                item[i]=new Weat(Time + ":00", Temp, Weather, Day);
                items.add(item[i]);
                Log.d("asdf", Time + " " + Temp + " " + Day  + " " + Weather);
            }
            rva = new RecyclerViewAdapter(context,items, R.layout.content_weather);

            recyclerView.setAdapter(rva);
            super.onPostExecute(doc);
        }
    }//end inner class - GetXMLTask
}
