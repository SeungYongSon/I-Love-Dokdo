package com.dokdo.transcreation.ilovedokdo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Seungyong Son on 2018-02-28.
 */
public class MainDokdoImgSliderAdapter extends PagerAdapter{

    private int[] images = {R.drawable.dokdo, R.drawable.dokdo_sec};
    private LayoutInflater inflater;
    private Context context;

    public MainDokdoImgSliderAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dokdo_img_slider, container, false);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);

        imageView.setImageResource(images[position]);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
    }
}
