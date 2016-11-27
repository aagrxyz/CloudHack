package com.hack.digitalocean.hisaab;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Aman Agrawal on 26-11-2016.
 */

public class MySingleton {
    private static MySingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;
    public static final String Server_IP="http://67.205.151.138";
    public static final String add_grp_URL=Server_IP+"/api/group";
    public static final String add_user_URL =Server_IP+"/api/user";
    public static final String view_Group_URL =Server_IP+"/api/group";
    public static final String view_Transact =Server_IP+"/api/recieve";





    private MySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

    }

    public static synchronized MySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


}