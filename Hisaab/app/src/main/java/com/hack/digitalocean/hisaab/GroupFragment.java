package com.hack.digitalocean.hisaab;

import android.app.Fragment;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Aman Agrawal on 26-11-2016.
 */

public class GroupFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.group_list_fragment,container,false);
        String[] groupArray = {"Android  33","IPhone  44","WindowsMobile  55","Android  33","IPhone  44","WindowsMobile  55","Android  33","IPhone  44","WindowsMobile  55","Blackberry  77",
                "WebOS  88","Ubuntu  99","Windows7  101","Max OS X  333"};
        ListView lv = (ListView) v.findViewById(R.id.group_list);
        lv.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,groupArray));

        return v;
    }
}
