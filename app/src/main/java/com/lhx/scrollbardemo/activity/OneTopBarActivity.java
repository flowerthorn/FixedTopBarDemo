package com.lhx.scrollbardemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lhx.scrollbardemo.R;
import com.lhx.scrollbardemo.adapter.NormalAdapter;
import com.lhx.scrollbardemo.util.ViewUtils;
import com.lhx.scrollbardemo.widget.MyScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lihongxin on 2019/1/21
 */
public class OneTopBarActivity extends AppCompatActivity {

    @BindView(R.id.ll_fixed_parent)
    LinearLayout llFixed;
    @BindView(R.id.inside_fixed_bar_parent)
    LinearLayout insideFixedBarParent;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.scrollView)
    MyScrollView scrollView;
    @BindView(R.id.rl_inside_fixed)
    RelativeLayout rlInsideFixed;


    private List<String> list;
    private NormalAdapter normalAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int topHeight;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, OneTopBarActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_bar);
        ButterKnife.bind(this);
        initList();
        initView();
    }

    private void initView() {
        topHeight = ViewUtils.dip2px(this, 200);

        linearLayoutManager = new LinearLayoutManager(this);
        normalAdapter = new NormalAdapter(list);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(normalAdapter);
        scrollView.setScrollChangeListener(new MyScrollView.ScrollChangedListener() {
            @Override
            public void onScrollChangedListener(int x, int y, int oldX, int oldY) {
                if (y >= topHeight) {
                    if (rlInsideFixed.getParent() != llFixed) {
                        insideFixedBarParent.removeView(rlInsideFixed);
                        llFixed.addView(rlInsideFixed);
                        recyclerView.setNestedScrollingEnabled(true);

                    }
                } else {
                    if (rlInsideFixed.getParent() != insideFixedBarParent) {
                        llFixed.removeView(rlInsideFixed);
                        insideFixedBarParent.addView(rlInsideFixed);
                        recyclerView.setNestedScrollingEnabled(false);
                    }
                }
            }
        });
    }

    private void initList() {
        list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(i + "");
        }
    }
}
