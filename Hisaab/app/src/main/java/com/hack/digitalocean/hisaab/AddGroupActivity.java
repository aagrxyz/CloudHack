package com.hack.digitalocean.hisaab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddGroupActivity extends AppCompatActivity {

    private LinearLayout lnrDynamicEditTextHolder;
    private EditText grp_name;
    private Button btnAdd,btnCreate;
    private int no_of_members=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        no_of_members=0;
        lnrDynamicEditTextHolder = (LinearLayout) findViewById(R.id.DynamicEmailHolder);
        grp_name = (EditText) findViewById(R.id.grp_name);
        btnAdd = (Button) findViewById(R.id.addpeope_init_group);
        btnCreate = (Button) findViewById(R.id.confirmgrp);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmgrp();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = new EditText(AddGroupActivity.this);
                editText.setId(no_of_members++);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                        android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
                editText.setLayoutParams(params);

                editText.setHint("Email of Member "+(no_of_members));
                lnrDynamicEditTextHolder.addView(editText);
            }
        });
    }

    public void confirmgrp()
    {
        if(grp_name.getText().length()>0)
        {
            String name = grp_name.getText().toString();
            JSONObject grp_json = new JSONObject();
            try {
                grp_json.put("name",name);
                JSONArray user_json = new JSONArray();
                SharedPreferences sharedPref = this.getSharedPreferences("preferences", MODE_PRIVATE);
                user_json.put(sharedPref.getString(getResources().getString(R.string.emailkey), ""));
                for(int i=0;i<no_of_members;i++)
                {
                    user_json.put(((EditText)findViewById(i)).getText().toString());
                }
                grp_json.put("users",user_json);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.POST, MySingleton.add_grp_URL, grp_json, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response)
                        {
                            try {
                                String mess = response.getString("message");
                                if(mess.equalsIgnoreCase("new group created!"))
                                {
                                    String grpid = response.getString("id");
                                    SharedPreferences sharedPref = getApplication().getSharedPreferences("preferences",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPref.edit();
                                    editor.putString(grp_name.getText().toString(),grpid);
                                    editor.commit();
                                    Toast.makeText(getBaseContext(),grpid,Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                                    startActivity(i);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {

                        }
                    });
            MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
        }
    }
}
