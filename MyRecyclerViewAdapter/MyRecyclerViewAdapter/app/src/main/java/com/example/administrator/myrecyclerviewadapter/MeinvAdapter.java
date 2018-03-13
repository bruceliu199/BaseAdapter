package com.example.administrator.myrecyclerviewadapter;

import android.content.Context;

import com.example.administrator.myrecyclerviewadapter.base.BaseRecyclerAdapter;
import com.example.administrator.myrecyclerviewadapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/1/25.
 */

public class MeinvAdapter extends BaseRecyclerAdapter<String> {
    /*
    *   app:cardBackgroundColor这是设置背景颜色
        app:cardCornerRadius这是设置圆角大小
        app:cardElevation这是设置z轴的阴影
        app:cardMaxElevation这是设置z轴的最大高度值
        app:cardUseCompatPadding是否使用CompatPadding
        app:cardPreventCornerOverlap是否使用PreventCornerOverlap
        app:contentPadding 设置内容的padding
        app:contentPaddingLeft 设置内容的左padding
        app:contentPaddingTop 设置内容的上padding
        app:contentPaddingRight 设置内容的右padding
        app:contentPaddingBottom 设置内容的底padding
    *
    * */

    public MeinvAdapter(Context ctx, int itemLayoutId, List<String> list) {
        super(ctx, itemLayoutId, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, int p, String s) {
        holder.setImagebyUrl(R.id.iv, s);
    }
}
