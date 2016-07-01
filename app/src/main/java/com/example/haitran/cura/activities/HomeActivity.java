package com.example.haitran.cura.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haitran.cura.R;
import com.example.haitran.cura.data.MyData;
import com.example.haitran.cura.fragments.InQueueFragment;
import com.example.haitran.cura.fragments.RegisteredPatientFragment;
import com.example.haitran.cura.models.Patient;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private TextView  mTxtCountRegistered, mTxtCountQueue,
            mTxtBgRegistered, mTxtBgQueue;
    private LinearLayout linear_registered, linear_queue;

    private boolean isRegistered;


    private Patient patientSelected;
    private List<Patient> registeredPatientList;
    private List<Patient> patientsInQueueList;
    private MyData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWidgets();
        data = new MyData();

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Home");
//        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.setIcon(R.mipmap.ic_home_3);
        ab.setDefaultDisplayHomeAsUpEnabled(true);

        //Get data from MyData
        registeredPatientList = data.getRegisteredPatientList();
        patientsInQueueList = data.getPatientListInQueue();
        patientsInQueueList = data.sortPatientByTimeInQueue(patientsInQueueList);

        RegisteredPatientFragment registeredPatientFragment = new RegisteredPatientFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content_fragment, registeredPatientFragment).commit();
        isRegistered = true;

        mTxtCountRegistered.setText("(" + registeredPatientList.size() + ")");
        mTxtCountQueue.setText("(" + patientsInQueueList.size() + ")");

        linear_registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeredClick();
            }
        });

        linear_queue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inQueueClick();
            }
        });
    }

    public void reload(){
        registeredPatientList = data.getRegisteredPatientList();
        patientsInQueueList = data.sortPatientByTimeInQueue(data.getPatientListInQueue());

        mTxtCountRegistered.setText("(" + registeredPatientList.size() + ")");
        mTxtCountQueue.setText("(" + patientsInQueueList.size() + ")");
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

        linear_queue = (LinearLayout) findViewById(R.id.linear_in_queue_home);
        linear_registered = (LinearLayout) findViewById(R.id.linear_register_home);
    }

    public void registeredClick() {
        mTxtBgRegistered.setBackgroundResource(R.color.colorPrimary);
        mTxtBgQueue.setBackgroundResource(android.R.color.transparent);

        if (!isRegistered) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment, new RegisteredPatientFragment()).commit();
            isRegistered = true;
        }
        Toast.makeText(this, "Registered click", Toast.LENGTH_SHORT).show();
    }

    public void inQueueClick() {
        mTxtBgQueue.setBackgroundResource(R.color.colorPrimary);
        mTxtBgRegistered.setBackgroundResource(android.R.color.transparent);

        if (isRegistered) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment, new InQueueFragment()).commit();
            isRegistered = false;
        }
        Toast.makeText(this, "In Queue click", Toast.LENGTH_SHORT).show();
    }

    public void setPatientSelected(Patient patientSelected) {
        this.patientSelected = patientSelected;
    }

    public Patient getPatientSelected() {
        return patientSelected;
    }

    public void updateRegisteredPatient(int num){
        addPatientInQueue();
        removeRegisteredPatient(num);

        reload();
        inQueueClick();
    }

    public void addPatientInQueue(){
        data.addPatientInQueue(getPatientSelected());
    }

    public void removeRegisteredPatient(int position){
        data.removeRegisteredPatient(position);
    }

    public List<Patient> getPatientsInQueueList(){
        return data.sortPatientByTimeInQueue(data.getPatientListInQueue());
    }

    public List<Patient> getRegisteredPatientList() {
        return registeredPatientList;
    }
}
