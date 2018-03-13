package com.example.administrator.myrecyclerviewadapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 创建时间：2018/1/24
 * 创建人： liuzj
 * 描述：
 * 包名：com.example.administrator.myrecyclerviewadapter.base
 * 邮箱：liuzj@hi-board.com
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected Context mContext;
    protected final LayoutInflater inflater;
    protected List<T> mDatas;
    protected int mLayoutId;
    private OnItemClickListener onItemClickListener;

    public BaseRecyclerViewAdapter(Context mContext, int layoutId, List<T> datas) {
        this.mContext = mContext;
        this.mLayoutId = layoutId;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    /*数据操作*/
    public void addData(T bean) {
        mDatas.add(bean);
        notifyDataSetChanged();
    }

    public void addDatas(List<T> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        if (index < 0 && index > mDatas.size()) {
            throw new IndexOutOfBoundsException("index not right");
        }
        else mDatas.remove(index);
        notifyDataSetChanged();
    }

    public void removeAll() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(mLayoutId, parent, false);
        return onCreatHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int pos = getRealPosition(holder);
        final T bean = mDatas.get(pos);
        onBindHolder(holder, pos, bean);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(pos, bean);
                }

            }
        });
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        return holder.getLayoutPosition();
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    /*写一个接口回调点击事件*/
    public interface OnItemClickListener<T> {
        void onItemClick(int position, T bean);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    /**
     * @param view
     * @return 抽象出去
     */
    public abstract RecyclerView.ViewHolder onCreatHolder(View view);

    protected abstract void onBindHolder(RecyclerView.ViewHolder holder, int pos, T bean);
}
