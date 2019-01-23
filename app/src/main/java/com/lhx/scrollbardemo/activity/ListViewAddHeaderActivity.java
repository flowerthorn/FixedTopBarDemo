package com.lhx.scrollbardemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.lhx.scrollbardemo.R;
import com.lhx.scrollbardemo.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lihongxin on 2019/1/18
 */
public class ListViewAddHeaderActivity extends AppCompatActivity {


    @BindView(R.id.lv)
    ListView listView;
    @BindView(R.id.rl_inside_fixed)
    RelativeLayout rlInsideFixed;
    @BindView(R.id.fl_outside_fixed)
    FrameLayout flOutSideBar;

    private View mHeaderView;
    private View mTopBarView;
    private ListViewAdapter mAdapter;
    private List<String> list;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, ListViewAddHeaderActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ButterKnife.bind(this);
        initList();
        initView();
    }

    private void initView() {


        mHeaderView = LayoutInflater.from(this).inflate(R.layout.header, listView, false);
        mTopBarView = LayoutInflater.from(this).inflate(R.layout.inside_fixed_bar, listView, false);

        listView.addHeaderView(mHeaderView);
        listView.addHeaderView(mTopBarView);
        mAdapter = new ListViewAdapter(list);
        listView.setAdapter(mAdapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                /* 判断ListView头部(mHeaderView)当前是否可见
                 * 来决定隐藏或显示浮动栏(mFloatBar)*/
                if (firstVisibleItem >= 1) {
                    flOutSideBar.setVisibility(View.VISIBLE);
                } else {
                    flOutSideBar.setVisibility(View.GONE);
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
