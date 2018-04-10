package com.lxz.common.fourth.udp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxz.common.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lxz on 2017/11/1 0001.
 */
public class ChatReclerViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Map<Integer, String>> chatContents;
    private LayoutInflater inflater;

    public ChatReclerViewAdapter(Context context, List<Map<Integer, String>> chatContents) {
        this.context = context;
        this.chatContents = chatContents;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.item_chat, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myHolder = (MyViewHolder) holder;
        myHolder.rlRight.setVisibility(View.GONE);
        HashMap<Integer, String> map = (HashMap<Integer, String>) chatContents.get(position);
        for (int key : map.keySet()) {
            if (key == 0) {//右边
                myHolder.rlRight.setVisibility(View.VISIBLE);
                myHolder.rlLeft.setVisibility(View.GONE);
                myHolder.tvChatContentRight.setText(map.get(key));
            } else {//左边
                myHolder.rlRight.setVisibility(View.GONE);
                myHolder.rlLeft.setVisibility(View.VISIBLE);
                myHolder.tvChatContentLeft.setText(map.get(key));
            }
        }
        // myHolder.tvChatContentLeft.setText(map.get(0).toString());
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_chat_content_left)
        TextView tvChatContentLeft;
        @Bind(R.id.rl_left)
        RelativeLayout rlLeft;
        @Bind(R.id.tv_chat_content_right)
        TextView tvChatContentRight;
        @Bind(R.id.rl_right)
        RelativeLayout rlRight;
        @Bind(R.id.iv_head_left)
        ImageView ivHeadLeft;
        @Bind(R.id.iv_head_right)
        ImageView ivHeadRight;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemCount() {
        return chatContents == null ? 0 : chatContents.size();
    }

    public void setChatContents(List<Map<Integer, String>> chatContents) {
        this.chatContents = chatContents;
    }
}
