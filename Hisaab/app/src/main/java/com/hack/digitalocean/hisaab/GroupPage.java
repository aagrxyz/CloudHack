package com.hack.digitalocean.hisaab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GroupPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_page);
        final String grpname = getIntent().getStringExtra("grpname");
        final TextView tt = (TextView) findViewById(R.id.textView2);
        SharedPreferences sharedPref = this.getSharedPreferences("preferences",MODE_PRIVATE);


        String url = MySingleton.view_Group_URL + "/" + sharedPref.getString(grpname, "");
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        tt.setText(response.toString());


                    }
                }, new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(),"Some error occured while retieving group info",Toast.LENGTH_LONG).show();
                    }
                });

        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }
}
