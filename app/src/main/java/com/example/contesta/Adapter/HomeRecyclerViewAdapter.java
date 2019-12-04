package com.example.contesta.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contesta.Login.LoginActivity;
import com.example.contesta.Model.Data;
import com.example.contesta.Model.DataBase;
import com.example.contesta.R;
import com.example.contesta.util.PopupActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        private TextView name, title, date, url;
        private ImageView img;
        private FrameLayout fl;
        private ImageView btnStar;
        private boolean flag;

        public Map<String, Object> toMap(View view) {
            HashMap<String, Object> result = new HashMap<>();
            BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
            result.put("Image", "https://image.fmkorea.com/files/attach/new/20190317/486616/291138520/1674527678/ffdee79774242891404ace7d985437c9.jpg");//getImageUri(view.getContext(), drawable.getBitmap()));
            result.put("Date", date);
            result.put("Title", title);
            result.put("Name", name);
            return result;
        }
        private Uri getImageUri(Context context, Bitmap inImage) {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            return Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null));
        }

        ItemViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            url = itemView.findViewById(R.id.url);
            img = itemView.findViewById(R.id.img);
            btnStar = itemView.findViewById(R.id.btn_bookmark);
            fl = itemView.findViewById(R.id.ViewFrame);

            fl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
                    final String[] textli = new String[4];
                    textli[0] = name.getText().toString();
                    textli[1] = title.getText().toString();
                    textli[2] = date.getText().toString();
                    textli[3] = url.getText().toString();

                    Intent intent = new Intent(view.getContext(), PopupActivity.class);
                    intent.putExtra("clickData", textli);
                    DataBase.setCkImg(drawable.getBitmap());
                    view.getContext().startActivity(intent);
                }
            });

            btnStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (LoginActivity.getLogin()){
                        for (int i = 0; i < DataBase.getIdx(); i++) {
//                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//                            Map<String, Object> childUpdates = new HashMap<>();
//                            childUpdates.put(LoginActivity.getId(), toMap(view));
//                            databaseReference.updateChildren(childUpdates);

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
                        }
                    }else{
                        Toast.makeText(view.getContext(), "로그인 후 이용 가능합니다!", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

        void onBind(Data data) {
            strli = data.getStr();
            date.setText(strli[0]);
            title.setText(strli[1]);
            name.setText(strli[2]);
            url.setText(strli[4]);
            img.setImageBitmap(data.getImg());
            if (strli[3] == "true"){
                btnStar.setImageResource(R.drawable.fillstar);
            }
        }
    }
}