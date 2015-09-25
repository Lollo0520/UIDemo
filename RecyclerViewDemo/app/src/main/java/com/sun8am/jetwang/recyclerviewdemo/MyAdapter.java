package com.sun8am.jetwang.recyclerviewdemo;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jetwang on 15/9/24.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener {

    public ArrayList<String> datas = null;

    private int[] colors = new int[]{ Color.RED, Color.BLUE, Color.BLACK, Color.CYAN, Color.GREEN, Color.DKGRAY, Color.MAGENTA};

    private static int[] height = { 150, 250, 350, 450, 550, 650, 750};

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            mOnItemClickListener.OnItemClick(v, (int)v.getTag());
        }
    }

    static interface OnRecyclerViewItemClickListener{
        void OnItemClick(View view, int position);
    }

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    public void removeItem(int pos) {
        datas.remove(pos);
        notifyItemRemoved(pos);//Attention!
    }


    public MyAdapter(String[] datas) {
        this.datas = new ArrayList<>(Arrays.asList(datas));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, null);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String data = datas.get(position);
        holder.mTextView.setText(data);
        holder.itemView.setTag(position);
//        holder.mTextView.setHeight(height[ThreadLocalRandom.current().nextInt(0, 7)]);
//        holder.mTextView.setBackgroundColor(colors[ThreadLocalRandom.current().nextInt(0, 7)]);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text);
        }
    }

}
