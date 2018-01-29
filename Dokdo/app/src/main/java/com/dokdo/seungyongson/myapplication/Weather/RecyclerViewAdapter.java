package com.dokdo.seungyongson.myapplication.Weather;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dokdo.seungyongson.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seungyong Son on 2018-01-20.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    List<Weat> items;
    int item_layout;

    public RecyclerViewAdapter(Context context, List<Weat> items, int item_layout) {
        this.context=context;
        this.items=items;
        this.item_layout=item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Weat item = items.get(position);

        holder.image.setImageResource(item.getImage());
        holder.day.setText(item.getDay());
        holder.time.setText(item.getGTime());
        holder.weather.setText(item.getWeather());
        Log.e("fuck", item.getImage() + " " + item.getDay()  + " " + item.getGTime() + " " + item.getWeather());
    }

    public void RemoveData() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView day, time, weather;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.wea_image);
            day=(TextView)itemView.findViewById(R.id.wea_day);
            time=(TextView)itemView.findViewById(R.id.wea_time);
            weather=(TextView)itemView.findViewById(R.id.wea_weather);
            cardview=(CardView)itemView.findViewById(R.id.wea_cardview);
        }
    }
}
