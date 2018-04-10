package com.lxz.common.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lxz.common.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lxz on 2017/8/12 0012.
 */

public class FrameListAdapter extends BaseAdapter {
    private Context mContext;
    private String[] frameList;
    private LayoutInflater inflater;

    public FrameListAdapter(Context mContext, String[] frameList) {
        this.mContext = mContext;
        this.frameList = frameList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return frameList.length;
    }

    @Override
    public Object getItem(int position) {
        return frameList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_frame_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(frameList[position]);
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.tv)
        TextView tv;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
