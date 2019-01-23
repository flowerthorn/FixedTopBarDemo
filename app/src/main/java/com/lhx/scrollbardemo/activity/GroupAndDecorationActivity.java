package com.lhx.scrollbardemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lhx.scrollbardemo.R;
import com.lhx.scrollbardemo.adapter.GroupAdapter;
import com.lhx.scrollbardemo.bean.TestData;
import com.lhx.scrollbardemo.widget.GroupDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lihongxin on 2019/1/22
 */
public class GroupAndDecorationActivity extends AppCompatActivity {

    @BindView(R.id.rv_list)
    RecyclerView recyclerView;
    private List<TestData> testDataList;
    private LinearLayoutManager linearLayoutManager;
    private GroupAdapter groupAdapter;


    public static void start(Activity activity) {
        Intent intent = new Intent(activity, GroupAndDecorationActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_and_decoration);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(this);
        groupAdapter = new GroupAdapter(testDataList);
        recyclerView.addItemDecoration(new GroupDecoration(this));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(groupAdapter);


    }

    private void initData() {
        testDataList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            testDataList.add(new TestData(String.format("第一组%d号", i + 1), "第一组"));
        }
        for (int i = 0; i < 8; i++) {
            testDataList.add(new TestData(String.format("第二组%d号", i + 1), "第二组"));
        }
        for (int i = 0; i < 10; i++) {
            testDataList.add(new TestData(String.format("第三组%d号", i + 1), "第三组"));
        }
        for (int i = 0; i < 12; i++) {
            testDataList.add(new TestData(String.format("第四组%d号", i + 1), "第四组"));
        }
    }
}
