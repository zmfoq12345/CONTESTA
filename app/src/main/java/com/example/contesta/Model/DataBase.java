package com.example.contesta.Model;

import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DataBase {
    public static String[][] confInfo = new String[100][100];
    public static Bitmap[] confImg = new Bitmap[100];


    public static Bitmap clickImg;

    public static int idx = 0;

    public static void setCkImg(Bitmap bt){clickImg = bt;}
    public static Bitmap getCkImg(){return clickImg;}

    public static int getIdx() {
        return idx;
    }

    public static void setStr(String str1, String str2, String str3, String mark, String url) {
        confInfo[idx][0] = str1;
        confInfo[idx][1] = str2;
        confInfo[idx][2] = str3;
        confInfo[idx][3] = mark;
        confInfo[idx][4] = url;
    }
    public static void setImg(Bitmap img1) {
        confImg[idx++] = img1;
    }

    public static String[][] getStr() {
        return confInfo;
    }
    public static Bitmap[] getImg() {
        return confImg;
    }
}
