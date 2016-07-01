package com.example.haitran.cura.views.adapters;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haitran.cura.R;
import com.example.haitran.cura.fragments.AuthenticationFragment;
import com.example.haitran.cura.fragments.FileHomeFragment;
import com.example.haitran.cura.objects.Personal;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by kha.phan on 6/30/2016.
 */
public class RecyclerPatientDetailAdapter extends RecyclerView.Adapter<RecyclerPatientDetailAdapter.MyViewHolder> {

    Context mContext;
    private final static int FADE_DURATION = 500;
    AppCompatActivity mAppCompatActivity;
    List<String> mList;

    public RecyclerPatientDetailAdapter(Context mContext, AppCompatActivity mAppCompatActivity, List<String> mList) {
        this.mContext = mContext;
        this.mAppCompatActivity = mAppCompatActivity;
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_patient_detail, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setData(mList.get(position));
        setFadeAnimation(holder.itemView);
    }
    private void setFadeAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        // super.onViewDetachedFromWindow(holder);
        holder.clearAnimation();
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView textDetail;
        public View view;
        public MyViewHolder(View itemView) {
            super(itemView);
            view =itemView;
            textDetail = (TextView) view.findViewById(R.id.text_patient_detail);
        }
        public void setData(String detail){
            textDetail.setText(detail);
            textDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((TextView)v).getText()=="Files") {
                        FileHomeFragment frag = new FileHomeFragment();
                        FragmentTransaction fragmentTransaction = mAppCompatActivity.getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.transition.slide_in, R.transition.slide_out);
                        fragmentTransaction.add(R.id.layout_home, frag, "PAGE_FILE_HOME").commit();
                    }
                }
            });
        }

        public void clearAnimation() {
            view.clearAnimation();
        }
    }
}
