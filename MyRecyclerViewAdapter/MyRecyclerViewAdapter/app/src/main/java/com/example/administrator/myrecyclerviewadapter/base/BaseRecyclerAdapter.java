package com.example.administrator.myrecyclerviewadapter.base;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间：2018/1/24
 * 创建人： liuzj
 * 描述：
 * 包名：com.example.administrator.myrecyclerviewadapter.base
 * 邮箱：liuzj@hi-board.com
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {


    protected Context mContext;
    protected List<T> mDatas;
    private int totalList;

    private int itemLayoutId;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private final LayoutInflater mInflater;
    private static final int TYPE_HEAD = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOT = 2;
    private View headerView, footerView;  //头和尾的view


    public BaseRecyclerAdapter(Context ctx, int itemLayoutId, List<T> list) {
        this.mContext = ctx;
        this.itemLayoutId = itemLayoutId;
        mDatas = (list != null) ? list : new ArrayList<T>();
        mInflater = LayoutInflater.from(ctx);
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
        } else mDatas.remove(index);
        notifyDataSetChanged();
    }

    public void removeAll() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public void setFooterView(View view) {
        this.footerView = view;
    }

    public void setHeaderView(View view) {
        this.headerView = view;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEAD && headerView != null) {
            return new BaseViewHolder(mContext, headerView);
        }
        if (viewType == TYPE_FOOT && footerView != null) {
            return new BaseViewHolder(mContext, footerView);
        }

        View view = mInflater.inflate(itemLayoutId, parent, false);
        return new BaseViewHolder(mContext, view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (getItemViewType(position) != TYPE_ITEM) {
            //如果不是正常的Item，就不去绑定数据
            return;
        }
        final int p = getRealPosition(position);
        convert(holder, p, mDatas.get(p));
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(p, mDatas.get(p));
                }
            });
        }
        if (onItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemLongClickListener.onLongItemClick(p, mDatas.get(p));
                    return true;
                }
            });
        }


    }


    private int getRealPosition(int position) {
        return headerView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        if (mDatas == null) {
            return 0;
        }
        if (headerView != null && footerView != null) {
            //头尾都不为空
            totalList = mDatas.size() + 2;
        } else if (headerView == null && footerView == null) {
            //头尾都为空
            totalList = mDatas.size();
        } else {
            //头尾有一个不为空
            totalList = mDatas.size() + 1;
        }
        return totalList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && headerView != null) {
            return TYPE_HEAD;
        } else if (position + 1 == getItemCount() && footerView != null) {
            return TYPE_FOOT;
        } else {
            return TYPE_ITEM;
        }
    }

    /**
     * @param recyclerView 处理GridLayoutManager
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            //setSpanSizeLookup的getSpanSize可以控制列数
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (getItemViewType(position) == TYPE_HEAD || getItemViewType(position) == TYPE_FOOT) ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }/*else if (manager instanceof StaggeredGridLayoutManager) {  StaggeredGridLayoutManager并没有setSpanSizeLookup这个方法，所以此路不通
            StaggeredGridLayoutManager staggeredManager = (StaggeredGridLayoutManager) manager;

        }*/
    }

    /**
     * @param holder
     * 处理StaggerdGridLayoutManager
     */
    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            if (headerView != null && holder.getLayoutPosition() == 0) {
                p.setFullSpan(true);
            }

            if (footerView != null && holder.getLayoutPosition() == getItemCount() - 1) {
                p.setFullSpan(true);
            }
        }

    }

    /*
            * 需要根据实际情况设置的部分抽象出去
            * */
    protected abstract void convert(BaseViewHolder holder, int p, T t);

    /*接口回调点击和长按事件*/
    public interface OnItemClickListener<T> {
        void onItemClick(int position, T bean);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemLongClickListener<T> {
        void onLongItemClick(int position, T bean);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onLongClickListener) {
        this.onItemLongClickListener = onLongClickListener;
    }
}
