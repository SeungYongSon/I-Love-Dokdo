package com.dokdo.transcreation.ilovedokdo.News;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dokdo.transcreation.ilovedokdo.R;

import java.util.ArrayList;

/**
 * Created by Seungyong Son on 2018-01-20.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<NewsInfo> items;
    int item_layout;

    public void add(ArrayList<NewsInfo> data){
        items.addAll(data);
        notifyDataSetChanged();
    }

    public RecyclerViewAdapter(Context context, ArrayList<NewsInfo> items, int item_layout) {
        this.context=context;
        this.items=items;
        this.item_layout=item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_cardview,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final NewsInfo item = items.get(position);

        //holder.image.setImageResource(item.getImage());
        holder.Title.setText(item.getTitle());
        holder.Description.setText(item.getDescription());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
                intent.setPackage("com.android.chrome");
                context.startActivity(intent);
            }
        });
        Log.e("fuck", item.getTitle() + " " + item.getDescription()  + " " + item.getLink());
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
        //ImageView image;
        TextView Title, Description;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            //image = (ImageView)itemView.findViewById(R.id.wea_image);
            Title = (TextView)itemView.findViewById(R.id.news_title);
            Description = (TextView)itemView.findViewById(R.id.news_content);
            cardview = (CardView)itemView.findViewById(R.id.news_cardview);
        }
    }
}
