package com.example.administrator.myrecyclerviewadapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.myrecyclerviewadapter.base.BaseRecyclerAdapter;
import com.example.administrator.myrecyclerviewadapter.util.ScreenUtil;
import com.example.administrator.myrecyclerviewadapter.util.ToastUtil;
import com.example.administrator.myrecyclerviewadapter.util.glide.ShowImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间：2018/1/24
 * 创建人： liuzj
 * 描述：
 * 包名：com.example.administrator.myrecyclerviewadapter
 * 邮箱：liuzj@hi-board.com
 */

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private MeinvAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

        List<String> texts = new ArrayList<>();
        texts.add("http://p4.so.qhmsg.com/t0157596cfec8884249.jpg");
        texts.add("http://p2.so.qhmsg.com/t012824128d00583497.jpg");
        texts.add("http://p3.so.qhimgs1.com/t0125644adcf2cb71d3.jpg");
        texts.add("http://p0.so.qhmsg.com/t014ee81ba5ecc695e5.jpg");
        texts.add("http://p1.so.qhimgs1.com/t01aab3774cd6dd52d1.jpg");
        texts.add("http://p1.so.qhimgs1.com/bdr/200_200_/t01231af0b96b6a236d.jpg");
        texts.add("http://p2.so.qhimgs1.com/t01c5973455f3b9f0b2.jpg");
        texts.add("http://p2.so.qhmsg.com/t013cd48ad026dedf07.jpg");

        recyclerview.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new MeinvAdapter(this, R.layout.item_meinv, texts);
        ImageView imageView = new ImageView(this);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ScreenUtil.dp2px(this, 200));
        imageView.setLayoutParams(lp);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ShowImageUtils.showImageView(this, "http://p0.so.qhmsg.com/t014ee81ba5ecc695e5.jpg", imageView);
        adapter.setHeaderView(imageView);
        ImageView footView = new ImageView(this);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ScreenUtil.dp2px(this, 140));
        footView.setLayoutParams(p);
        footView.setScaleType(ImageView.ScaleType.FIT_XY);
        ShowImageUtils.showImageView(this, "http://p2.so.qhimgs1.com/t01c5973455f3b9f0b2.jpg", footView);
        adapter.setFooterView(footView);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(int position, String bean) {
                adapter.remove(position);
            }
        });
        adapter.setOnItemLongClickListener(new BaseRecyclerAdapter.OnItemLongClickListener<String>() {

            @Override
            public void onLongItemClick(int position, String bean) {
                ToastUtil.showToast(RecyclerViewActivity.this, "你点击了" + position);
            }
        });
        recyclerview.setAdapter(adapter);


    }
}
