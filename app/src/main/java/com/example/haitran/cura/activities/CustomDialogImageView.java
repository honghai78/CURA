package com.example.haitran.cura.activities;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haitran.cura.R;

/**
 * Created by hongh on 7/4/2016.
 */
public class CustomDialogImageView extends Dialog {

    private Activity mActivity;
    private Dialog mDialog;
    private ImageView mImageView, mDelete;
  private Bitmap mBitmap;

    public CustomDialogImageView(Activity activity, Bitmap bitmap) {
        super(activity);
        this.mActivity = activity;
        this.mBitmap = bitmap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog_image_view);
        mImageView = (ImageView) findViewById(R.id.imageview_dialog);
        mImageView.setImageBitmap(mBitmap);
        mDelete = (ImageView) findViewById(R.id.bt_delete_dialog);
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}