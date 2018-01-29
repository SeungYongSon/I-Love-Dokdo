package com.dokdo.transcreation.ilovedokdo.Dictionary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dokdo.transcreation.ilovedokdo.R;

/**
 * Created by Seungyong Son on 2018-01-21.
 */

public class SecondKnow  extends android.support.v4.app.Fragment{
    LinearLayout linearLayout;
    TextView know_text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        linearLayout=(LinearLayout)inflater.inflate(R.layout.know_layout,container,false);
        know_text = (TextView) linearLayout.findViewById(R.id.know_text);
        know_text.setText("독도는 대한민국의 천연기념물 제336호 이다.");
        return linearLayout;
    }
}