package com.sun8am.jetwang.recyclerviewdemo;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.ThreadLocalRandom;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.recycler_view)
    RecyclerView _recyclerView;

    MyAdapter _adapter;
    String[] datas = new String[50];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        _recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
                Paint redPaint = new Paint();
                redPaint.setColor(Color.RED);

                c.drawLine(0, 0, parent.getMeasuredWidth(), 0, redPaint);
            }
        });


        _recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        _recyclerView.setLayoutManager(layoutManager);
        _recyclerView.setHasFixedSize(true);
        _adapter = new MyAdapter(getDummyDatas());
        _recyclerView.setAdapter(_adapter);

        _adapter.setOnRecyclerViewItemClick(new MyAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void OnItemClick(View view, final int position) {
                Snackbar.make(view, String.format("You Clicked a item labled %d", position), Snackbar.LENGTH_LONG).setAction(android.R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _adapter.removeItem(position);
                        Toast.makeText(MainActivity.this, "删除了"+position, Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private String[] getDummyDatas() {
        for (int i = 0; i < datas.length; i++){
            datas[i] = String.valueOf(ThreadLocalRandom.current().nextInt());
        }

        return datas;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
