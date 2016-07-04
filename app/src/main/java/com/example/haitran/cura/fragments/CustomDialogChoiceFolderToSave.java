package com.example.haitran.cura.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.haitran.cura.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanh.tran on 7/4/2016.
 */
public class CustomDialogChoiceFolderToSave extends Dialog implements CheckBox.OnCheckedChangeListener {

    private Activity mActivity;
    private byte[] bytes;
    private List<CheckBox> checkBoxList = new ArrayList<>();

    public CustomDialogChoiceFolderToSave(Activity mActivity, byte[] bytes) {
        super(mActivity);
        this.mActivity = mActivity;
        this.bytes = bytes;
    }

    public void onCreateDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_chose_folder_to_save, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();


        CheckBox checkBox1 = (CheckBox) dialogView.findViewById(R.id.chk_external_labs);
        CheckBox checkBox2 = (CheckBox) dialogView.findViewById(R.id.chk_sugar_logbook);
        CheckBox checkBox3 = (CheckBox) dialogView.findViewById(R.id.chk_physical_exam);
        CheckBox checkBox4 = (CheckBox) dialogView.findViewById(R.id.chk_in_patient_notes);
        CheckBox checkBox5 = (CheckBox) dialogView.findViewById(R.id.chk_medicines);

        checkBoxList.add(checkBox1);
        checkBoxList.add(checkBox2);
        checkBoxList.add(checkBox3);
        checkBoxList.add(checkBox4);
        checkBoxList.add(checkBox5);

//        set onCheckedChangeListener for checkbox
        for (int i = 0; i < checkBoxList.size(); i++) {
            checkBoxList.get(i).setOnCheckedChangeListener(this);
        }

        Button btn_done = (Button) dialogView.findViewById(R.id.btn_done_dialog_select_folder);
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCheck = false;
                for (int i = 0; i < checkBoxList.size(); i++)
                    if (checkBoxList.get(i).isChecked()) {
                        Bitmap bitmap = convertFromBytesToBitmap(bytes);
                        String imageName = checkBoxList.get(i).getText() + "-" + i;
                        String folderName = checkBoxList.get(i).getText().toString();
                        createDirectoryAndSaveFile(bitmap, imageName, folderName);

                        Toast.makeText(mActivity, "You choice checkbox " + (i + 1) + ":" + checkBoxList.get(i).getText(),
                                Toast.LENGTH_SHORT).show();
                        isCheck = true;
                        break;
                    }
                if (isCheck) {
                    dialog.dismiss();
                } else {
                    Toast.makeText(mActivity, "Still check yet!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        for (int i = 0; i < checkBoxList.size(); i++) {
            if (checkBoxList.get(i).isChecked()) {
                for (int j = 0; j < checkBoxList.size(); j++) {
                    if (i != j) {
                        checkBoxList.get(j).setEnabled(false);
                    }
                }
                break;
            } else {
                for (int j = 0; j < checkBoxList.size(); j++) {
                    if (i != j) {
                        checkBoxList.get(j).setEnabled(true);
                    }
                }
            }
        }
    }

    public Bitmap convertFromBytesToBitmap(byte[] bytes) {
        Bitmap background = BitmapFactory.decodeByteArray(bytes, 0,
                bytes.length);
        Bitmap bit = Bitmap.createBitmap(background.getWidth(),
                background.getHeight(), Bitmap.Config.ARGB_8888);
        return bit;
    }

    private void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName, String folderName) {

        File direct = mActivity.getDir(folderName, mActivity.MODE_PRIVATE);
//        direct = new File(direct, fileName);

//        File direct = new File(Environment.getExternalStorageDirectory() + "/" + folderName);

        if (!direct.exists()) {
//            File wallpaperDirectory = new File("/sdcard/" + folderName + "/");
//            wallpaperDirectory.mkdirs();
            File wallpaperDirectory = new File(folderName + "/");
            wallpaperDirectory.mkdirs();
        }

//        File file = new File(new File("/sdcard/" + folderName + "/"), fileName);
        File file = new File(new File(folderName + "/"), fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
