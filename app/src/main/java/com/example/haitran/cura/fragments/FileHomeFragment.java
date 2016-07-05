package com.example.haitran.cura.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haitran.cura.R;
import com.example.haitran.cura.activities.CameraActivity;
import com.example.haitran.cura.views.adapters.RecyclerFileHomeAdapter;
import com.example.haitran.cura.views.adapters.RecyclerPatientDetailAdapter;
import com.software.shell.fab.ActionButton;
import com.software.shell.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kha.phan on 7/1/2016.
 */
public class FileHomeFragment extends Fragment {
    private View mLayout;
    private RecyclerView mRecyclerView;
    private List<String> mList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.getSupportActionBar().setTitle("Files");
        appCompatActivity.getSupportActionBar().setSubtitle(null);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appCompatActivity.getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        View view =inflater.inflate(R.layout.fragment_file_home,container,false);
        ActionButton floatingActionButton = (ActionButton) view.findViewById(R.id.action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CameraActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getWidgets(view);
    }
    private void getWidgets(View view){
        mLayout = (View) view.findViewById(R.id.layout_full_screen);
        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        mList = new ArrayList<String>();
        mList.add("Sugar Logbook");
        mList.add("External Labs");
        mList.add("Physical Exam");
        mList.add("In patient Note");
        mList.add("Medicines");
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_file_home);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        int heightToolBar = appCompatActivity.getSupportActionBar().getHeight();
        int heightMargin = (int)dipToPixels(getContext(),20f);
        height = height - (heightToolBar + heightMargin*5);
        //height = 600;
        int width = displayMetrics.widthPixels;
        Log.d("+++@height", "getWidgets: " + height);
        RecyclerFileHomeAdapter recyclerFileHomeAdapter= new RecyclerFileHomeAdapter(getContext(),(AppCompatActivity)getActivity(),mList,height);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(recyclerFileHomeAdapter);

    }
    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }
    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("PAGE_FILE_HOME");
                    if (fragment != null) {
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.transition.sli_re_in, R.transition.sli_re_out);
                        fragmentTransaction.remove(fragment).commit();
                        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
                        appCompatActivity.getSupportActionBar().setTitle("Patient Detail:");
                        appCompatActivity.getSupportActionBar().setSubtitle("Mr Kha");
                        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        appCompatActivity.getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
                    }
                    return true;
                }
                return false;
            }
        });
    }
}
