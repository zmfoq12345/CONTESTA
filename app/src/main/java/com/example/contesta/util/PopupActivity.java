package com.example.contesta.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contesta.Model.DataBase;
import com.example.contesta.R;

public class PopupActivity extends Activity {
    private ImageView Pimg;
    private TextView Pname, Ptitle, Pdate;
    TextView txtText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        Pimg = (ImageView)findViewById(R.id.popup_img);
        Pdate = (TextView)findViewById(R.id.popup_date);
        Ptitle = (TextView)findViewById(R.id.popup_title);
        Pname = (TextView)findViewById(R.id.popup_name);

        //UI 객체생성
        Intent intent = getIntent();
        String[] data = intent.getStringArrayExtra("clickData");
        Pdate.setText(data[0]);
        Ptitle.setText(data[1]);
        Pname.setText(data[2]);
        Pimg.setImageBitmap(DataBase.getCkImg());
    }
    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    public void mOnBack(View v){
        finish();
    }
//    @Override
//    public void onBackPressed() {
//        //안드로이드 백버튼 막기
//        return;
//    }
}