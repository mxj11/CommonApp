package com.lxz.common.home.tablayoutandviewpager.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lxz on 2017/9/2 0002.
 */
public class MyFragment extends Fragment{

    private TextView textView;
    /**
     * 标题
     */
    private  String title;
    /**
     * 内容
     */
    private  String content;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public MyFragment() {
    }
    @SuppressWarnings({"NewApi", "ValidFragment"})
    public MyFragment(String title, String content){
        this.title = title;
        this.content = content;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        textView = new TextView(getActivity());
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        textView.setText(content);
        super.onActivityCreated(savedInstanceState);
    }
}
