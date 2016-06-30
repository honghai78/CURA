package com.example.haitran.cura.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haitran.cura.R;
import com.example.haitran.cura.fragments.AuthenticationFragment;
import com.example.haitran.cura.objects.Personal;

import java.util.List;

/**
 * Created by hai.tran on 6/28/2016.
 */
public class ListviewSignInAdapter extends RecyclerView.Adapter<ListviewSignInAdapter.MyViewHolder> {

    Context mContext;
    private final static int FADE_DURATION = 800;
    AppCompatActivity mAppCompatActivity;
    List<Personal> mList;
    public ListviewSignInAdapter(Context context, List<Personal> list, AppCompatActivity appCompatActivity)
    {
        mContext=context;
        mList=list;
        mAppCompatActivity = appCompatActivity;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_listview_signin, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setData(mList.get(position));
        setFadeAnimation(holder.itemView);
    }

    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
       // super.onViewDetachedFromWindow(holder);
        holder.clearAnimation();
    }

    public void appendList(List<Personal> l) {

        this.mList.clear();
        this.mList.addAll(l);
        this.notifyDataSetChanged();
    }

    private void setFadeAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageView;
        public TextView textViewName, textViewSub;
        public View mView;
        public MyViewHolder(View v)
        {
            super(v);
            mView = v;
            imageView =(ImageView) v.findViewById(R.id.signin_image);
            textViewName = (TextView) v.findViewById(R.id.signin_textName);
            textViewSub = (TextView) v.findViewById(R.id.signin_textSub);
        }

        public void clearAnimation()
        {
            mView.clearAnimation();
        }

        public void setData(final Personal personal)
        {
            textViewName.setText(personal.getName());
            textViewSub.setText(personal.getLevel());
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AuthenticationFragment frag = new AuthenticationFragment();
                    frag.setNameEducator(personal.getName());
                    FragmentTransaction fragmentTransaction = mAppCompatActivity.getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.transition.slide_in, R.transition.slide_out);
                    fragmentTransaction.add(R.id.signin_line2, frag, "PAGE_2").commit();
                }
            });
        }
    }
}

