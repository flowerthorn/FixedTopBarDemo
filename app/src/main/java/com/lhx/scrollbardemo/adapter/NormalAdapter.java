package com.lhx.scrollbardemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lhx.scrollbardemo.R;

import java.net.PortUnreachableException;
import java.util.List;

/**
 * Created by lihongxin on 2019/1/18
 */
public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.ViewHolder> {

    public List<String> datas;
    private View mHeaderView;
    private View mFooterView;
    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的

    public NormalAdapter(List<String> datas) {
        this.datas = datas;
    }

    public void setmHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
    }

    public void setmFooterView(View mFooterView) {
        this.mFooterView = mFooterView;
    }

    /**
     * 重写这个方法，很重要，是加入Header和Footer的关键，我们通过判断item的类型，从而绑定不同的view    *
     */
    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null) {
            return TYPE_NORMAL;
        }

        if (position == 0) {
            if (mHeaderView != null) {
                //第一个item应该加载Header
                return TYPE_HEADER;
            } else {
                return TYPE_NORMAL;
            }

        }
        if (position == getItemCount() - 1) {
            if (mFooterView != null) {
                //最后一个,应该加载Footer
                return TYPE_FOOTER;
            } else {
                return TYPE_NORMAL;
            }

        }
        return TYPE_NORMAL;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_NORMAL:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
                break;
            case TYPE_HEADER:
                view = mHeaderView;
                break;
            case TYPE_FOOTER:
                view = mFooterView;
                break;
            default:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
                break;
        }

        ViewHolder vh = new ViewHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == TYPE_NORMAL) {
            if (mHeaderView != null) {
                viewHolder.mTextView.setText(datas.get(i - 1));
            } else {
                viewHolder.mTextView.setText(datas.get(i));
            }

        }
    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null && mFooterView == null) {
            return datas == null ? 0 : datas.size();
        } else if (mHeaderView == null && mFooterView != null) {
            return datas == null ? 0 : datas.size() + 1;
        } else if (mHeaderView != null && mFooterView == null) {
            return datas == null ? 0 : datas.size() + 1;
        } else {
            return datas == null ? 0 : datas.size() + 2;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if (itemView == mHeaderView) {
                return;
            } else if (itemView == mFooterView) {
                return;
            }
            mTextView = itemView.findViewById(R.id.tv_index);

        }
    }

    //是否是头部布局
    public boolean isHasHeader() {
        return mHeaderView != null;
    }


    //是否是固定的顶部布局
    public boolean isFirstItem(int position) {
        if (mHeaderView != null && position == 1) {
            return true;
        } else if (mHeaderView == null && position == 0) {
            return true;
        } else {
            return false;
        }
    }
}
