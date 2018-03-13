package com.example.administrator.myrecyclerviewadapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void common(View view) {
        startActivity(new Intent(this, EasyRecyclerViewActivity.class));
    }

    public void second(View view) {
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }

    public void end(View view) {
        startActivity(new Intent(this, StaggeredGridLayoutActivity.class));
    }
}
