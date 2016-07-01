package com.example.haitran.cura.views.adapters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.haitran.cura.R;

import java.util.List;

/**
 * Created by kha.phan on 7/1/2016.
 */
public class RecyclerFileHomeAdapter extends RecyclerView.Adapter<RecyclerFileHomeAdapter.MyViewHolder> {

    Context mContext;
    AppCompatActivity mAppCompatActivity;
    List<String> mList;
    int mHeight;

    public RecyclerFileHomeAdapter(Context mContext, AppCompatActivity mAppCompatActivity, List<String> mList, int height) {
        this.mContext = mContext;
        this.mAppCompatActivity = mAppCompatActivity;
        this.mList = mList;
        mHeight = height;
    }

    @Override
    public RecyclerFileHomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_file_home, parent, false);

        return new MyViewHolder(view,mHeight);
    }

    @Override
    public void onBindViewHolder(RecyclerFileHomeAdapter.MyViewHolder holder, int position) {
        holder.setData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageFolder;
        TextView textFolder;
        View view;
        public MyViewHolder(View itemView,int height) {
            super(itemView);
            view = itemView;
            imageFolder = (ImageView)view.findViewById(R.id.image_folder);
            textFolder = (TextView) view.findViewById(R.id.text_folder_name);
            view.getLayoutParams().height= height/5;
            view.requestLayout();
        }
        public void setData(String detail){
            textFolder.setText(detail);
            imageFolder.setBackgroundResource(R.drawable.ic_image_folder);

        }
    }
}
