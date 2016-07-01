package com.example.haitran.cura.others;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tuan.huynh on 6/30/2016.
 */
public class PhotoHandler implements PictureCallback {

    private final Context context;

    public PhotoHandler(Context context) {
        this.context = context;
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {

        Bitmap bitmap = BitmapFactory.decodeByteArray(data , 0, data.length);
//        File pictureFileDir = getDir();
//
//        if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
//
//            Toast.makeText(context, "Can't create directory to save image.",
//                    Toast.LENGTH_LONG).show();
//            return;
//
//        }
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyddMMyyyyHHmmss");
//        String date = dateFormat.format(new Date());
//        String photoFile = "Picture_" + date + ".jpg";
//
//        String filename = pictureFileDir.getPath() + File.separator + photoFile;
//
//        File pictureFile = new File(filename);
//
//        try {
//            FileOutputStream fos = new FileOutputStream(pictureFile);
//            fos.write(data);
//            fos.close();
//            Toast.makeText(context, "New Image saved:" + photoFile,
//                    Toast.LENGTH_LONG).show();
//        } catch (Exception error) {
//            Toast.makeText(context, "Image could not be saved.",
//                    Toast.LENGTH_LONG).show();
//        }
    }

    private File getDir() {
        File sdDir = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return sdDir;
    }
}