package com.cy.viewpagertablayout;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cy.vptablayout.CYTabLayout;
import com.cy.vptablayout.RVAdapter;
import com.cy.vptablayout.TabViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<Fragment> list_fragment;
    private TabViewPager viewPager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private CYTabLayout cyTabLayout;

    private RVAdapter<String> rvAdapter;
    private List<String> list_title;

    private float positionOffset_last = 0;

    private int postion_current_last = 0;
    private int x_last_position_left = 0;
    private int offset_last = 0;
    private int position_last = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_fragment = new ArrayList<>();
        list_fragment.add(VPFragment.create("title0"));
        list_fragment.add(VPFragment.create("title1"));
        list_fragment.add(VPFragment.create("title2"));
        list_fragment.add(VPFragment.create("title3"));
        list_fragment.add(VPFragment.create("title4"));
        list_fragment.add(VPFragment.create("title5"));
        list_fragment.add(VPFragment.create("title6"));
        list_fragment.add(VPFragment.create("title7"));


        list_title = new ArrayList<>();
        list_title.add("胡歌0");
        list_title.add("霍建华1");
        list_title.add("屌丝胡2");
        list_title.add("美女3");
        list_title.add("彩笔4");
        list_title.add("坑逼5");
        list_title.add("撕逼6");
        list_title.add("傻逼7");


        viewPager = (TabViewPager) findViewById(R.id.vp);


        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list_fragment.get(position);
            }

            @Override
            public int getCount() {
                return list_fragment.size();
            }
        };
        viewPager.setAdapter(fragmentPagerAdapter);


        cyTabLayout = (CYTabLayout) findViewById(R.id.cytl);


        rvAdapter = new RVAdapter<String>(list_title) {
            @Override
            public void bindDataToView(RVViewHolder holder, int position, String bean, boolean isSelected) {

                if (isSelected) {
                    holder.setTextColor(R.id.tv, 0xffffff00);
                } else {
                    holder.setTextColor(R.id.tv, 0xffffffff);

                }
                holder.setText(R.id.tv, bean);
            }

            @Override
            public int getItemLayoutID(int position, String bean) {
                return R.layout.item_tab_tv;

            }

            @Override
            public void onItemClick(int position, String bean) {
                viewPager.setCurrentItem(position);

            }
        };


        cyTabLayout.setAdapter(rvAdapter);


        viewPager.setOnTabPageChangeListener(new TabViewPager.OnTabPageChangeListener() {
            @Override
            public void onPageScrolled(float dx, float downX, float downY, float moveX, float moveY) {

//                Log.e("dx", "__________________________" + dx);

//                cyTabLayout.getRecyclerView().smoothScrollBy(-(int) (dx+0.5f), 0);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.e("positionOffset", "__________________________" + positionOffset);
//                Log.e("positionOffset_last", "__________________________" + positionOffset_last);

//                Log.e("position", "+++++++++++++++++++++++++++" + position);
                LinearLayoutManager layoutManager = (LinearLayoutManager) cyTabLayout.getRecyclerView().getLayoutManager();
//
//
//                Log.e("width", "+++++++++++++++++++" + layoutManager.getChildAt(position).getWidth());


                int offset = (int) ((positionOffset) * layoutManager.getChildAt(position).getWidth());
                offset_last = (int) ((positionOffset_last) * layoutManager.getChildAt(position).getWidth());
                Log.e("offset", "__________________________" + offset);
                Log.e("offset_last", "__________________________" + offset_last);

                offset = offset - offset_last;

                if (offset<0){
                    cyTabLayout.getRecyclerView().scrollBy(0, 0);

                }else {

                    cyTabLayout.getRecyclerView().scrollBy(offset, 0);
                }
                positionOffset_last = positionOffset;

            }

            @Override
            public void onPageSelected(int position) {

//                cyTabLayout.getRecyclerView().scrollTo(100,0);
                LinearLayoutManager layoutManager = (LinearLayoutManager) cyTabLayout.getRecyclerView().getLayoutManager();


            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    Log.e("停止","++++++++++++++++++++++++");

                }

            }
        });

        cyTabLayout.getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });


    }

}
