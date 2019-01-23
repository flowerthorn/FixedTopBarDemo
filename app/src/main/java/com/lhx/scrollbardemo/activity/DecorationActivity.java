package com.lhx.scrollbardemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.lhx.scrollbardemo.R;
import com.lhx.scrollbardemo.adapter.NormalAdapter;
import com.lhx.scrollbardemo.widget.FixedBarDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lihongxin on 2019/1/22
 */
public class DecorationActivity extends AppCompatActivity {
    @BindView(R.id.rv_list)
    RecyclerView recyclerView;
    private View headerView;
    private List<String> list;
    private NormalAdapter normalAdapter;
    private LinearLayoutManager linearLayoutManager;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, DecorationActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoration);
        ButterKnife.bind(this);
        initList();
        initView();
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(this);
        normalAdapter = new NormalAdapter(list);
        recyclerView.addItemDecoration(new FixedBarDecoration(this));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(normalAdapter);
        headerView = LayoutInflater.from(this).inflate(R.layout.header, recyclerView, false);
        normalAdapter.setmHeaderView(headerView);


    }

    private void initList() {
        list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(i + "");
        }
    }
}
