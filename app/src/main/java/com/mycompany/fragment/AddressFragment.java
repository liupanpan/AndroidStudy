package com.mycompany.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rzw4v0 on 2015/10/9.
 */
public class AddressFragment extends ListFragment {
    private AnimalListAdapter adapter = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new AnimalListAdapter (getActivity());
        setListAdapter(adapter);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.page_02, container, false);
        return v;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        System.out.println("Click On List Item!!!");
        super.onListItemClick(l, v, position, id);
    }
}
