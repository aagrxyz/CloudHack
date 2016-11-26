package com.hack.digitalocean.hisaab;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.io.InputStream;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    ImageView dp;
    TextView name, email;
    String nameOfUser;
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
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add_grp = new Intent(getBaseContext(),AddGroupActivity.class);
                startActivity(add_grp);
            }
        });

        // GroupFragment grpFrag = new GroupFragment();

        // In case this activity was started with special instructions                                                     from an     // Intent, pass the Intent's extras to the fragment as arguments
        //grpFrag.setArguments(getIntent().getExtras());

        // Add the fragment to the 'fragment_container' FrameLayout
        // getSupportFragmentManager().beginTransaction()
        //       .add(R.id.fragment, grpFrag).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        initdrawer(drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar tt = (Toolbar) findViewById(R.id.toolbar);


        if (id == R.id.nav_groups) {
            drawer.closeDrawer(GravityCompat.START);
            tt.setTitle("My Groups");
            // Handle the camera action
        }
        else if (id == R.id.nav_approvals)
        {
            tt.setTitle("Pending Approvals");
        }
        else if (id == R.id.nav_log)
        {
            tt.setTitle("My Transactions");
        } else if (id == R.id.nav_feedback) {
            final Dialog rankDialog = new Dialog(HomeActivity.this, R.style.FullHeightDialog);
            rankDialog.setContentView(R.layout.rank_dialog);
            rankDialog.setCancelable(true);
            RatingBar ratingBar = (RatingBar) rankDialog.findViewById(R.id.dialog_ratingbar);
            TextView text = (TextView) rankDialog.findViewById(R.id.rank_dialog_text1);
            text.setText(nameOfUser);
            Button updateButton = (Button) rankDialog.findViewById(R.id.rank_dialog_button);
            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rankDialog.dismiss();
                }
            });
            rankDialog.show();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_signout) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            updateUI();
                        }
                    });
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void initdrawer(DrawerLayout drawer) {

        NavigationView navigationView = (NavigationView) drawer.findViewById(R.id.nav_view);
        View headerLayout = navigationView.getHeaderView(0);
        dp = (ImageView) headerLayout.findViewById(R.id.userdpnav);
        name = (TextView) headerLayout.findViewById(R.id.usernamenav);
        email = (TextView) headerLayout.findViewById(R.id.useremailnav);
        SharedPreferences sharedPref = this.getSharedPreferences("preferences", MODE_PRIVATE);
        String url = sharedPref.getString(getResources().getString(R.string.photourikey), null);
        name.setText(sharedPref.getString(getResources().getString(R.string.namekey), ""));
        nameOfUser = (String) name.getText();
        email.setText(sharedPref.getString(getResources().getString(R.string.emailkey), ""));
        new HomeActivity.DownloadImageTask(dp)
                .execute(url);
    }

    public void updateUI() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
