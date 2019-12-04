package com.example.contesta.Model;

import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class Data {
    public static int markIdx = 0;
    private String date, title, name, mark, url;
    private Bitmap img;

    public void setStr(String str1, String str2, String str3, String mark1, String url1) {
        date = str1 ;
        title = str2 ;
        name = str3 ;
        mark = mark1;
        url = url1;
        markIdx+=1;
    }
    public String[] getStr() {
        String[] strli = new String[5];
        strli[0] = date;
        strli[1] = title;
        strli[2] = name;
        strli[3] = mark;
        strli[4] = url;
        return strli;
    }

    public void setImg(Bitmap img1) {
        img = img1;
    }
    public Bitmap getImg() {
        return img;
    }
}
