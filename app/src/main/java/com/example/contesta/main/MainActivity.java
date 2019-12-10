package com.example.contesta.main;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.contesta.R;
import com.example.contesta.Adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private HomeFragment homeFragment;
    public static BookmarkFragment bookmarkFragment;
    private ProfileFragment profileFragment;
    public static FragmentTransaction transaction;
    private ViewPager viewPager;
    private MenuItem prevMenuItem;
    private ViewPagerAdapter adapter;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(Color.TRANSPARENT);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager_main);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                transaction = fragmentManager.beginTransaction();
                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        viewPager.setCurrentItem(0);
                        transaction.detach(homeFragment).attach(homeFragment).commit();
                        break;
                    case R.id.menu_Bookmark:
                        viewPager.setCurrentItem(1);
//                        transaction.detach(bookmarkFragment).attach(bookmarkFragment).commit();
                        break;
                    case R.id.menu_profile:
                        viewPager.setCurrentItem(2);
                        transaction.detach(profileFragment).attach(profileFragment).commit();
                        break;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
//                adapter.notifyDataSetChanged();
                Log.d("page", "onPageSelected: " + position);
                if (position == 1){
                    transaction = fragmentManager.beginTransaction();
                    transaction.detach(bookmarkFragment).attach(bookmarkFragment).commit();
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);
//        heart = (ImageView)findViewById(R.id.heart);
//        heart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (ck == true){
//                    heart.setImageResource(R.drawable.binheart);
//                    ck = false;
//                }else{
//                    heart.setImageResource(R.drawable.fillheart);
//                    ck = true;
//                }
//            }
//        });
      /*  init();
        getData();*/
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        homeFragment = new HomeFragment();
        bookmarkFragment = new BookmarkFragment();
        profileFragment = new ProfileFragment();

        adapter.addFragment(homeFragment);
        adapter.addFragment(bookmarkFragment);
        adapter.addFragment(profileFragment);
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        if (id == R.id.cameraBtn) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
