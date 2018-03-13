package com.example.administrator.myrecyclerviewadapter;

/**
 * 创建时间：2018/1/26
 * 创建人： liuzj
 * 描述：
 * 包名：com.example.administrator.myrecyclerviewadapter
 * 邮箱：liuzj@hi-board.com
 */

public class Product {
    private String img;
    private String title;

    public Product(String img, String title) {
        this.img = img;
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
