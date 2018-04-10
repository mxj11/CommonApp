package com.lxz.common.home.imageloader.adapter;

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
import com.lxz.common.home.imageloader.activity.ImageloaderListviewActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lxz on 2017/8/16 0016.
 */
public class ImageloaderListviewAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    public ImageloaderListviewAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.ic_launcher)          // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)  // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)       // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)                          // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(50))  // 设置成圆角图片
                .build();                                   // 创建配置过得DisplayImageOption对象;
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
            convertView = inflater.inflate(R.layout.item_imageloader_listview,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvShow.setText("item"+i);
        imageLoader.displayImage(Constants.IMAGES[i],holder.iv,options);
        return convertView;
    }

    class ViewHolder{
        @Bind(R.id.tv_imageloader_name)
        public TextView tvShow;
        @Bind(R.id.iv_imageloader_listview)
        public ImageView iv;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
