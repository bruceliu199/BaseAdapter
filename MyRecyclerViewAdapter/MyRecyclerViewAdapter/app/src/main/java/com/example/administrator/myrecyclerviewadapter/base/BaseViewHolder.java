package com.example.administrator.myrecyclerviewadapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myrecyclerviewadapter.R;
import com.example.administrator.myrecyclerviewadapter.util.glide.ShowImageUtils;

/**
 * 创建时间：2018/1/24
 * 创建人： liuzj
 * 描述：BaseViewHolder封装一些常用控件的处理
 * 包名：com.example.administrator.myrecyclerviewadapter.base
 * 邮箱：liuzj@hi-board.com
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    /*
    * 数据量不大，最好在千级以内
    * key必须为int类型，这中情况下的HashMap可以用SparseArray代替：比如
    * HashMap<Integer, Object> map = new HashMap<>();
    * 用SparseArray代替:
    * SparseArray<Object> array = new SparseArray<>();
    * */
    private SparseArray<View> mViews; //集合类，layout里包含的View,以view的id作为key，value是view对象
    private Context mContext;

    public BaseViewHolder(Context mContext, View itemView) {
        super(itemView);
        this.mContext = mContext;
        mViews = new SparseArray<>();
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    //  封装一些常用的控件  根据对应的id获取控件
    public TextView getTextView(int viewId) {
        return (TextView) getView(viewId);
    }

    public Button getButton(int viewId) {
        return (Button) getView(viewId);
    }

    public ImageView getImageView(int viewId) {
        return (ImageView) getView(viewId);
    }

    public ImageButton getImageButton(int viewId) {
        return (ImageButton) getView(viewId);
    }

    public EditText getEditText(int viewId) {
        return (EditText) getView(viewId);
    }

    public BaseViewHolder setText(int viewId, String value) {
        TextView textView = getView(viewId);
        textView.setText(value);
        return this;
    }

    public BaseViewHolder setBitmapImage(int viewId, int imageId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(imageId);
        return this;
    }

    public BaseViewHolder setImagebyUrl(int viewId, String url) {
        ImageView iv = getView(viewId);
        ShowImageUtils.showImageView(mContext, url, iv);
        return this;
    }

    public BaseViewHolder setCircleImagebyUrl(int viewId, String url) {
        ImageView iv = getView(viewId);
        ShowImageUtils.showImageViewToCircle(mContext, R.mipmap.ic_launcher, url, iv);
        return this;
    }

    public BaseViewHolder setBackground(int viewId, int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    public BaseViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

}
