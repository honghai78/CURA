package com.example.haitran.cura.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haitran.cura.R;
import com.example.haitran.cura.activities.CameraActivity;
import com.example.haitran.cura.objects.ImageDay;
import com.example.haitran.cura.views.adapters.RecyclerFileFolderAdapter;
import com.software.shell.fab.ActionButton;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kha.phan on 7/4/2016.
 */
public class FileFolderFragment extends Fragment {
    private View mLayout;
    private RecyclerView mRecyclerView;
    private List<ImageDay> mList;
    private String subtitle="";

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.getSupportActionBar().setTitle("Files :");
        appCompatActivity.getSupportActionBar().setSubtitle(subtitle);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appCompatActivity.getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        return inflater.inflate(R.layout.fragment_file_folder,container,false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getWidgets(view);
    }

    private void getWidgets(View view) {
        mLayout = (View) view.findViewById(R.id.layout_full_screen);
        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        ActionButton floatingActionButton = (ActionButton) view.findViewById(R.id.action_camera_folder);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CameraActivity.class);
                getActivity().startActivity(intent);
            }
        });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_file_folder);
        //loadDataForInternet(subtitle);
        loadDataLocal("Labs");
        RecyclerFileFolderAdapter recyclerFileFolderAdapter = new RecyclerFileFolderAdapter(getContext(),(AppCompatActivity)getActivity(),mList);
        recyclerFileFolderAdapter.setIsLocal(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(recyclerFileFolderAdapter);

    }
    private void loadDataForInternet(String filename){
        String url = "http://hinhnendep.pro/wp-content/uploads/2016/03/hinh-anh-hot-girl-dep-nhat-tren-facebook-2.jpg";
        ArrayList<String> today = new ArrayList<String>();
        today.add(url);
        today.add(url);

        ImageDay imageDay= new ImageDay("To day",today);
        ArrayList<String> June = new ArrayList<String>();
        June.add(url);
        June.add(url);
        June.add(url);
        ImageDay imageDay1 = new ImageDay("16 June 2016",June);
        ArrayList<String> May = new ArrayList<String>();
        May.add(url);
        May.add(url);
        May.add(url);
        May.add(url);
        ImageDay imageDay2 = new ImageDay("08 May 2016", May);
        mList = new ArrayList<ImageDay>();
        mList.add(imageDay);
        mList.add(imageDay1);
        mList.add(imageDay2);
    }
    private void loadDataLocal(String filename){
        ArrayList<String> f = new ArrayList<String>();
        File file= new File(android.os.Environment.getExternalStorageDirectory(),filename);
        File[] listFile;
        if (file.isDirectory())
        {
            listFile = file.listFiles();
            for (int i = 0; i < listFile.length; i++)
            {
                f.add(listFile[i].getAbsolutePath());
            }
        }
        ArrayList<String> arrDayImage = new ArrayList<String>();
        for (String path : f){
            File filePath = new File(path);
            Date lastModDate = new Date(file.lastModified());
            String date = new SimpleDateFormat("dd MMMM yyyy").format(lastModDate);
            if (!isExist(arrDayImage,date)){
                arrDayImage.add(date);
            }
        }
        ArrayList<String> arrayPathImageDay = new ArrayList<String>();
        for (String day: arrDayImage){
            for (String path : f){
                File filePath = new File(path);
                Date lastModDate = new Date(file.lastModified());
                String date = new SimpleDateFormat("dd MMMM yyyy").format(lastModDate);
                if (day.equals(date)){
                    arrayPathImageDay.add(path);
                }
            }
            ImageDay imageDay = new ImageDay(day,arrayPathImageDay);
            mList = new ArrayList<ImageDay>();
            mList.add(imageDay);
        }
    }
    private boolean isExist(ArrayList<String> arrayList,String day){
        for (String dayList :arrayList){
            if (day.equals(dayList)) return true;
        }
        return false;
    }
}
