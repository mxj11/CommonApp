package com.lxz.common.home.picasso.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.lxz.common.R;
import com.lxz.common.home.picasso.Utils;
import com.lxz.common.home.picasso.activity.PicassoTransfromationsActivity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.ColorFilterTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import jp.wasabeef.picasso.transformations.CropSquareTransformation;
import jp.wasabeef.picasso.transformations.CropTransformation;
import jp.wasabeef.picasso.transformations.GrayscaleTransformation;
import jp.wasabeef.picasso.transformations.MaskTransformation;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import jp.wasabeef.picasso.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.VignetteFilterTransformation;

/**
 * Created by lxz on 2017/8/16 0016.
 * gradle需要配置：compile 'jp.wasabeef:picasso-transformations:2.1.0'
                  compile 'jp.co.cyberagent.android.gpuimage:gpuimage-library:1.4.1'
 */
public class PicassoTransformationsAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mData;
    private LayoutInflater inflater;
    public PicassoTransformationsAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mData = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_picasso_transformations,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText("item"+mData.get(i));
        int integer = Integer.parseInt(mData.get(i));

        switch (integer) {

            case 1: {
                int width = Utils.dip2px(mContext, 133.33f);
                int height = Utils.dip2px(mContext, 126.33f);
                Picasso.with(mContext)
                        .load(R.drawable.check)
                        .resize(width, height)
                        .centerCrop()
                        .transform((new MaskTransformation(mContext, R.drawable.mask_starfish)))
                        .into(holder.image);
                break;
            }
            case 2: {
                int width = Utils.dip2px(mContext, 150.0f);
                int height = Utils.dip2px(mContext, 100.0f);
                Picasso.with(mContext)
                        .load(R.drawable.check)
                        .resize(width, height)
                        .centerCrop()
                        .transform(new MaskTransformation(mContext, R.drawable.demo))
                        .into(holder.image);
                break;
            }
            case 3:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.LEFT,
                                CropTransformation.GravityVertical.TOP))
                        .into(holder.image);
                break;
            case 4:
                Picasso.with(mContext).load(R.drawable.demo)
                        // 300, 100, CropTransformation.GravityHorizontal.LEFT, CropTransformation.GravityVertical.CENTER))
                        .transform(new CropTransformation(300, 100)).into(holder.image);
                break;
            case 5:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.LEFT,
                                CropTransformation.GravityVertical.BOTTOM))
                        .into(holder.image);
                break;
            case 6:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.TOP))
                        .into(holder.image);
                break;
            case 7:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(300, 100))
                        .into(holder.image);
                break;
            case 8:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.BOTTOM))
                        .into(holder.image);
                break;
            case 9:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.RIGHT,
                                CropTransformation.GravityVertical.TOP))
                        .into(holder.image);
                break;
            case 10:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.RIGHT,
                                CropTransformation.GravityVertical.CENTER))
                        .into(holder.image);
                break;
            case 11:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.RIGHT,
                                CropTransformation.GravityVertical.BOTTOM))
                        .into(holder.image);
                break;
            case 12:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation((float) 16 / (float) 9,
                                CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.CENTER))
                        .into(holder.image);
                break;
            case 13:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation((float) 4 / (float) 3,
                                CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.CENTER))
                        .into(holder.image);
                break;
            case 14:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(3, CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.CENTER))
                        .into(holder.image);
                break;
            case 15:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(3, CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.TOP))
                        .into(holder.image);
                break;
            case 16:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(1, CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.CENTER))
                        .into(holder.image);
                break;
            case 17:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation((float) 0.5, (float) 0.5,
                                CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.CENTER))
                        .into(holder.image);
                break;
            case 18:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation((float) 0.5, (float) 0.5,
                                CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.TOP))
                        .into(holder.image);
                break;
            case 19:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation((float) 0.5, (float) 0.5,
                                CropTransformation.GravityHorizontal.RIGHT,
                                CropTransformation.GravityVertical.BOTTOM))
                        .into(holder.image);
                break;
            case 20:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation((float) 0.5, 0, (float) 4 / (float) 3,
                                CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.CENTER))
                        .into(holder.image);
                break;
            case 21:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropSquareTransformation())
                        .into(holder.image);
                break;
            case 22:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropCircleTransformation())
                        .into(holder.image);
                break;
            case 23:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new ColorFilterTransformation(Color.argb(80, 255, 0, 0)))
                        .into(holder.image);
                break;
            case 24:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new GrayscaleTransformation())
                        .into(holder.image);
                break;
            case 25:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new RoundedCornersTransformation(30, 0,
                                RoundedCornersTransformation.CornerType.BOTTOM_LEFT))
                        .into(holder.image);
                break;
            case 26:
                Picasso.with(mContext)
                        .load(R.drawable.check)
                        .transform(new BlurTransformation(mContext, 25, 1))
                        .into(holder.image);
                break;
            case 27:
                Picasso.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new ToonFilterTransformation(mContext))
                        .into(holder.image);
                break;
            case 28:
                Picasso.with(mContext)
                        .load(R.drawable.check)
                        .transform(new SepiaFilterTransformation(mContext))
                        .into(holder.image);
                break;
            case 29:
                Picasso.with(mContext)
                        .load(R.drawable.check)
                        .transform(new ContrastFilterTransformation(mContext, 2.0f))
                        .into(holder.image);
                break;
            case 30:
                Picasso.with(mContext)
                        .load(R.drawable.check)
                        .transform(new InvertFilterTransformation(mContext))
                        .into(holder.image);
                break;
            case 31:
                Picasso.with(mContext)
                        .load(R.drawable.check)
                        .transform(new PixelationFilterTransformation(mContext, 20))
                        .into(holder.image);
                break;
            case 32:
                Picasso.with(mContext)
                        .load(R.drawable.check)
                        .transform(new SketchFilterTransformation(mContext))
                        .into(holder.image);
                break;
            case 33:
                Picasso.with(mContext)
                        .load(R.drawable.check)
                        .transform(new SwirlFilterTransformation(mContext, 0.5f, 1.0f, new PointF(0.5f, 0.5f)))
                        .into(holder.image);

                break;
            case 34:
                Picasso.with(mContext)
                        .load(R.drawable.check)
                        .transform(new BrightnessFilterTransformation(mContext, 0.5f))
                        .into(holder.image);
                break;
            case 35:
                Picasso.with(mContext)
                        .load(R.drawable.check)
                        .transform(new KuwaharaFilterTransformation(mContext, 25))
                        .into(holder.image);
                break;
            case 36:
                Picasso.with(mContext)
                        .load(R.drawable.check)
                        .transform(new VignetteFilterTransformation(mContext, new PointF(0.5f, 0.5f),
                                new float[]{0.0f, 0.0f, 0.0f}, 0f, 0.75f))
                        .into(holder.image);
                break;
        }

        return convertView;
    }

    class ViewHolder{
        @Bind(R.id.tv_picasso)
        TextView tv;
        @Bind(R.id.iv_picasso)
        ImageView image;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
