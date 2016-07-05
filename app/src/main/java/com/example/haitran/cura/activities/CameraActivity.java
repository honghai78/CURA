package com.example.haitran.cura.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.haitran.cura.fragments.Camera2BasicFragment;
import com.example.haitran.cura.R;

public class CameraActivity extends AppCompatActivity {
    private int fragmentStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Intent intent = getIntent();

        fragmentStart = intent.getIntExtra("FILE_FOLDER",0);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_main, Camera2BasicFragment.newInstance(), "B")
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                 getView().setFocusableInTouchMode(true);
                replace();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

            finish();

    }

    public void replace()
    {
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_main, Camera2BasicFragment.newInstance())
                .commit();
    }
}
