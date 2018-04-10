package com.lxz.common.home.recycleview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lxz.common.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lxz on 2017/8/16 0016.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<String> mData;

    public RecycleViewAdapter(Context context, ArrayList<String> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //根据位置得到对应的数据
        String  data  = mData.get(position);
        ViewHolder myHolder = (ViewHolder) holder;
        myHolder.tvTitle.setText(data);
        myHolder.ivIcon.setImageResource(R.drawable.demo);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_icon)
        ImageView ivIcon;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.setBackgroundResource(R.drawable.selector_item_bg);
                    Toast.makeText(mContext, "data=="+mData.get(getLayoutPosition()), Toast.LENGTH_SHORT).show();
                    if(onItemClickListener != null){
                        onItemClickListener.onItemClick(v,mData.get(getLayoutPosition()));
                    }
                }
            });

            ivIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "我是图片=="+getLayoutPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    /**
     * 添加数据
     * @param position
     * @param data
     */
    public void addData(int position, String data) {
        mData.add(position,data);
        //刷新适配器
        notifyItemInserted(position);

    }

    /**
     * 移除数据
     * @param position
     */
    public void removeData(int position) {
        mData.remove(position);
        //刷新适配器
        notifyItemRemoved(position);
    }
    /**
     * 点击RecyclerView某条的监听
     */
    public interface OnItemClickListener{

        /**
         * 当RecyclerView某个被点击的时候回调
         * @param view 点击item的视图
         * @param data 点击得到的数据
         */
        public void onItemClick(View view,String data);

    }

    private  OnItemClickListener onItemClickListener;

    /**
     * 设置RecyclerView某个的监听
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
