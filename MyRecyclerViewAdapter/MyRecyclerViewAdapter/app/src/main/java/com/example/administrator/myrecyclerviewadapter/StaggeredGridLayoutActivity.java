package com.example.administrator.myrecyclerviewadapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.myrecyclerviewadapter.base.BaseRecyclerAdapter;
import com.example.administrator.myrecyclerviewadapter.base.BaseViewHolder;
import com.example.administrator.myrecyclerviewadapter.util.ScreenUtil;
import com.example.administrator.myrecyclerviewadapter.util.ToastUtil;
import com.example.administrator.myrecyclerviewadapter.util.glide.ShowImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间：2018/1/26
 * 创建人： liuzj
 * 描述：
 * 包名：com.example.administrator.myrecyclerviewadapter
 * 邮箱：liuzj@hi-board.com
 */

public class StaggeredGridLayoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BaseRecyclerAdapter<Product> adapter;
    private List<Integer> mHeights;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        List<String> texts = new ArrayList<>();
        texts.add("http://p4.so.qhmsg.com/t0157596cfec8884249.jpg");
        texts.add("http://p2.so.qhmsg.com/t012824128d00583497.jpg");
        texts.add("http://p3.so.qhimgs1.com/t0125644adcf2cb71d3.jpg");
        texts.add("http://p0.so.qhmsg.com/t014ee81ba5ecc695e5.jpg");
        texts.add("http://p1.so.qhimgs1.com/t01aab3774cd6dd52d1.jpg");
        texts.add("http://p1.so.qhimgs1.com/bdr/200_200_/t01231af0b96b6a236d.jpg");
        texts.add("http://p2.so.qhimgs1.com/t01c5973455f3b9f0b2.jpg");
        texts.add("http://p2.so.qhmsg.com/t013cd48ad026dedf07.jpg");
        List<Product> products = new ArrayList<>();
        mHeights = new ArrayList<>();
        for (int i = 0; i < texts.size(); i++) {
            Product product = new Product(texts.get(i), "我是" + i + "产品");
            products.add(product);
            mHeights.add((int)(300+Math.random()*400));
        }

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BaseRecyclerAdapter<Product>(this, R.layout.item_stagggriad, products) {
            @Override
            protected void convert(BaseViewHolder holder, int p, final Product product) {
                ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
                layoutParams.height = mHeights.get(p);
                holder.itemView.setLayoutParams(layoutParams);
                holder.setImagebyUrl(R.id.imageview, product.getImg());
                holder.setText(R.id.text_view, product.getTitle());
                holder.setOnClickListener(R.id.text_view, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastUtil.showToast(StaggeredGridLayoutActivity.this, "你点击了" + product.getTitle());
                    }
                });
            }
        };

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
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new SpacesItemDecoration(16));
    }
}
