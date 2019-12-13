package com.example.contesta.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contesta.Model.DataBase;
import com.example.contesta.R;


public class PopupActivity extends Activity {
    private ImageView Pimg;
    private TextView Pname, Ptitle, Pdate, PmemoBox;
    private String urlstr;
    TextView txtText;
    private EditText Pmemo;
    private int idx;


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
        Pmemo = (EditText)findViewById(R.id.memo);
        PmemoBox = (TextView)findViewById(R.id.memoBox);

        //UI 객체생성
        Intent intent = getIntent();
        String[] data = intent.getStringArrayExtra("clickData");
        Pdate.setText(data[0]);
        Ptitle.setText(data[1]);
        Pname.setText(data[2]);
        urlstr = data[3];
        idx = Integer.parseInt(data[4]);
        Log.d("url", "onCreate: "+urlstr);
        Pimg.setImageBitmap(DataBase.getCkImg());
        if(DataBase.memo[idx] != null && DataBase.memo[idx] != "") PmemoBox.setText(DataBase.memo[idx]);
        else PmemoBox.setText("메모를 입력해요!");
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

    public void onClick(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlstr)));
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
        if (Pmemo.getText().toString().length() != 0) DataBase.memo[idx] = Pmemo.getText().toString();
        finish();
    }
//    @Override
//    public void onBackPressed() {
//        //안드로이드 백버튼 막기
//        return;
//    }
}