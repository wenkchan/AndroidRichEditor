package com.wenkchan.androidricheditor;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by chenwenkun on 2015/11/4.
 * <p/>
 * 通用ViewHolder类
 */
public class Holder {
    private SparseArray<View> mViews;//一个比Map更高效的键值对容器
    private int mPosition;
    private View mConvertView;

    public Holder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        mViews = new SparseArray<>();
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    public static Holder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new Holder(context, parent, layoutId, position);
        } else {
            Holder holder = (Holder) convertView.getTag();
            holder.mPosition = position;//更新一下position，并不复用position
            return holder;
        }
    }

    /**
     * 通过ViewId获取控件
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;

    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 为TextView设置内容的方法
     *
     * @param viewId
     * @param text
     * @return
     */
    public Holder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;//链式编程
    }

    public int getPosition(){
        return mPosition;
    }
}
