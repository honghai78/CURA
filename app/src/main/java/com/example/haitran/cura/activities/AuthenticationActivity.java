package com.example.haitran.cura.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.haitran.cura.R;
import com.example.haitran.cura.fragments.AuthenticationFragment;

/**
 * Created by kha.phan on 6/29/2016.
 */
public class AuthenticationActivity extends AppCompatActivity{


    private String mNameEducator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Authentication");
        setContentView(R.layout.activity_authentication);
        mNameEducator = getNameEducator();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        AuthenticationFragment fragment = new AuthenticationFragment();
        fragment.setNameEducator(mNameEducator);
        ft.replace(R.id.layout_fragment, fragment);
        ft.commit();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private String getNameEducator(){
        Intent intent = getIntent();
        if(intent!=null)
            return intent.getStringExtra("NAME");
        return "";
    }
}
