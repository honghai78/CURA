package com.example.haitran.cura.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.ProgressBar;

import com.example.haitran.cura.R;
import com.example.haitran.cura.fragments.AuthenticationFragment;

public class SplashActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        new MyAsyncTask(this).execute();
//        new Handler().postDelayed(new Runnable(){
//            @Override
//            public void run() {
//                Intent mainIntent = new Intent (SplashActivity.this, SignInActivity.class);
//                SplashActivity.this.startActivity(mainIntent);
//                SplashActivity.this.finish();
//            }
//        }, 3000);
    }



    private class MyAsyncTask extends AsyncTask <Void, Integer, Void> {

        Activity contextParent;

        public MyAsyncTask(Activity contextParent) {
            this.contextParent = contextParent;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i <= 100; i++) {
                SystemClock.sleep(20);
                publishProgress(i);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            ProgressBar progressBar = (ProgressBar) contextParent.findViewById(R.id.progressBar);
            int number = values[0];
            progressBar.setProgress(number);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent mainIntent = new Intent (SplashActivity.this, SignInActivity.class);
            SplashActivity.this.startActivity(mainIntent);
            SplashActivity.this.finish();
        }
    }
}