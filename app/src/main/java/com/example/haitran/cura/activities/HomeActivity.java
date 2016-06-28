package com.example.haitran.cura.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.haitran.cura.R;
import com.example.haitran.cura.adapters.PatientAdapter;
import com.example.haitran.cura.models.Patient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private Toolbar mToolbarHome;
    private TextView mTitleToolbar, mTxtCountRegistered, mTxtCountQueue, mTxtRegistered, mTxtInQueue,
            mTxtDateCurrent, mTxtCountPatient, mTxtBgRegistered, mTxtBgQueu;

    private RecyclerView recycler_patient;

    private PatientAdapter adapter;
    private List<Patient> patients = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWidgets();

        mTitleToolbar.setText("Home");
        setSupportActionBar(mToolbarHome);

//        final ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowHomeEnabled(true);
//        actionBar.setIcon(R.drawable.ic_home);

        Calendar cal = Calendar.getInstance();
        mTxtDateCurrent.setText(cal.getTime().getDate() + "/" + cal.getTime().getMonth() + "/" + cal.getTime().getYear());
        prepareData();
        mTxtCountPatient.setText(patients.size() + "");

        mTxtRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeredClicK();
            }
        });

        mTxtInQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inQueueClicK();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void getWidgets() {
        mToolbarHome = (Toolbar) findViewById(R.id.toolbar_home);
        mTitleToolbar = (TextView) findViewById(R.id.txt_title_toolbar_home);
        mTxtCountRegistered = (TextView) findViewById(R.id.txt_count_registered_home);
        mTxtBgRegistered = (TextView) findViewById(R.id.txt_bg_registered_home);
        mTxtCountQueue = (TextView) findViewById(R.id.txt_count_queue_home);
        mTxtBgQueu = (TextView) findViewById(R.id.txt_bg_queue_home);
        mTxtDateCurrent = (TextView) findViewById(R.id.txt_today_home);
        mTxtCountPatient = (TextView) findViewById(R.id.txt_count_patient_home);
        recycler_patient = (RecyclerView) findViewById(R.id.recycler_view_home);

        mTxtRegistered = (TextView) findViewById(R.id.txt_registered_home);
        mTxtInQueue = (TextView) findViewById(R.id.txt_in_queue_home);
    }

    public void prepareData() {
        patients.add(new Patient("10000" + 0, "abc 1", 77, "000000000" + 0, 0, "xyz 1", 1));
        patients.add(new Patient("10000" + 1, "abc 2", 89, "000000000" + 1, 1, "xyz 2", 0));
        patients.add(new Patient("10000" + 2, "abc 3", 77, "000000000" + 2, 0, "xyz 3", 2));
        patients.add(new Patient("10000" + 3, "abc 4", 27, "000000000" + 3, 0, "xyz 4", 1));
        patients.add(new Patient("10000" + 4, "abc 5", 19, "000000000" + 4, 1, "xyz 5", 0));
        patients.add(new Patient("10000" + 5, "abc 6", 47, "000000000" + 5, 0, "xyz 6", 2));

        adapter = new PatientAdapter(this, patients);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler_patient.setLayoutManager(mLayoutManager);
        recycler_patient.setAdapter(adapter);
    }

    public void registeredClicK(){
        mTxtBgRegistered.setBackgroundResource(R.drawable.bg_text_view);
        mTxtBgQueu.setBackgroundResource(android.R.color.transparent);

        //getList()
    }
    public void inQueueClicK(){
        mTxtBgQueu.setBackgroundResource(R.drawable.bg_text_view);
        mTxtBgRegistered.setBackgroundResource(android.R.color.transparent);

        //getList()
    }

}
