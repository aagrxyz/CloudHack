package com.hack.digitalocean.hisaab;

import android.app.Fragment;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.Inet4Address;
import java.util.ArrayList;

/**
 * Created by Aman Agrawal on 26-11-2016.
 */

public class GroupFragment extends Fragment {

    public ArrayList<String> al = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.group_list_fragment,container,false);
        SharedPreferences sharedPref = getActivity().getBaseContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        final ListView lv = (ListView) v.findViewById(R.id.group_list);



        String url = MySingleton.add_user_URL + "/" + sharedPref.getString(getResources().getString(R.string.emailkey), "");
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray grps = response.getJSONArray("groups");
                           // Toast.makeText(getActivity(),String.valueOf(grps.length()),Toast.LENGTH_LONG).show();
                            for(int i= 0;i< grps.length();i++)
                            {
                               // Toast.makeText(getActivity(),grps.getJSONObject(i).getString("name"),Toast.LENGTH_LONG).show();

                                String ggggname = grps.getJSONObject(i).getString("name");
                                String ggid = grps.getJSONObject(i).getString("_id");
                                al.add(ggggname);
                                editor.putString(ggggname,ggid);

                                //Toast.makeText(getActivity(),String.valueOf(al.size()),Toast.LENGTH_LONG).show();



                            }
                            lv.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,al));
                            editor.commit();
                            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent i = new Intent(getActivity(),GroupPage.class);
                                    i.putExtra("grpname",al.get(position));
                                    startActivity(i);
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),"Some error occured while retieving groups",Toast.LENGTH_LONG).show();
                    }
                });

        MySingleton.getInstance(getActivity()).addToRequestQueue(jsObjRequest);
       // Toast.makeText(getActivity(),String.valueOf(al.size()),Toast.LENGTH_LONG).show();


        return v;
    }
}
