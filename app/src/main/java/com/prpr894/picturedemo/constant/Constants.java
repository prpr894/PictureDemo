package com.prpr894.picturedemo.constant;

import android.app.Activity;
import android.os.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2017/12/11 0011.
 * Description：
 */

public class Constants {
    public static Map<String, Class<? extends Activity>> classMap = new HashMap<>();

    public static void addActivityClass(String classActivityName, Class<? extends Activity> classActivivty) {
        if (classMap.get(classActivityName) == null) {
            classMap.put(classActivityName, classActivivty);
        }

    }
    public static final String BASE_PATH = Environment.getExternalStorageDirectory() + "/YuanQuanYiJiaQin/";
    public static final String PHOTO_PATH =BASE_PATH + "photo";
    public static final String DOWNLOAD_PATH =BASE_PATH + "download";
    public static final String DOWNLOAD_PATH_APK =BASE_PATH + "download/apk";
    public final static String APP_PATH_PICTURE = BASE_PATH + "picture";
}
