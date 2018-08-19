package com.cy.vptablayout;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by cy on 2018/8/19.
 */

public class TabViewPager extends ViewPager {
    private float downX;
    private float downY;

    private OnTabPageChangeListener onTabPageChangeListener;
    public TabViewPager(Context context) {
        this(context, null);
    }

    public TabViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();

                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();
                // 水平滑动

                if (Math.abs(moveY - downY) < Math.abs(moveX - downX)) {


                    if (onTabPageChangeListener!=null){
                        onTabPageChangeListener.onPageScrolled(moveX-downX,downX,downY,moveX,moveY);
                    }



                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        return super.onInterceptTouchEvent(event);

    }

    public void setOnTabPageChangeListener(OnTabPageChangeListener onTabPageChangeListener) {
        this.onTabPageChangeListener = onTabPageChangeListener;
    }

    public interface OnTabPageChangeListener {

        public void onPageScrolled(float dx,float downX,float downY,float moveX,float moveY);
    }
}
