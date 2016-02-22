package com.wenkchan.androidricheditor;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by chenwenkun on 2015/11/4.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mDatas;
    private int mLayoutId;
    private boolean mIsRoot;

    public CommonAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        this.mDatas = datas;
        this.mLayoutId = layoutId;
    }

    public CommonAdapter(Context context, List<T> datas, int layoutId, boolean isRoot) {
        this.mContext = context;
        this.mDatas = datas;
        this.mLayoutId = layoutId;
        mIsRoot = isRoot;
    }

    @Override
    public int getCount() {

        return mDatas.size();
    }

    public void setList(List<T> datas) {
        mDatas = datas;

    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Holder holder = Holder.get(mContext, view, viewGroup, mLayoutId, position);
        convert(holder, getItem(position));
        return holder.getConvertView();
    }

    public abstract void convert(Holder holder, T t);//就只需要实现这个方法。
}
