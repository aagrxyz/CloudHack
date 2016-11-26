package com.hack.digitalocean.hisaab;

import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextClock;
import android.widget.TextView;

import java.sql.Array;

public class ggg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ggg);
        String[] groupArray = {"Android  33","IPhone  44","WindowsMobile  55","Android  33","IPhone  44","WindowsMobile  55","Android  33","IPhone  44","WindowsMobile  55","Blackberry  77",
                "WebOS  88","Ubuntu  99","Windows7  101","Max OS X  333"};

       ListView l  = (ListView) (findViewById(R.id.listView));
       // ( new ArrayAdapter<String>()).
        l.setAdapter(new ArrayAdapter<String>(ggg.this, android.R.layout.simple_list_item_1,groupArray));


    }

}
