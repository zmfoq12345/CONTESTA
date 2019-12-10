package com.example.contesta.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contesta.Model.DataBase;
import com.example.contesta.R;
import com.example.contesta.main.HomeFragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AddPopupActivity extends Activity {

    private ImageView Pimg;
    private Bitmap bitmap;
    private EditText Pname, Ptitle, Pdate, Purl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_addpopup);

        Pimg = (ImageView)findViewById(R.id.Img);

        Pdate = (EditText)findViewById(R.id.popup_date);
        Purl = (EditText)findViewById(R.id.popup_ImgUrl);
        Ptitle = (EditText)findViewById(R.id.popup_title);
        Pname = (EditText)findViewById(R.id.popup_name);

        Pimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pimg.setImageBitmap(UrlImg(Purl.getText().toString()));
            }
        });

    }
    public void mOnBack(View v){
        finish();
    }

    public void onClick(View v){
        HomeFragment.listDate.add(Pdate.getText().toString());
        HomeFragment.listImg.add(Purl.getText().toString());
        HomeFragment.listTitle.add(Ptitle.getText().toString());                  
        HomeFragment.listUrl.add("https://https://www.google.co.kr/");
        HomeFragment.listName.add(Pname.getText().toString());
        Toast.makeText(this, "등록 되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }
    private Bitmap UrlImg(final String strurl) {
        Thread mThread = new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(strurl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true); // 서버로 부터 응답 수신
                    conn.connect();

                    InputStream is = conn.getInputStream(); // InputStream 값 가져오기
                    bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 변환

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        mThread.start(); // Thread 실행

        try {
            mThread.join();
            return bitmap;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}