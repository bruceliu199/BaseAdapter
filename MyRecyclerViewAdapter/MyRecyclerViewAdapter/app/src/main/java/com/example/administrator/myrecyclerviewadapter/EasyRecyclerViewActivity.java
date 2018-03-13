package com.example.administrator.myrecyclerviewadapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.myrecyclerviewadapter.base.BaseRecyclerViewAdapter;
import com.example.administrator.myrecyclerviewadapter.util.StatusBarCompat;
import com.example.administrator.myrecyclerviewadapter.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间：2018/1/24
 * 创建人： liuzj
 * 描述：
 * 包名：com.example.administrator.myrecyclerviewadapter
 * 邮箱：liuzj@hi-board.com
 */

public class EasyRecyclerViewActivity extends AppCompatActivity {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private BaseRecyclerViewAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_recyclerview);
        StatusBarCompat.compat(this, R.color.colorAccent);
        ButterKnife.bind(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        List<String> texts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            texts.add("你好！我是" + i + "号");
        }
        adapter = new BaseRecyclerViewAdapter<String>(this, R.layout.item_recyclerview_linear, texts) {
            @Override
            public RecyclerView.ViewHolder onCreatHolder(View view) {
                return new ViewHolder(view);
            }

            @Override
            protected void onBindHolder(RecyclerView.ViewHolder holder, int pos, String bean) {
                ViewHolder viewHolder = (ViewHolder) holder;
                viewHolder.tv.setText(bean);
            }

        };
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<String>() {

            @Override
            public void onItemClick(int position, String bean) {
                ToastUtil.showToast(EasyRecyclerViewActivity.this, bean);
            }
        });
        recyclerview.setAdapter(adapter);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
