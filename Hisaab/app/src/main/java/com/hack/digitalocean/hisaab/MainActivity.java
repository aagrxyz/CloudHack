package com.hack.digitalocean.hisaab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.io.InputStream;
import java.io.StringReader;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    ImageView dp;
    TextView t;
    Button signout;
    private GoogleApiClient mGoogleApiClient;

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("MainActivity", "onConnectionFailed:" + connectionResult);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {

            String urldisplay = urls[0];

            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
               // Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dp = (ImageView) findViewById(R.id.userdp);
        t= (TextView) findViewById(R.id.everything);
        signout = (Button) findViewById(R.id.sign_out_button_main);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        SharedPreferences sharedPref = this.getSharedPreferences("preferences",MODE_PRIVATE);
        String url = sharedPref.getString(getResources().getString(R.string.photourikey),null);
        String s = sharedPref.getString(getResources().getString(R.string.namekey),"")  + "   "
                + sharedPref.getString(getResources().getString(R.string.emailkey),"");
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
        new DownloadImageTask((ImageView) findViewById(R.id.userdp))
                .execute(url);
        t.setText(s);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            signOut();
            }
        });

    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI();

                        // [END_EXCLUDE]
                    }
                });
    }
    public void updateUI()
    {
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
    }
}
