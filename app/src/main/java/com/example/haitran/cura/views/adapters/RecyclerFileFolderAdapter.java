package com.example.haitran.cura.views.adapters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haitran.cura.R;
import com.example.haitran.cura.objects.ImageDay;
import com.example.haitran.cura.views.InternetImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kha.phan on 7/4/2016.
 */
public class RecyclerFileFolderAdapter extends RecyclerView.Adapter<RecyclerFileFolderAdapter.MyViewHolder>  {

    Context mContext;
    AppCompatActivity mAppCompatActivity;
    List<ImageDay> mList;
    private boolean isLocal = false;

    public void setIsLocal(boolean isLocal) {
        this.isLocal = isLocal;
    }

    public RecyclerFileFolderAdapter(Context mContext, AppCompatActivity mAppCompatActivity, List<ImageDay> mList) {
        this.mContext = mContext;
        this.mAppCompatActivity = mAppCompatActivity;
        this.mList = mList;
    }

    @Override

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_file_folder, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textDay,textImage;
        InternetImageView imageFolder1,imageFolder2,imageFolder3;
        List<InternetImageView> listImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            textDay = (TextView) itemView.findViewById(R.id.text_day);
            textImage = (TextView) itemView.findViewById(R.id.text_image);
            imageFolder1 = (InternetImageView) itemView.findViewById(R.id.image_folder_1);
            imageFolder2 = (InternetImageView) itemView.findViewById(R.id.image_folder_2);
            imageFolder3 = (InternetImageView) itemView.findViewById(R.id.image_folder_3);
            listImage = new ArrayList<InternetImageView>();
            listImage.add(imageFolder1);
            listImage.add(imageFolder2);
            listImage.add(imageFolder3);
        }
        public void setData(ImageDay imageDay){
            textDay.setText(imageDay.getDay());
            if(imageDay.getUrlImage().length>3){
                for (int i=0;i<3;i++){
                    if (!isLocal) {
                        listImage.get(i).setImageAsync(imageDay.getUrlImage()[i]);
                    }else {

                    }
                }
                textImage.setText("+"+(imageDay.getUrlImage().length-2));
            }
            else if (imageDay.getUrlImage().length==3){
                for (int i=0;i<imageDay.getUrlImage().length;i++){
                    if (!isLocal) {
                        listImage.get(i).setImageAsync(imageDay.getUrlImage()[i]);
                    }else {

                    }
                }
                textImage.setVisibility(View.GONE);
            }else if(imageDay.getUrlImage().length<3){
                for (int i=0;i<imageDay.getUrlImage().length;i++){
                    if (!isLocal) {
                        listImage.get(i).setImageAsync(imageDay.getUrlImage()[i]);
                    }else {

                    }
                }
                for (int i=imageDay.getUrlImage().length; i<3; i++){
                    listImage.get(i).setVisibility(View.GONE);
                }
                textImage.setVisibility(View.GONE);
            }
        }

    }
}
