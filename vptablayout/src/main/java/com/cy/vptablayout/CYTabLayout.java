package com.cy.vptablayout;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by leifeng on 2018/8/2.
 */

public class CYTabLayout extends LinearLayout {
    private RecyclerView recyclerView;
    private View view_indicator;

    public CYTabLayout(Context context) {
        this(context, null);
    }

    public CYTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        setOverScrollMode(OVER_SCROLL_NEVER);

        recyclerView = new RecyclerView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0);
        layoutParams.weight=1;
        recyclerView.setLayoutParams(layoutParams);

        view_indicator = new View(context);
        view_indicator.setLayoutParams(new LinearLayout.LayoutParams(100, 10));
        view_indicator.setBackgroundColor(0xffffff00);

        addView(recyclerView);
        addView(view_indicator);

    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }


    public View getView_indicator() {
        return view_indicator;
    }

}
