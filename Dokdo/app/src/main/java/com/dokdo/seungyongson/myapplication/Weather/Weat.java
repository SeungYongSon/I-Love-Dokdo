package com.dokdo.seungyongson.myapplication.Weather;

import android.util.Log;

import com.dokdo.seungyongson.myapplication.R;

/**
 * Created by Seungyong Son on 2018-01-20.
 */

public class Weat {

    private String Time;
    private String Temp;
    private String Weather;
    private String Day;

    public void setGTime(String time){
        Time = time + ":00";
    }

    public void setTemp(String Temp){
        this.Temp = Temp;
    }

    public void setDay(int day){
        if(day == 0){
            Day = "어제";
        } else if(day == 1){
            Day = "어제";
        }else{
            Day = "내일";
        }
    }

    public void setWeather(String Weather){
        this.Weather = Weather;
    }

    public int getImage(){
        if (Weather.equals("맑음")) {
            return R.drawable.ic_weather_sun;
        } else if (Weather.equals("구름 조금")) {
            return R.drawable.ic_weather_little_cloud;
        } else if (Weather.equals("구름 많음")) {
            return R.drawable.ic_weather_many_cloud;
        }else if (Weather.equals("흐림")) {
            return R.drawable.ic_weather_blue;
        } else if (Weather.equals("흐리고 비")) {
            return R.drawable.ic_weather_cloudy_rain;
        } else if (Weather.equals("비")) {
            return R.drawable.ic_weather_rain;
        } else {
            Log.w("CHECK", "등록된 이미지가 없습니다.");
            return R.drawable.ic_sample;
        }
    }

    public String getGTime(){
        return Time;
    }

    public String getTemp(){
        return Temp;
    }

    public String getDay(){
        return Day;
    }

    public String getWeather(){
        return Weather;
    }


    public Weat(String Time, String Temp, String Weather, String Day){
        this.Time=Time;
        this.Temp=Temp;
        this.Weather=Weather;
        this.Day=Day;
    }
}
