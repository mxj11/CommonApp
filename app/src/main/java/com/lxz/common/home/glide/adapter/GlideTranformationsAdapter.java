package com.lxz.common.home.glide.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.lxz.common.home.picasso.Utils;

import java.util.List;
import com.lxz.common.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

/**
 * Created by lxz on 2017/8/17 0017.
 */
public class GlideTranformationsAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<String> mData;
    private LayoutInflater inflater;

    public GlideTranformationsAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mData = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_glide_trans, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tvGlideName.setText("item"+position);
        int integer = Integer.parseInt(mData.get(position));

        switch (integer) {
            case 1: {
                int width = Utils.dip2px(mContext, 133.33f);
                int height = Utils.dip2px(mContext, 126.33f);
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .override(width, height)
                        .bitmapTransform(new CenterCrop(mContext),
                                new MaskTransformation(mContext, R.drawable.mask_starfish))
                        .into(myViewHolder.image);
                break;
            }
            case 2: {
                int width = Utils.dip2px(mContext, 150.0f);
                int height = Utils.dip2px(mContext, 100.0f);
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .override(width, height)
                        .bitmapTransform(new CenterCrop(mContext),
                                new MaskTransformation(mContext, R.drawable.demo))
                        .into(myViewHolder.image);
                break;
            }
            case 3:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .bitmapTransform(
                                new CropTransformation(mContext, 300, 100, CropTransformation.CropType.TOP))
                        .into(myViewHolder.image);
                break;
            case 4:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .bitmapTransform(new CropTransformation(mContext, 300, 100))
                        .into(myViewHolder.image);
                break;
            case 5:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .bitmapTransform(
                                new CropTransformation(mContext, 300, 100, CropTransformation.CropType.BOTTOM))
                        .into(myViewHolder.image);

                break;
            case 6:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .bitmapTransform(new CropSquareTransformation(mContext))
                        .into(myViewHolder.image);
                break;
            case 7:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .bitmapTransform(new CropCircleTransformation(mContext))
                        .into(myViewHolder.image);
                break;
            case 8:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .bitmapTransform(new ColorFilterTransformation(mContext, Color.argb(80, 255, 0, 0)))
                        .into(myViewHolder.image);
                break;
            case 9:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .bitmapTransform(new GrayscaleTransformation(mContext))
                        .into(myViewHolder.image);
                break;
            case 10:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .bitmapTransform(new RoundedCornersTransformation(mContext, 30, 0,
                                RoundedCornersTransformation.CornerType.BOTTOM))
                        .into(myViewHolder.image);
                break;
            case 11:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .bitmapTransform(new BlurTransformation(mContext, 25))
                        .into(myViewHolder.image);
                break;
            case 12:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .bitmapTransform(new ToonFilterTransformation(mContext))
                        .into(myViewHolder.image);
                break;
            case 13:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .bitmapTransform(new SepiaFilterTransformation(mContext))
                        .into(myViewHolder.image);
                break;
            case 14:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .bitmapTransform(new ContrastFilterTransformation(mContext, 2.0f))
                        .into(myViewHolder.image);
                break;
            case 15:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .bitmapTransform(new InvertFilterTransformation(mContext))
                        .into(myViewHolder.image);
                break;
            case 16:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .bitmapTransform(new PixelationFilterTransformation(mContext, 20))
                        .into(myViewHolder.image);
                break;
            case 17:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .bitmapTransform(new SketchFilterTransformation(mContext))
                        .into(myViewHolder.image);
                break;
            case 18:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .bitmapTransform(
                                new SwirlFilterTransformation(mContext, 0.5f, 1.0f, new PointF(0.5f, 0.5f)))
                        .into(myViewHolder.image);
                break;
            case 19:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .bitmapTransform(new BrightnessFilterTransformation(mContext, 0.5f))
                        .into(myViewHolder.image);
                break;
            case 20:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .bitmapTransform(new KuwaharaFilterTransformation(mContext, 25))
                        .into(myViewHolder.image);
                break;
            case 21:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .bitmapTransform(new VignetteFilterTransformation(mContext, new PointF(0.5f, 0.5f),
                                new float[]{0.0f, 0.0f, 0.0f}, 0f, 0.75f))
                        .into(myViewHolder.image);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_glide_tranfromations)
        ImageView image;
        @Bind(R.id.tv_glide_name)
        TextView tvGlideName;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
