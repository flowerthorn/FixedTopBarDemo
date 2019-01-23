package com.lhx.scrollbardemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lhx.scrollbardemo.adapter.NormalAdapter;
import com.lhx.scrollbardemo.util.ViewUtils;

/**
 * Created by lihongxin on 2019/1/22
 */
public class FixedBarDecoration extends RecyclerView.ItemDecoration {

    private int mItemHeaderHeight;
    private Paint mLinePaint;
    private Paint mItemHeaderPaint;
    private Paint mTextPaint;
    private Rect mTextRect;

    public FixedBarDecoration(Context context) {

        mItemHeaderHeight = ViewUtils.dip2px(context, 40);

        mTextRect = new Rect();
        mItemHeaderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mItemHeaderPaint.setColor(Color.BLUE);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(Color.GRAY);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(46);
        mTextPaint.setColor(Color.WHITE);
    }


    //吸顶效果的主要实现方法
    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getAdapter() instanceof NormalAdapter) {
            NormalAdapter adapter = (NormalAdapter) parent.getAdapter();
            int position = ((LinearLayoutManager) (parent.getLayoutManager())).findFirstVisibleItemPosition();
            if (adapter.isHasHeader() && position == 0) {
                return;
            }
            //如果不是头部view 那就直接在当前第一个可见的item顶部画一个固定栏即可
//            View view = parent.findViewHolderForAdapterPosition(position).itemView;
            c.drawRect(0, 0, parent.getWidth(), mItemHeaderHeight, mItemHeaderPaint);
            mTextPaint.getTextBounds("悬浮固定栏", 0, "悬浮固定栏".length(), mTextRect);
            c.drawText("悬浮固定栏", parent.getWidth() / 2 - mTextRect.width() / 2, mItemHeaderHeight / 2 + mTextRect.height() / 2, mTextPaint);

        }
    }

    //绘制分割线和固定栏
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getAdapter() instanceof NormalAdapter) {
            NormalAdapter adapter = (NormalAdapter) parent.getAdapter();
            int count = parent.getChildCount();
            for (int i = 0; i < count; i++) {
                View view = parent.getChildAt(i);
                int position = parent.getChildLayoutPosition(view);
                boolean isFirstItem = adapter.isFirstItem(position);
                if (isFirstItem) {
                    c.drawRect(0, view.getTop() - mItemHeaderHeight, parent.getWidth(), view.getTop(), mItemHeaderPaint);
                    mTextPaint.getTextBounds("悬浮固定栏", 0, "悬浮固定栏".length(), mTextRect);
                    c.drawText("悬浮固定栏", parent.getWidth() / 2 - mTextRect.width() / 2, (view.getTop() - mItemHeaderHeight) + mItemHeaderHeight / 2 + mTextRect.height() / 2, mTextPaint);
                } else {
                    c.drawRect(0, view.getTop() - 1, parent.getWidth(), view.getTop(), mLinePaint);
                }
            }
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getAdapter() instanceof NormalAdapter) {
            NormalAdapter adapter = (NormalAdapter) parent.getAdapter();
            int position = parent.getChildLayoutPosition(view);
            boolean isFirstItem = adapter.isFirstItem(position);
            if (isFirstItem) {
                outRect.top = mItemHeaderHeight;
            } else {
                outRect.top = 1;
            }

        }
    }

}
