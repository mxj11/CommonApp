package com.lxz.common.home.picasso.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.lxz.common.R;
import com.lxz.common.home.imageloader.Constants;
import com.lxz.common.home.picasso.activity.PicassoListviewActivity;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lxz on 2017/8/16 0016.
 */
public class PicassoListviewAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    public PicassoListviewAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return Constants.IMAGES.length;
    }

    @Override
    public Object getItem(int i) {
        return Constants.IMAGES[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_picasso_listview,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv.setText("item"+i);
        Picasso.with(mContext)
                .load(Constants.IMAGES[i])
                .placeholder(R.mipmap.ic_launcher)//图片加载中默认显示的图片
                .error(R.mipmap.ic_launcher)//图片加载错误显示的图片
                .into(holder.iv);
        return convertView;
    }

    class ViewHolder{
        @Bind(R.id.tv_picasso_name)
        TextView tv;
        @Bind(R.id.iv_picasso_item)
        ImageView iv;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

}
