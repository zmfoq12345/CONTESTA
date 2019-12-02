package com.example.contesta.main;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contesta.Adapter.HomeRecyclerViewAdapter;
import com.example.contesta.Model.Data;
import com.example.contesta.Model.DataBase;
import com.example.contesta.R;

import java.util.Arrays;
import java.util.List;

public class BookmarkFragment extends Fragment {
    private HomeRecyclerViewAdapter adapter;
    private Bitmap bitmap;
    private RecyclerView recyclerView;
    private Button btnStar;
    public BookmarkFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("ct", "Create");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);
        recyclerView = view.findViewById(R.id.recycler_bookmark);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new HomeRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        btnStar = view.findViewById(R.id.btn_bookmark);

        getData();
        return view;
    }
    private void getData() {
        String[][] str = DataBase.getStr();
        Bitmap[] img = DataBase.getImg();
        for (int i = 0; i < DataBase.getIdx(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            if (str[i][3] == "true"){
                Data data = new Data();
                data.setStr(str[i][0], str[i][1], str[i][2], str[i][3]);
                data.setImg(img[i]);
                // 각 값이 들어간 data를 adapter에 추가합니다.
                adapter.addItem(data);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
