package com.hack.digitalocean.hisaab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddTransaction extends AppCompatActivity {

    EditText grpe,toe,amte;
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        grpe = (EditText) findViewById(R.id.grp_name_add_transact);
        toe = (EditText) findViewById(R.id.person_name_add_transact);
        amte = (EditText) findViewById(R.id.amount_add_transact);
        confirm = (Button) findViewById(R.id.confirm_add_transact);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTransact();
            }
        });




    }

    public void addTransact()
    {
        SharedPreferences sharedPref = getBaseContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        String from =sharedPref.getString(getResources().getString(R.string.emailkey), "");
        String to = toe.getText().toString();
        String grp = grpe.getText().toString();
        String amt = amte.getText().toString();
        Toast.makeText(this,"Transaction Added",Toast.LENGTH_LONG).show();
        Intent i= new Intent(this,HomeActivity.class);
        startActivity(i);

    }
}
