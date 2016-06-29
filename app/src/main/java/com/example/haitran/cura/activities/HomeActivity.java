package com.example.haitran.cura.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haitran.cura.R;
import com.example.haitran.cura.fragments.InQueueFragment;
import com.example.haitran.cura.fragments.RegisteredPatientFragment;

public class HomeActivity extends AppCompatActivity {

    private TextView  mTxtCountRegistered, mTxtCountQueue, mTxtRegistered, mTxtInQueue,
            mTxtBgRegistered, mTxtBgQueue;

    private boolean isRegistered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWidgets();

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Home");
//        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.setIcon(R.mipmap.ic_home_3);
        ab.setDefaultDisplayHomeAsUpEnabled(true);

        RegisteredPatientFragment registeredPatientFragment = new RegisteredPatientFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content_fragment, registeredPatientFragment).commit();
        isRegistered = true;

        mTxtCountRegistered.setText("(" + registeredPatientFragment.countRegisteredPatient() + ")");

        mTxtRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeredClick();
            }
        });

        mTxtInQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inQueueClick();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_search:
                Toast.makeText(getBaseContext(), "Search clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_about:
                Toast.makeText(getBaseContext(), "About clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getWidgets() {
        mTxtCountRegistered = (TextView) findViewById(R.id.txt_count_registered_home);
        mTxtBgRegistered = (TextView) findViewById(R.id.txt_bg_registered_home);
        mTxtCountQueue = (TextView) findViewById(R.id.txt_count_queue_home);
        mTxtBgQueue = (TextView) findViewById(R.id.txt_bg_queue_home);
        mTxtRegistered = (TextView) findViewById(R.id.txt_registered_home);
        mTxtInQueue = (TextView) findViewById(R.id.txt_in_queue_home);
    }


    public void registeredClick() {
        mTxtBgRegistered.setBackgroundResource(R.drawable.bg_text_view);
        mTxtBgQueue.setBackgroundResource(android.R.color.transparent);

        if (!isRegistered) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment, new RegisteredPatientFragment()).commit();
            isRegistered = true;
        }
        Toast.makeText(this, "Registered click", Toast.LENGTH_SHORT).show();
    }

    public void inQueueClick() {
        mTxtBgQueue.setBackgroundResource(R.drawable.bg_text_view);
        mTxtBgRegistered.setBackgroundResource(android.R.color.transparent);

        if (isRegistered) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment, new InQueueFragment()).commit();
            isRegistered = false;
        }
        Toast.makeText(this, "In Queue click", Toast.LENGTH_SHORT).show();
    }

//    public void setCountRegisteredPatient(int count){
//        mTxtCountRegistered.setText("(" + count + ")");
//    }

}
