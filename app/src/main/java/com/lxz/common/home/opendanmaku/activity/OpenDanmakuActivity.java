package com.lxz.common.home.opendanmaku.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.lxz.common.R;
import com.opendanmaku.DanmakuItem;
import com.opendanmaku.DanmakuView;
import com.opendanmaku.IDanmakuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpenDanmakuActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.videoView)
    VideoView videoView;
    @Bind(R.id.danmakuView)
    DanmakuView mDanmakuView;
    @Bind(R.id.switcher)
    Button switcherBtn;
    @Bind(R.id.text)
    EditText textEditText;
    @Bind(R.id.send)
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_danmaku_main);
        ButterKnife.bind(this);
        //设置相关播放视频
        setVideoView();
        //设置标题
        tvTitle.setText("OpenDanmaku弹幕");

        //重点
        List<IDanmakuItem> list = initItems();
        //变成随机数据
        Collections.shuffle(list);

        //添加到弹幕控件里面
        mDanmakuView.addItem(list, true);

        switcherBtn.setOnClickListener(this);
        send.setOnClickListener(this);
    }

    @OnClick({R.id.switcher, R.id.send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.switcher:
                break;
            case R.id.send:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switcher:
                if (mDanmakuView.isPaused()) {
                    switcherBtn.setText("hide");
                    mDanmakuView.show();
                } else {
                    switcherBtn.setText("show");
                    mDanmakuView.hide();
                }
                break;
            case R.id.send:
                String input = textEditText.getText().toString();
                if (TextUtils.isEmpty(input)) {
                    Toast.makeText(OpenDanmakuActivity.this, "please input text", Toast.LENGTH_SHORT).show();
                } else {
                    IDanmakuItem item = new DanmakuItem(this, new SpannableString(input), mDanmakuView.getWidth(),0,R.color.my_item_color,0,1);
//                    IDanmakuItem item = new DanmakuItem(this, input, mDanmakuView.getWidth());
//                    item.setTextColor(getResources().getColor(R.color.my_item_color));
//                    item.setTextSize(14);
//                    item.setTextColor(textColor);
                    mDanmakuView.addItemToHead(item);
                }
                textEditText.setText("");
                break;
        }
    }

    /**
     * 播放视频想
     */
    private void setVideoView() {
        //设置准备好的监听
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();//开始播放
            }
        });
        //设置播放完成
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();//重新开始播放
            }
        });

        //设置播放出错
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(OpenDanmakuActivity.this, "播放出错了", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //设置控制面板
        videoView.setMediaController(new MediaController(this));
        //设置播放地址
        videoView.setVideoPath("http://vfx.mtime.cn/Video/2016/12/12/mp4/161212190638286683_480.mp4");
        //       videoView.setVideoPath("http://192.168.11.218:8080/oppo.mp4");
    }

    /**
     * 构建弹幕的数据集合
     * @return
     */
    private List<IDanmakuItem> initItems() {
        List<IDanmakuItem> list = new ArrayList<>();
        //创建100条文本的弹幕
        for (int i = 0; i < 100; i++) {
            IDanmakuItem item = new DanmakuItem(this, i + " : plain text danmuku", mDanmakuView.getWidth());
            list.add(item);
        }
        //创建100条文本带图片的弹幕
        String msg = " : text with image   ";
        for (int i = 0; i < 100; i++) {
            ImageSpan imageSpan = new ImageSpan(this, R.drawable.em);
            SpannableString spannableString = new SpannableString(i + msg);
            spannableString.setSpan(imageSpan, spannableString.length() - 2, spannableString.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            IDanmakuItem item = new DanmakuItem(this, spannableString, mDanmakuView.getWidth(), 0, 0, 0, 1.5f);
            list.add(item);
        }
        return list;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //显示弹幕
        mDanmakuView.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //隐藏弹幕弹幕
        mDanmakuView.hide();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //清除弹幕
        mDanmakuView.clear();
    }

}
