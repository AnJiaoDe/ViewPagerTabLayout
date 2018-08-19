package com.cy.vptablayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by leifeng on 2018/8/2.
 */

public class CYTabLayout extends LinearLayout {
    private TabRecyclerView recyclerView;
    //    private View view_indicator;
    private GradientDrawable mIndicatorDrawable = new GradientDrawable();

    private int postion_current=0;

    private Scroller scroller;
    public CYTabLayout(Context context) {
        this(context, null);
    }

    public CYTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        setOverScrollMode(OVER_SCROLL_NEVER);

        recyclerView = new TabRecyclerView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0);
        layoutParams.weight = 1;
        recyclerView.setLayoutParams(layoutParams);

//        view_indicator = new View(context);
//        view_indicator.setLayoutParams(new LinearLayout.LayoutParams(100, 10));
//        view_indicator.setBackgroundColor(0xffffff00);

        addView(recyclerView);
//        addView(view_indicator);

        scroller=new Scroller(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mIndicatorDrawable.setColor(0xffff0000);

        LinearLayoutManager layoutManager= (LinearLayoutManager) recyclerView.getLayoutManager();
//        mIndicatorDrawable.setBounds(layoutManager.getChildAt(postion_current).getLeft(),
//                layoutManager.getChildAt(postion_current).getBottom()-20,
//                layoutManager.getChildAt(postion_current).getRight(),
//                layoutManager.getChildAt(postion_current).getBottom()-10);
        mIndicatorDrawable.draw(canvas);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

    }

    public TabRecyclerView getRecyclerView() {
        return recyclerView;
    }


//    public View getView_indicator() {
//        return view_indicator;
//    }

    public int getPostion_current() {
        return postion_current;
    }

    public void setPostion_current(int postion_current) {
        this.postion_current = postion_current;
    }

    public Scroller getScroller() {
        return scroller;
    }
    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {// 如果正在计算的过程中
            // 更新滚动的位置
            scrollTo( scroller.getCurrX(),0);
            invalidate();
        }

    }
}
