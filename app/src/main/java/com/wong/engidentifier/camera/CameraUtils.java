package com.wong.engidentifier.camera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;


import com.wong.engidentifier.utils.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author WongKyunban
 * description
 * created at 2018-12-29 下午6:57
 * @version 1.0
 */
public class CameraUtils {


    public static void openSysCamera(Activity context, int resultCode) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        context.startActivityForResult(cameraIntent, resultCode);
    }

    /**
     * get pictures
     */
    public static Bitmap getPicture(Intent data) {
        if (data == null) return null;

        Bundle bundle = data.getExtras();
        if (bundle != null) {
            return (Bitmap) bundle.get("data");
        } else {
            return null;
        }

    }

    /**
     * 保存方法
     */
    public static void saveBitmap(Context context, String picName, Bitmap bm) {
        File f = new File(FileUtils.getAppCache(context), picName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存方法
     */
    public static void saveBitmapIntoExternal(Context context, String picName, Bitmap bm) {
        File f = new File(FileUtils.getSmartAppCacheDir(context), picName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
