package com.example.contesta.Login;

import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity {
    private static Boolean Login = false; // change this!
    private static String id = "";

    public static void setId(String id) {
        LoginActivity.id = id;
    }

    public static String getId() {
        return id;
    }

    public static void setLogin(Boolean log) {
        Login = log;
    }
    public static Boolean getLogin() {
        return Login;
    }
}
