package com.example.contesta.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HomeFragment extends Fragment {
    private HomeRecyclerViewAdapter adapter;
    private Bitmap bitmap;
    private RecyclerView recyclerView;
    public static List<String> listImg = Arrays.asList(
            "https://image.fmkorea.com/files/attach/new/20190317/486616/291138520/1674527678/ffdee79774242891404ace7d985437c9.jpg",
            "https://t1.daumcdn.net/cfile/tistory/2616FD35574F9F522C",
            "https://post-phinf.pstatic.net/MjAxOTExMjVfMjM4/MDAxNTc0NjYyNjQ2NTM2.cSL6cHs7ik7qmot2AvzQLnmk8G-DgADCbC7OTFHiLjAg.5j0IKYZRsgZuPhubncXbLkjcq8sRpIyNUsXmRXed4Bwg.JPEG/%EB%89%B4%EC%8A%A41%EC%86%8C%EB%86%8D%EB%AF%BC.jpg?type=w1200");

    public static List<String> listUrl = Arrays.asList(
            "https://https://www.google.co.kr/",
            "https://https://www.google.co.kr/",
            "https://https://www.google.co.kr/");
    public static List<String> listDate = Arrays.asList(
            "2019-10-24",
            "2012-12-31",
            "2019-11-26");

    public static List<String> listTitle = Arrays.asList(
            "Messy-Go-Round",
            "Pro",
            "Son");
    public static List<String> listName = Arrays.asList(
            "이진성",
            "강지훈",
            "손흥민");

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
        final String url1 = "<div><div class=\"\" style=\"opacity: 1; top: 100px;\"><a class=\"\" href=\"/events/795\"><div class=\"EventCard__CardHeader-sc-1fkxjid-1 yOjMy\"><div src=\"https://cf.festa.io/img/2019-12-9/374a79e7-95a3-4384-bec4-edbf54876751.jpg\" class=\"EventCard__CardHeaderImage-sc-1fkxjid-2 ddIGmL\"></div></div><div class=\"EventCard__CardInner-sc-1fkxjid-3 kiBYon\"><div class=\"EventCard__CardBody-sc-1fkxjid-4 dAmmLk\"><span class=\"EventCard__TimeRow-sc-1fkxjid-6 uEHrF\"><time title=\"2019년 12월 23일 오후 7:30\" datetime=\"2019-12-23T10:30:00.000Z\">2019년 12월 23일 오후 7:30</time></span><h3 class=\"EventCard__Title-sc-1fkxjid-7 lpbKDy\">개발자, 한 달에 책 한 권 읽기 2019년 12월 모임</h3><a class=\"EventCard__HostLink-sc-1fkxjid-8 bXcNKJ\" href=\"/hosts/528\"><span class=\"EventCard__HostName-sc-1fkxjid-9 bAxXfi\">달랩</span></a></div><div class=\"EventCard__CardFooter-sc-1fkxjid-5 fKosKl\"><hr class=\"HorizontalRule-va4njg-0 EventCard__StyledHR-sc-1fkxjid-11 cezkbn\"><span class=\"EventCard__Fee-sc-1fkxjid-10 bhnBuv\">₩6,000</span></div></div></a></div></div>";
        final String url2 = "<div><div class=\"\" style=\"opacity: 1; top: 100px;\"><a class=\"EventCard__Card-sc-1fkxjid-0 fXEyPd\" href=\"/events/794\"><div class=\"EventCard__CardHeader-sc-1fkxjid-1 yOjMy\"><div src=\"https://cf.festa.io/img/2019-12-9/4271d927-e55e-4a95-9467-2eee0a3437e2.jpg\" class=\"EventCard__CardHeaderImage-sc-1fkxjid-2 bIZzsj\"></div></div><div class=\"EventCard__CardInner-sc-1fkxjid-3 kiBYon\"><div class=\"EventCard__CardBody-sc-1fkxjid-4 dAmmLk\"><span class=\"EventCard__TimeRow-sc-1fkxjid-6 uEHrF\"><time title=\"2019년 12월 10일 오후 7:00\" datetime=\"2019-12-10T10:00:00.000Z\">2019년 12월 10일 오후 7:00</time></span><h3 class=\"EventCard__Title-sc-1fkxjid-7 lpbKDy\">코딩 도장 #9</h3><a class=\"EventCard__HostLink-sc-1fkxjid-8 bXcNKJ\" href=\"/hosts/528\"><span class=\"EventCard__HostName-sc-1fkxjid-9 bAxXfi\">달랩</span></a></div><div class=\"EventCard__CardFooter-sc-1fkxjid-5 fKosKl\"><hr class=\"HorizontalRule-va4njg-0 EventCard__StyledHR-sc-1fkxjid-11 cezkbn\"><span class=\"EventCard__Fee-sc-1fkxjid-10 bhnBuv\">₩5,000</span></div></div></a></div></div>";
        final String url3 = "<div><div class=\"\" style=\"opacity: 1; top: 100px;\"><a class=\"EventCard__Card-sc-1fkxjid-0 fXEyPd\" href=\"/events/793\"><div class=\"EventCard__CardHeader-sc-1fkxjid-1 yOjMy\"><div src=\"https://cf.festa.io/img/2019-12-7/66aef889-1642-4e7b-aa31-3c8cf91a0e0c.png\" class=\"EventCard__CardHeaderImage-sc-1fkxjid-2 hYLzJu\"></div></div><div class=\"EventCard__CardInner-sc-1fkxjid-3 kiBYon\"><div class=\"EventCard__CardBody-sc-1fkxjid-4 dAmmLk\"><span class=\"EventCard__TimeRow-sc-1fkxjid-6 uEHrF\"><time title=\"2019년 12월 19일 오후 7:30\" datetime=\"2019-12-19T10:30:00.000Z\">2019년 12월 19일 오후 7:30</time></span><h3 class=\"EventCard__Title-sc-1fkxjid-7 lpbKDy\">제2회 스포카콘: Grow Together!</h3><a class=\"EventCard__HostLink-sc-1fkxjid-8 bXcNKJ\" href=\"/hosts/301\"><span class=\"EventCard__HostName-sc-1fkxjid-9 bAxXfi\">Spoqa</span></a></div><div class=\"EventCard__CardFooter-sc-1fkxjid-5 fKosKl\"><hr class=\"HorizontalRule-va4njg-0 EventCard__StyledHR-sc-1fkxjid-11 cezkbn\"><span class=\"EventCard__Fee-sc-1fkxjid-10 bhnBuv\">무료</span></div></div></a></div></div>";
        final String url4 = "<div><div class=\"\" style=\"opacity: 1; top: 100px;\"><a class=\"EventCard__Card-sc-1fkxjid-0 fXEyPd\" href=\"/events/791\"><div class=\"EventCard__CardHeader-sc-1fkxjid-1 yOjMy\"><div src=\"https://cf.festa.io/img/2019-12-6/2f959e85-82e8-4700-86dc-6c8d7a62cb66.jpg\" class=\"EventCard__CardHeaderImage-sc-1fkxjid-2 gipgHm\"></div></div><div class=\"EventCard__CardInner-sc-1fkxjid-3 kiBYon\"><div class=\"EventCard__CardBody-sc-1fkxjid-4 dAmmLk\"><span class=\"EventCard__TimeRow-sc-1fkxjid-6 uEHrF\"><time title=\"2019년 12월 10일 오후 7:30\" datetime=\"2019-12-10T10:30:00.000Z\">2019년 12월 10일 오후 7:30</time></span><h3 class=\"EventCard__Title-sc-1fkxjid-7 lpbKDy\">[Do it! 딥러닝] 스터디 2기 3차</h3><a class=\"EventCard__HostLink-sc-1fkxjid-8 bXcNKJ\" href=\"/hosts/588\"><span class=\"EventCard__HostName-sc-1fkxjid-9 bAxXfi\">홍대 머신러닝 스터디</span></a></div><div class=\"EventCard__CardFooter-sc-1fkxjid-5 fKosKl\"><hr class=\"HorizontalRule-va4njg-0 EventCard__StyledHR-sc-1fkxjid-11 cezkbn\"><span class=\"EventCard__Fee-sc-1fkxjid-10 bhnBuv\">₩10,000</span></div></div></a></div></div>";
        final String url5 = "<div><div class=\"\" style=\"opacity: 1; top: 100px;\"><a class=\"EventCard__Card-sc-1fkxjid-0 fXEyPd\" href=\"/events/788\"><div class=\"EventCard__CardHeader-sc-1fkxjid-1 yOjMy\"><div src=\"https://cf.festa.io/img/2019-12-4/a001abb2-4807-441f-b1aa-dd1870f57d50.jpg\" class=\"EventCard__CardHeaderImage-sc-1fkxjid-2 gRCDwE\"></div></div><div class=\"EventCard__CardInner-sc-1fkxjid-3 kiBYon\"><div class=\"EventCard__CardBody-sc-1fkxjid-4 dAmmLk\"><span class=\"EventCard__TimeRow-sc-1fkxjid-6 uEHrF\"><time title=\"2019년 12월 16일 오후 7:00\" datetime=\"2019-12-16T10:00:00.000Z\">2019년 12월 16일 오후 7:00</time></span><h3 class=\"EventCard__Title-sc-1fkxjid-7 lpbKDy\">월간 GDG: 광주 12월호 (2019)</h3><a class=\"EventCard__HostLink-sc-1fkxjid-8 bXcNKJ\" href=\"/hosts/605\"><span class=\"EventCard__HostName-sc-1fkxjid-9 bAxXfi\">GDG Gwangju</span></a></div><div class=\"EventCard__CardFooter-sc-1fkxjid-5 fKosKl\"><hr class=\"HorizontalRule-va4njg-0 EventCard__StyledHR-sc-1fkxjid-11 cezkbn\"><span class=\"EventCard__Fee-sc-1fkxjid-10 bhnBuv\">무료</span></div></div></a></div></div>";
        final String url6 = "<div><div class=\"\" style=\"opacity: 1; top: 100px;\"><a class=\"EventCard__Card-sc-1fkxjid-0 fXEyPd\" href=\"/events/787\"><div class=\"EventCard__CardHeader-sc-1fkxjid-1 yOjMy\"><div src=\"https://cf.festa.io/img/2019-12-4/58f6db7b-83ed-4c95-9ba9-60549b1696ae.jpg\" class=\"EventCard__CardHeaderImage-sc-1fkxjid-2 jJZplP\"></div></div><div class=\"EventCard__CardInner-sc-1fkxjid-3 kiBYon\"><div class=\"EventCard__CardBody-sc-1fkxjid-4 dAmmLk\"><span class=\"EventCard__TimeRow-sc-1fkxjid-6 uEHrF\"><time title=\"2019년 12월 16일 오후 1:30\" datetime=\"2019-12-16T04:30:00.000Z\">2019년 12월 16일 오후 1:30</time></span><h3 class=\"EventCard__Title-sc-1fkxjid-7 lpbKDy\">2019 넥스트 콘텐츠 비즈니스 컨퍼런스</h3><a class=\"EventCard__HostLink-sc-1fkxjid-8 bXcNKJ\" href=\"/hosts/718\"><span class=\"EventCard__HostName-sc-1fkxjid-9 bAxXfi\">뉴스토마토</span></a></div><div class=\"EventCard__CardFooter-sc-1fkxjid-5 fKosKl\"><hr class=\"HorizontalRule-va4njg-0 EventCard__StyledHR-sc-1fkxjid-11 cezkbn\"><span class=\"EventCard__Fee-sc-1fkxjid-10 bhnBuv\">₩33,000</span></div></div></a></div></div>";
        final String url7 = "<div><div class=\"\" style=\"opacity: 1; top: 100px;\"><a class=\"EventCard__Card-sc-1fkxjid-0 fXEyPd\" href=\"/events/786\"><div class=\"EventCard__CardHeader-sc-1fkxjid-1 yOjMy\"><div src=\"https://cf.festa.io/img/2019-12-4/349cb540-7896-4462-af37-3d9c9fe2afdc.jpg\" class=\"EventCard__CardHeaderImage-sc-1fkxjid-2 hGIxaD\"></div></div><div class=\"EventCard__CardInner-sc-1fkxjid-3 kiBYon\"><div class=\"EventCard__CardBody-sc-1fkxjid-4 dAmmLk\"><span class=\"EventCard__TimeRow-sc-1fkxjid-6 uEHrF\"><time title=\"2019년 12월 21일 오후 1:00\" datetime=\"2019-12-21T04:00:00.000Z\">2019년 12월 21일 오후 1:00</time></span><h3 class=\"EventCard__Title-sc-1fkxjid-7 lpbKDy\">Hands on! PWA + Azure workshop</h3><a class=\"EventCard__HostLink-sc-1fkxjid-8 bXcNKJ\" href=\"/hosts/313\"><span class=\"EventCard__HostName-sc-1fkxjid-9 bAxXfi\">조은</span></a></div><div class=\"EventCard__CardFooter-sc-1fkxjid-5 fKosKl\"><hr class=\"HorizontalRule-va4njg-0 EventCard__StyledHR-sc-1fkxjid-11 cezkbn\"><span class=\"EventCard__Fee-sc-1fkxjid-10 bhnBuv\">₩5,000</span></div></div></a></div></div>";
        final String url8 = "<div><div class=\"\" style=\"opacity: 1; top: 100px;\"><a class=\"EventCard__Card-sc-1fkxjid-0 fXEyPd\" href=\"/events/785\"><div class=\"EventCard__CardHeader-sc-1fkxjid-1 yOjMy\"><div src=\"https://cf.festa.io/img/2019-12-5/cd6f8ae6-546d-4a76-ae7d-79debb5faf6b.png\" class=\"EventCard__CardHeaderImage-sc-1fkxjid-2 iZUPnd\"></div></div><div class=\"EventCard__CardInner-sc-1fkxjid-3 kiBYon\"><div class=\"EventCard__CardBody-sc-1fkxjid-4 dAmmLk\"><span class=\"EventCard__TimeRow-sc-1fkxjid-6 uEHrF\"><time title=\"2019년 12월 18일 오후 7:00\" datetime=\"2019-12-18T10:00:00.000Z\">2019년 12월 18일 오후 7:00</time></span><h3 class=\"EventCard__Title-sc-1fkxjid-7 lpbKDy\">애자일코리아 12월 밋업 - \"2019 기년회\"</h3><a class=\"EventCard__HostLink-sc-1fkxjid-8 bXcNKJ\" href=\"/hosts/260\"><span class=\"EventCard__HostName-sc-1fkxjid-9 bAxXfi\">애자일 코리아 밋업 (Agile Korea Meetup)</span></a></div><div class=\"EventCard__CardFooter-sc-1fkxjid-5 fKosKl\"><hr class=\"HorizontalRule-va4njg-0 EventCard__StyledHR-sc-1fkxjid-11 cezkbn\"><span class=\"EventCard__Fee-sc-1fkxjid-10 bhnBuv\">₩10,000</span></div></div></a></div></div>";

        Document doc = Jsoup.parse(url1);
        Document doc1 = Jsoup.parse(url2);
        Document doc2 = Jsoup.parse(url3);
        Document doc3 = Jsoup.parse(url4);
        Document doc4 = Jsoup.parse(url5);
        Document doc5 = Jsoup.parse(url6);
        Document doc6 = Jsoup.parse(url7);
        Document doc7 = Jsoup.parse(url8);

        // 1번
        Elements title = doc.select("h3");
        System.out.println("title:" + title.text());

        Elements date = doc.select("time");
        System.out.println("date:" + date.text());

        Elements eve_url = doc.select("a[href]");
        String urls = "https://festa.io" + eve_url.attr("href");
        System.out.println("event_url:" + urls);

        Elements name = doc.select("a > span");
        System.out.println("name:" + name.text());

        Elements src = doc.select("div[src]");
        String imgs = src.attr("src");
        System.out.println("img_src: " + imgs);

        // 2번
        Elements title1 = doc1.select("h3");
        System.out.println("title1:" + title1.text());

        Elements date1 = doc1.select("time");
        System.out.println("date1:" + date1.text());

        Elements eve_url1 = doc1.select("a[href]");
        String urls1 = "https://festa.io" + eve_url1.attr("href");
        System.out.println("event_url:" + urls1);

        Elements name1 = doc1.select("a > span");
        System.out.println("name:" + name1.text());

        Elements src1 = doc1.select("div[src]");
        String imgs1 = src1.attr("src");
        System.out.println("img_src: " + imgs1);

        // 3번
        Elements title2 = doc2.select("h3");
        System.out.println("title2:" + title2.text());

        Elements date2 = doc2.select("time");
        System.out.println("date2:" + date2.text());

        Elements eve_url2 = doc2.select("a[href]");
        String urls2 = "https://festa.io" + eve_url2.attr("href");
        System.out.println("event_url:" + urls2);

        Elements name2 = doc2.select("a > span");
        System.out.println("name:" + name2.text());

        Elements src2 = doc2.select("div[src]");
        String imgs2 = src2.attr("src");
        System.out.println("img_src: " + imgs2);

        // 4번
        Elements title3 = doc3.select("h3");
        System.out.println("title3:" + title3.text());

        Elements date3 = doc3.select("time");
        System.out.println("date3:" + date3.text());

        Elements eve_url3 = doc3.select("a[href]");
        String urls3 = "https://festa.io" + eve_url3.attr("href");
        System.out.println("event_url:" + urls3);

        Elements name3 = doc3.select("a > span");
        System.out.println("name:" + name3.text());

        Elements src3 = doc3.select("div[src]");
        String imgs3 = src3.attr("src");
        System.out.println("img_src: " + imgs3);

        // 5번
        Elements title4 = doc4.select("h3");
        System.out.println("title4:" + title4.text());

        Elements date4 = doc4.select("time");
        System.out.println("date4:" + date4.text());

        Elements eve_url4 = doc4.select("a[href]");
        String urls4 = "https://festa.io" + eve_url4.attr("href");
        System.out.println("event_url:" + urls4);

        Elements name4 = doc4.select("a > span");
        System.out.println("name:" + name4.text());

        Elements src4 = doc4.select("div[src]");
        String imgs4 = src4.attr("src");
        System.out.println("img_src: " + imgs4);

        // 6번
        Elements title5 = doc5.select("h3");
        System.out.println("title5:" + title5.text());

        Elements date5 = doc5.select("time");
        System.out.println("date5:" + date5.text());

        Elements eve_url5 = doc5.select("a[href]");
        String urls5 = "https://festa.io" + eve_url5.attr("href");
        System.out.println("event_url:" + urls5);

        Elements name5 = doc5.select("a > span");
        System.out.println("name:" + name5.text());

        Elements src5 = doc5.select("div[src]");
        String imgs5 = src5.attr("src");
        System.out.println("img_src: " + imgs5);

        // 7번
        Elements title6 = doc6.select("h3");
        System.out.println("title6:" + title6.text());

        Elements date6 = doc6.select("time");
        System.out.println("date6:" + date6.text());

        Elements eve_url6 = doc6.select("a[href]");
        String urls6 = "https://festa.io" + eve_url6.attr("href");
        System.out.println("event_url:" + urls6);

        Elements name6 = doc6.select("a > span");
        System.out.println("name:" + name6.text());

        Elements src6 = doc6.select("div[src]");
        String imgs6 = src6.attr("src");
        System.out.println("img_src: " + imgs6);

        // 8번
        Elements title7 = doc7.select("h3");
        System.out.println("title7:" + title7.text());

        Elements date7 = doc7.select("time");
        System.out.println("date7:" + date7.text());

        Elements eve_url7 = doc7.select("a[href]");
        String urls7 = "https://festa.io" + eve_url7.attr("href");
        System.out.println("event_url:" + urls7);

        Elements name7 = doc7.select("a > span");
        System.out.println("name:" + name7.text());

        Elements src7 = doc7.select("div[src]");
        String imgs7 = src7.attr("src");
        System.out.println("img_src: " + imgs7);

        List<String> listImg = Arrays.asList(
                imgs.toString(),
                imgs1.toString(),
                imgs2.toString(),
                imgs3.toString(),
                imgs4.toString(),
                imgs5.toString(),
                imgs6.toString(),
                imgs7.toString()
                );
        List<String> listUrl = Arrays.asList(
                urls.toString(),
                urls1.toString(),
                urls2.toString(),
                urls3.toString(),
                urls4.toString(),
                urls5.toString(),
                urls6.toString(),
                urls7.toString()
        );
        List<String> listDate = Arrays.asList(
                date.text().toString(),
                date1.text().toString(),
                date2.text().toString(),
                date3.text().toString(),
                date4.text().toString(),
                date5.text().toString(),
                date6.text().toString(),
                date7.text().toString()
                );
        List<String> listTitle = Arrays.asList(
                title.text().toString(),
                title1.text().toString(),
                title2.text().toString(),
                title3.text().toString(),
                title4.text().toString(),
                title5.text().toString(),
                title6.text().toString(),
                title7.text().toString()
                );
        List<String> listName = Arrays.asList(
                name.text().toString(),
                name1.text().toString(),
                name2.text().toString(),
                name3.text().toString(),
                name4.text().toString(),
                name5.text().toString(),
                name6.text().toString(),
                name7.text().toString());

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

