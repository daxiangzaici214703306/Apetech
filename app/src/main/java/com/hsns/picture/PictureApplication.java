package com.hsns.picture;

import android.app.Application;
import android.content.Context;

public class PictureApplication extends Application {
    private static Context pictureApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        pictureApplication = getApplicationContext();
    }

    public static Context getApplication() {
        return pictureApplication;
    }
}
