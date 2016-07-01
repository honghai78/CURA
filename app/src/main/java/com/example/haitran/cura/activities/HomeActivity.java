package com.example.haitran.cura.activities;

import android.content.DialogInterface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.astuetz.PagerSlidingTabStrip;

import com.example.haitran.cura.R;
import com.example.haitran.cura.adapters.HomePagerAdapter;
import com.example.haitran.cura.data.MyData;
import com.example.haitran.cura.models.Patient;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    //final Context context = this;
    private HomePagerAdapter homePagerAdapter;
    private ViewPager viewPagerHome;
    private PagerSlidingTabStrip pagerTabStripHome;

    private Patient patientSelected;
    private List<Patient> registeredPatientList;
    private List<Patient> patientsInQueueList;
    private MyData data;
    private String[] mTitlesOfTab;

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
        patientsInQueueList = data.sortPatientByTimeInQueue(data.getPatientListInQueue());

        mTitlesOfTab = new String[]{"Registered (" + registeredPatientList.size() + ")", "In Queue (" + patientsInQueueList.size() + ")"};
        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), mTitlesOfTab);
        viewPagerHome.setAdapter(homePagerAdapter);
        viewPagerHome.setPageTransformer(true, new RotateUpTransformer());

        pagerTabStripHome.setAllCaps(false);
        pagerTabStripHome.setViewPager(viewPagerHome);


    }

    public void reload() {
        registeredPatientList = data.getRegisteredPatientList();
        patientsInQueueList = data.sortPatientByTimeInQueue(data.getPatientListInQueue());

        mTitlesOfTab = new String[]{"Registered\n (" + registeredPatientList.size() + ")", "In Queue\n (" + patientsInQueueList.size() + ")"};
//        homePagerAdapter.setTitles(mTitlesOfTab);
//        homePagerAdapter.notifyDataSetChanged();
        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), mTitlesOfTab);
        viewPagerHome.setAdapter(homePagerAdapter);
        viewPagerHome.setPageTransformer(true, new RotateUpTransformer());

        pagerTabStripHome.setAllCaps(false);
        pagerTabStripHome.setViewPager(viewPagerHome);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_search:
                Toast.makeText(getBaseContext(), "Search clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_about:
                //createDialogToShowChoseFolder();
                showChoiceFolderToSave();
                //Toast.makeText(getBaseContext(), "About clicked", Toast.LENGTH_SHORT).showChoiceFolderToSave();
                return true;
        }
        return true;
    }

    public void showChoiceFolderToSave(){
        new AlertDialog.Builder(this)
                .setTitle(R.string.select_folder)
                .setSingleChoiceItems(R.array.array_folder, 0, null)
                .setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                        // Do something useful withe the position of the selected radio button
                    }
                })
                .show();
    }

//    public void createDialogToShowChoseFolder() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = this.getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.layout_chose_folder_to_save, null);
//        builder.setView(dialogView);
//        AlertDialog dialog = builder.create();
//        dialog.showChoiceFolderToSave();
//
//        CheckBox checkBox1 = (CheckBox) dialogView.findViewById(R.id.chk_external_labs);
//        CheckBox checkBox2 = (CheckBox) dialogView.findViewById(R.id.chk_sugar_logbook);
//        CheckBox checkBox3 = (CheckBox) dialogView.findViewById(R.id.chk_physical_exam);
//        CheckBox checkBox4 = (CheckBox) dialogView.findViewById(R.id.chk_in_patient_notes);
//        CheckBox checkBox5 = (CheckBox) dialogView.findViewById(R.id.chk_medicines);
//
//        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (checkBox1.isChecked()) {
//                    checkBox2.setEnabled(false);
//                    checkBox3.setEnabled(false);
//                    checkBox4.setEnabled(false);
//                    checkBox5.setEnabled(false);
//                } else {
//                    checkBox2.setEnabled(true);
//                    checkBox3.setEnabled(true);
//                    checkBox4.setEnabled(true);
//                    checkBox5.setEnabled(true);
//                }
//            }
//        });
//        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (checkBox2.isChecked()) {
//                    checkBox1.setEnabled(false);
//                    checkBox3.setEnabled(false);
//                    checkBox4.setEnabled(false);
//                    checkBox5.setEnabled(false);
//                } else {
//                    checkBox1.setEnabled(true);
//                    checkBox3.setEnabled(true);
//                    checkBox4.setEnabled(true);
//                    checkBox5.setEnabled(true);
//                }
//            }
//        });
//        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (checkBox3.isChecked()) {
//                    checkBox2.setEnabled(false);
//                    checkBox1.setEnabled(false);
//                    checkBox4.setEnabled(false);
//                    checkBox5.setEnabled(false);
//                } else {
//                    checkBox2.setEnabled(true);
//                    checkBox1.setEnabled(true);
//                    checkBox4.setEnabled(true);
//                    checkBox5.setEnabled(true);
//                }
//            }
//        });
//        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (checkBox4.isChecked()) {
//                    checkBox2.setEnabled(false);
//                    checkBox3.setEnabled(false);
//                    checkBox1.setEnabled(false);
//                    checkBox5.setEnabled(false);
//                } else {
//                    checkBox2.setEnabled(true);
//                    checkBox3.setEnabled(true);
//                    checkBox1.setEnabled(true);
//                    checkBox5.setEnabled(true);
//                }
//            }
//        });
//        checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (checkBox5.isChecked()) {
//                    checkBox2.setEnabled(false);
//                    checkBox3.setEnabled(false);
//                    checkBox4.setEnabled(false);
//                    checkBox1.setEnabled(false);
//                } else {
//                    checkBox2.setEnabled(true);
//                    checkBox3.setEnabled(true);
//                    checkBox4.setEnabled(true);
//                    checkBox1.setEnabled(true);
//                }
//            }
//        });
//
//        Button btn_done = (Button) dialogView.findViewById(R.id.btn_done_dialog_select_folder);
//        btn_done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (checkBox1.isChecked() || checkBox2.isChecked() || checkBox3.isChecked() ||
//                        checkBox4.isChecked() || checkBox5.isChecked()) {
//
//                    Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).showChoiceFolderToSave();
//                    dialog.dismiss();
//
//                } else {
//                    Toast.makeText(getApplicationContext(), "Still Not check!", Toast.LENGTH_SHORT).showChoiceFolderToSave();
//                }
//            }
//        });
//    }


    public void getWidgets() {
        viewPagerHome = (ViewPager) findViewById(R.id.view_pager_home);
        pagerTabStripHome = (PagerSlidingTabStrip) findViewById(R.id.pager_tab_strip_home);
    }

    public void setPatientSelected(Patient patientSelected) {
        this.patientSelected = patientSelected;
    }

    public Patient getPatientSelected() {
        return patientSelected;
    }

    public void updateRegisteredPatient(int num) {
        addPatientInQueue();
        removeRegisteredPatient(num);

        reload();
    }

    public void addPatientInQueue() {
        data.addPatientInQueue(getPatientSelected());
    }

    public void removeRegisteredPatient(int position) {
        data.removeRegisteredPatient(position);
    }

    public List<Patient> getPatientsInQueueList() {
        return data.sortPatientByTimeInQueue(data.getPatientListInQueue());
    }

    public List<Patient> getRegisteredPatientList() {
        return registeredPatientList;
    }
}
