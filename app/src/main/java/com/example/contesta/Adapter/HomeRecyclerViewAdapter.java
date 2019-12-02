package com.example.contesta.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contesta.Model.Data;
import com.example.contesta.Model.DataBase;
import com.example.contesta.R;
import com.example.contesta.main.MainActivity;
import com.example.contesta.util.PopupActivity;

import java.util.ArrayList;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ItemViewHolder> {
    private String[] strli;
    // adapter에 들어갈 list 입니다.
    private ArrayList<Data> listData = new ArrayList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    public void addItem(Data data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView name, title, date;
        private ImageView img;
        private FrameLayout fl;
        private ImageView btnStar;
        private boolean flag;


        ItemViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            img = itemView.findViewById(R.id.img);
            btnStar = itemView.findViewById(R.id.btn_bookmark);
            fl = itemView.findViewById(R.id.ViewFrame);

            fl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
                    final String[] textli = new String[3];
                    textli[0] = name.getText().toString();
                    textli[1] = title.getText().toString();
                    textli[2] = date.getText().toString();

                    Intent intent = new Intent(view.getContext(), PopupActivity.class);
                    intent.putExtra("clickData", textli);
                    DataBase.setCkImg(drawable.getBitmap());
                    view.getContext().startActivity(intent);
                }
            });

            btnStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i < DataBase.getIdx(); i++) {
                        if (DataBase.confInfo[i][1] == title.getText().toString() && DataBase.confInfo[i][2] == name.getText().toString()){
                            if (DataBase.confInfo[i][3] == "true"){
                                btnStar.setImageResource(R.drawable.binstar);
                                DataBase.confInfo[i][3] = "false";
                            }
                            else{
                                btnStar.setImageResource(R.drawable.fillstar);
                                DataBase.confInfo[i][3] = "true";
                            }

                        }
//                MainActivity.transaction.detach(MainActivity.bookmarkFragment).attach(MainActivity.bookmarkFragment).commit();

                    }
                }
            });
        }

        void onBind(Data data) {
            strli = data.getStr();
            date.setText(strli[0]);
            title.setText(strli[1]);
            name.setText(strli[2]);
            img.setImageBitmap(data.getImg());
            if (strli[3] == "true"){
                btnStar.setImageResource(R.drawable.fillstar);
            }
        }
    }
}