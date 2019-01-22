package com.wong.engidentifier.utils;

import android.content.Context;
import android.os.Environment;

import com.wong.engidentifier.baidu.FileUtil;

import java.io.File;
import java.util.UUID;

/**
 * @author WongKyunban
 * description
 * created at 2018-12-29 下午7:00
 * @version 1.0
 */
public class FileUtils {


    /**
     * get the path of the cache of android
     */
    public static File getAppCache(Context context) {
        return context.getCacheDir();
    }

    /**
     * get the path which consist of the app cache path and custom path
     *
     * @param context context
     * @param subPath sub path
     * @return
     */
    public static File getAppCache(Context context, String subPath) {
        String path = context.getCacheDir() + File.separator + subPath;
        File file = new File(path);
        if (!file.exists() || !file.isDirectory()) {
            if (file.mkdirs()) {
                return file;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * get the path of the cache of android
     */
    public static File getAppFilesDir(Context context) {
        return context.getFilesDir();
    }



    /**
     * get external storage path
     * @param context
     * @return
     */
    public static File getExternalStorage(Context context) {
        return Environment.getExternalStorageDirectory();
    }

    /**
     * get external storage path which consist of subpath and external storage path
     * @param context
     * @param subPath
     * @return
     */
    public static File getExternalStorage(Context context, String subPath) {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + subPath);
        if (file.exists() && file.isDirectory()){
            return file;
        }else{
            if (file.mkdirs()) {
                return file;
            } else {
                return null;
            }
        }
    }

    /**
     * get application cache if sd cark did not exist
     * @param context
     * @return
     */
    public static File getSmartAppCacheDir(Context context){

        if(checkSDCardAvailable()){
            return getExternalStorage(context,context.getPackageName());

        }else{
            return getAppCache(context,context.getPackageName());
        }

    }

    public static File getSmartAppCacheFile(Context context,String fileName){
        if(checkSDCardAvailable()){
            return getExternalStorageFile(context,context.getPackageName()+File.separator+fileName);

        }else{
            return getAppCacheFile(context,context.getPackageName()+File.separator+fileName);
        }
    }
    /**
     * 判断当前存储卡是否可用
     **/
    public static boolean checkSDCardAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * get file of app cache
     * @param context
     * @param fileName
     * @return
     */
    public static File getAppCacheFile(Context context,String fileName){

        String path = getAppCache(context).getAbsolutePath()+File.separator+fileName;
        File file = new File(path);
        if(file.exists() && file.isFile()){
            return file;
        }
        return null;
    }

    /**
     * get file from external storage
     * @param context
     * @param fileName
     * @return
     */
    public static File getExternalStorageFile(Context context,String fileName){
        String path = getExternalStorage(context)+File.separator + fileName;

        File file = new File(path);
        if(file.exists() && file.isFile()){
            return file;
        }
        return null;

    }



    /**
     * use the suffix which is from the parameter and the UUID to split joint a file name
     * @param suffix
     * @return
     */
    public static String generateFileName(String suffix){
        return UUID.randomUUID().toString()+"."+suffix;
    }


    public static void clear(File file) {
        if(file == null) return;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                clear(f);
            }
            file.delete();
            //如要保留文件夹，只删除文件，请注释这行
        } else if (file.exists()) {
            file.delete();
        }
    }


}
