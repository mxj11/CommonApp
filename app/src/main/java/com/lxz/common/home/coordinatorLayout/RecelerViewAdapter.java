package com.lxz.common.home.coordinatorLayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxz.common.R;

/**
 * Created by lxz on 2017/11/6 0006.
 */

public class RecelerViewAdapter extends RecyclerView.Adapter{
    private String[] list = null;
    private Context context;
    private LayoutInflater inflater;

    public RecelerViewAdapter(String[] list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.item_frame_list,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tv.setText(list[position]);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
          tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.length;
    }
}
