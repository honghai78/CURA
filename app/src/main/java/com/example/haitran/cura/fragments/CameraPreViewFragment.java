package com.example.haitran.cura.fragments;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.haitran.cura.R;
import com.example.haitran.cura.activities.CameraActivity;

/**
 * Created by hai.tran on 7/1/2016.
 */
public class CameraPreViewFragment extends Fragment {
    private byte[] mData;
    private Bitmap mBitmap;
    private LinearLayout savePhoto;
    private ImageView mImageView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera_preview, container, false);
        mImageView = (ImageView) view.findViewById(R.id.pre_image);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        mImageView.setImageBitmap(Bitmap.createScaledBitmap(mBitmap, width, height * 8 / 10, true));
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Preview of Photo");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.pre_line);
      linearLayout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Log.d("saveIMage", "onClick: ");
              Toast.makeText(getActivity(),"click",Toast.LENGTH_SHORT).show();
              CustomDialogChoiceFolderToSave customDialogChoiceFolderToSave = new CustomDialogChoiceFolderToSave(getActivity(), mData);
              customDialogChoiceFolderToSave.show();
          }
      });

        ImageView imageView = (ImageView) view.findViewById(R.id.pre_delete);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CameraActivity) getActivity()).replace();
            }
        });
        savePhoto = (LinearLayout) view.findViewById(R.id.pre_line);
        savePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "OK", Toast.LENGTH_LONG).show();
                CustomDialogChoiceFolderToSave customDialogChoiceFolderToSave = new CustomDialogChoiceFolderToSave(getActivity(),mData);
                customDialogChoiceFolderToSave.onCreateDialog();

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public void setData(byte[] data)
    {
        mData = data;
        mBitmap = rotateImage(BitmapFactory.decodeByteArray(mData, 0, mData.length), 90);

    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Bitmap retVal;

        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        retVal = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);

        return retVal;
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
                    ((CameraActivity) getActivity()).replace();
                    return true;
                }
                return false;
            }
        });
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }


}
