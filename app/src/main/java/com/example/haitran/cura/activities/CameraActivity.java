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
    private static final int FILE_FOLDER_START = 2;
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

                Fragment fragment = getSupportFragmentManager().findFragmentByTag("PAGE_PRE");
                android.app.Fragment fragment1 = getFragmentManager().findFragmentByTag("B");
                if (fragment != null) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    //fragmentTransaction.setCustomAnimations(R.transition.sli_re_in, R.transition.sli_re_out);
                    fragmentTransaction.remove(fragment).commit();
                    getSupportActionBar().hide();
                }
                if (fragment1 != null)
                    fragment1.onResume();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (fragmentStart == FILE_FOLDER_START){
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("CAMERA", 2);
            startActivity(intent);
            finish();
        }else {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("CAMERA", 1);
            startActivity(intent);
            finish();
        }
    }
}
