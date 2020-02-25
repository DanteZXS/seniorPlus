package com.example.seniorplus;


import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import adaptar.AdapterMainViewPager;
import util.ImageManager;
import view.GalleryFragment;
import view.LayoutChats;

public class ActivityChatMain extends AppCompatActivity {


    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<TabLayout.Tab> tabList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_chat_main);

        initViews();
    }

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.vp_main);
        tabLayout = (TabLayout) findViewById(R.id.tl_main);

        tabList = new ArrayList<>();

        AdapterMainViewPager adapter = new AdapterMainViewPager(getSupportFragmentManager());

        adapter.addFragment(new LayoutChats());
        adapter.addFragment(new GalleryFragment());
//        adapter.addFragment(new LayoutMoments());

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        tabList.add(tabLayout.getTabAt(0));
        tabList.add(tabLayout.getTabAt(1));
//        tabList.add(tabLayout.getTabAt(2));
        tabList.get(0).setIcon(R.drawable.msgunselected).setText("Chats");
        tabList.get(1).setIcon(R.drawable.contactsunselected).setText("Gallery");
//        tabList.get(2).setIcon(R.drawable.momentunselected).setText("Moments");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabList.get(tab.getPosition()).setIcon(ImageManager.imageID[tab.getPosition() + 3]);
                tabLayout.setTabTextColors(
                        ContextCompat.getColor(ActivityChatMain.this, R.color.colorBlack),
                        ContextCompat.getColor(ActivityChatMain.this, R.color.colorBlue)
                );
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabList.get(tab.getPosition()).setIcon(ImageManager.imageID[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}