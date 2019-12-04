package com.example.contesta.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contesta.Adapter.HomeRecyclerViewAdapter;
import com.example.contesta.Model.Data;
import com.example.contesta.Model.DataBase;
import com.example.contesta.R;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    private HomeRecyclerViewAdapter adapter;
    private Bitmap bitmap;
    private RecyclerView recyclerView;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycler_main);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new HomeRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        getData();
        return view;
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

    private void getData() {
        List<String> listImg = Arrays.asList(
                "https://image.fmkorea.com/files/attach/new/20190317/486616/291138520/1674527678/ffdee79774242891404ace7d985437c9.jpg",
                "https://t1.daumcdn.net/cfile/tistory/2616FD35574F9F522C",
                "https://post-phinf.pstatic.net/MjAxOTExMjVfMjM4/MDAxNTc0NjYyNjQ2NTM2.cSL6cHs7ik7qmot2AvzQLnmk8G-DgADCbC7OTFHiLjAg.5j0IKYZRsgZuPhubncXbLkjcq8sRpIyNUsXmRXed4Bwg.JPEG/%EB%89%B4%EC%8A%A41%EC%86%8C%EB%86%8D%EB%AF%BC.jpg?type=w1200");
        List<String> listUrl = Arrays.asList(
                "https://https://www.google.co.kr/",
                "https://https://www.google.co.kr/",
                "https://https://www.google.co.kr/");
        List<String> listDate = Arrays.asList(
                "2019-10-24",
                "2012-12-31",
                "2019-11-26");
        List<String> listTitle = Arrays.asList(
                "Messy-Go-Round",
                "Pro",
                "Son");
        List<String> listName = Arrays.asList(
                "이진성",
                "강지훈",
                "손흥민");

        if (Data.markIdx == 0){
            for (int i = 0; i < listTitle.size(); i++) {
                // 각 List의 값들을 data 객체에 set 해줍니다.
                Data data = new Data();
                Log.d("tst", "getData: " + listName.get(i));
                data.setStr(listDate.get(i), listTitle.get(i), listName.get(i), "false", listUrl.get(i));
                data.setImg(UrlImg(listImg.get(i)));

                DataBase.setStr(listDate.get(i), listTitle.get(i), listName.get(i), "false", listUrl.get(i));
                DataBase.setImg(UrlImg(listImg.get(i)));
                // 각 값이 들어간 data를 adapter에 추가합니다.
                adapter.addItem(data);
            }

            // adapter의 값이 변경되었다는 것을 알려줍니다.

            adapter.notifyDataSetChanged();
        }
        else{
            String[][] str = DataBase.getStr();
            Bitmap[] img = DataBase.getImg();
            for (int i = 0; i < DataBase.getIdx(); i++) {
                // 각 List의 값들을 data 객체에 set 해줍니다.
                Data data = new Data();
                data.setStr(str[i][0], str[i][1], str[i][2], str[i][3], str[i][4]);
                data.setImg(img[i]);
                // 각 값이 들어간 data를 adapter에 추가합니다.
                adapter.addItem(data);
            }
            adapter.notifyDataSetChanged();
        }


    }
}

