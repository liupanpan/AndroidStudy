package com.mycompany.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

/**
 * Created by rzw4v0 on 2015/10/9.
 */
public class HomeFragment extends Fragment {
    MyChartView tu;
    HashMap<Double, Double> map;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.page_01, container, false);

        /*tu= (MyChartView)rootView.findViewById(R.id.menulist);
        tu.SetTuView(map, 50, 10, "", "", false);
        map=new HashMap<Double, Double>();
        map.put(1.0, (double) 0);
        map.put(2.0, 25.0);
        map.put(3.0, 32.0);
        map.put(4.0, 41.0);
        map.put(5.0, 16.0);
        map.put(6.0, 36.0);
        map.put(7.0, 26.0);
        tu.setTotalvalue(50);
        tu.setPjvalue(10);
        tu.setMap(map);
        tu.setMargint(20);
        tu.setMarginb(50);
        tu.setMstyle(MyChartView.Mstyle.Line);*/

        return rootView;
    }

}