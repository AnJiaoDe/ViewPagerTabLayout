package com.cy.viewpagertablayout;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cy.vptablayout.CYTabLayout;
import com.cy.vptablayout.RVAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<Fragment> list_fragment;
    private ViewPager viewPager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private CYTabLayout cyTabLayout;

    private RVAdapter<String> rvAdapter;
    private List<String> list_title;

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
        list_title.add("title0");
        list_title.add("title1");
        list_title.add("title2");
        list_title.add("title3");
        list_title.add("title4");
        list_title.add("title5");
        list_title.add("title6");
        list_title.add("title7");


        viewPager = (ViewPager) findViewById(R.id.vp);


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

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                cyTabLayout.getRecyclerView().scrollToPosition(position);
                rvAdapter.setSelectedPosition(position);

                LinearLayoutManager linearLayoutManager= (LinearLayoutManager) cyTabLayout.getRecyclerView().getLayoutManager();



                cyTabLayout.getView_indicator().scrollTo(100,0);
//                translate(cyTabLayout.getView_indicator(),linearLayoutManager.getChildAt(position).getLeft());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

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

                cyTabLayout.getView_indicator().scrollTo(dx, 0);
            }
        });


    }

    public void translate(View v,int x) {
//      TranslateAnimation ta = new TranslateAnimation(-100, 100, 0, 0);
//      ta.setDuration(2000);
//      ta.setFillAfter(true);
//      iv.startAnimation(ta);
        //创建属性动画师
        //arg0:要操作的对象
        //arg1:要修改的属性的名字

        ObjectAnimator oa1 = ObjectAnimator.ofFloat(v, "translationX", x);
        oa1.setDuration(100);
        oa1.start();
    }
}
