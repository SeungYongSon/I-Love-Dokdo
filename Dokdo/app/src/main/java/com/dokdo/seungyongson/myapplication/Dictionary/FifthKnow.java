package com.dokdo.seungyongson.myapplication.Dictionary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dokdo.seungyongson.myapplication.R;

/**
 * Created by Seungyong Son on 2018-01-21.
 */

public class FifthKnow  extends android.support.v4.app.Fragment{
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
        know_text.setText("독도 근해 해저에 메탄 하이드레이트가 약 6억 톤가량 매장 되어 있다.");
        return linearLayout;
    }
}
