package com.example.haitran.cura.fragments;

import android.content.Intent;
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

import java.util.ArrayList;
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
                intent.putExtra("FILE_FOLDER", 2);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_file_folder);
        loadData(subtitle);
        RecyclerFileFolderAdapter recyclerFileFolderAdapter = new RecyclerFileFolderAdapter(getContext(),(AppCompatActivity)getActivity(),mList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(recyclerFileFolderAdapter);

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
                    Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("PAGE_FILE_FOLDER");
                    if (fragment != null) {
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.transition.sli_re_in, R.transition.sli_re_out);
                        fragmentTransaction.remove(fragment).commit();
                        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
                        appCompatActivity.getSupportActionBar().setTitle("Files");
                        appCompatActivity.getSupportActionBar().setSubtitle(null);
                        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        appCompatActivity.getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
                    }
                    return true;
                }
                return false;
            }
        });
    }
    private void loadData(String filename){

        String [] today = {"http://hinhnendep.pro/wp-content/uploads/2016/03/hinh-anh-hot-girl-dep-nhat-tren-facebook-2.jpg",
                "http://hinhnendep.pro/wp-content/uploads/2016/03/hinh-anh-hot-girl-dep-nhat-tren-facebook-2.jpg"};

        ImageDay imageDay= new ImageDay("To day",today);
        String [] June = {"http://hinhnendep.pro/wp-content/uploads/2016/03/hinh-anh-hot-girl-dep-nhat-tren-facebook-2.jpg",
                "http://hinhnendep.pro/wp-content/uploads/2016/03/hinh-anh-hot-girl-dep-nhat-tren-facebook-2.jpg",
                "http://hinhnendep.pro/wp-content/uploads/2016/03/hinh-anh-hot-girl-dep-nhat-tren-facebook-2.jpg"};
        ImageDay imageDay1 = new ImageDay("16 June 2016",June);
        String [] May = {"http://hinhnendep.pro/wp-content/uploads/2016/03/hinh-anh-hot-girl-dep-nhat-tren-facebook-2.jpg",
                "http://hinhnendep.pro/wp-content/uploads/2016/03/hinh-anh-hot-girl-dep-nhat-tren-facebook-2.jpg",
                "http://hinhnendep.pro/wp-content/uploads/2016/03/hinh-anh-hot-girl-dep-nhat-tren-facebook-2.jpg",
                "http://hinhnendep.pro/wp-content/uploads/2016/03/hinh-anh-hot-girl-dep-nhat-tren-facebook-2.jpg"};
        ImageDay imageDay2 = new ImageDay("08 May 2016", May);
        mList = new ArrayList<ImageDay>();
        mList.add(imageDay);
        mList.add(imageDay1);
        mList.add(imageDay2);
    }
}
