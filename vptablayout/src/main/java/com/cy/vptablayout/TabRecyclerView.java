package com.cy.vptablayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Scroller;

/**
 * Created by cy on 2018/8/11.
 */

public class TabRecyclerView extends RecyclerView {
    private Scroller scroller;
    private Context context;

    public TabRecyclerView(Context context) {
        this(context, null, 0);
    }

    public TabRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        scroller = new Scroller(context);

    }



    public Scroller getScroller() {
        return scroller;
    }
    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {// 如果正在计算的过程中
            // 更新滚动的位置
            scrollTo(scroller.getCurrX(),0);
            invalidate();
        }

    }
}
