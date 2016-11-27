package com.hack.digitalocean.hisaab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GroupPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_page);
        String grpname = getIntent().getStringExtra("grpname");
        TextView tt = (TextView) findViewById(R.id.textView2);
        tt.setText(grpname);
    }
}
