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

public class ThirdKnow  extends android.support.v4.app.Fragment{
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
        know_text.setText("일본에서는 다케시마(일본어: 竹島 たけしま)라는 명칭으로 영유권을 주장한다.");
        return linearLayout;
    }
}
