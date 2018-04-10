package com.lxz.common.home.pulltorefresh.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.extras.SoundPullEventListener;
import com.lxz.common.R;

import java.util.Arrays;
import java.util.LinkedList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PullToRefreshGridActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.pull_refresh_grid)
    PullToRefreshGridView pullRefreshGrid;
    private GridView mGridView;
    private LinkedList<String> mListItems;
    private ArrayAdapter<String> mAdapter;

    private String[] mStrings = { "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh_grid);
        ButterKnife.bind(this);
        tvTitle.setText("PullToRefreshGridView");

        mGridView = pullRefreshGrid.getRefreshableView();

        // Set a listener to be invoked when the list should be refreshed.
        pullRefreshGrid.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {

            /**
             * 下拉刷新
             * @param refreshView
             */
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                //得到当前刷新的时间
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                //设置更新时间
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                Toast.makeText(PullToRefreshGridActivity.this, "下拉刷新", Toast.LENGTH_SHORT).show();

                new GetDataTask().execute();
            }

            /**
             * 上拉刷新
             * @param refreshView
             */
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                //得到当前刷新的时间
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                //设置更新时间
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                Toast.makeText(PullToRefreshGridActivity.this, "上拉刷新!", Toast.LENGTH_SHORT).show();
                new GetDataTask().execute();
            }

        });

        /**
         * Add Sound Event Listener
         * 添加刷新事件并且发出声音
         */
        SoundPullEventListener<GridView> soundListener = new SoundPullEventListener<GridView>(this);
        soundListener.addSoundEvent(PullToRefreshBase.State.PULL_TO_REFRESH, R.raw.pull_event);
        soundListener.addSoundEvent(PullToRefreshBase.State.RESET, R.raw.reset_sound);
        soundListener.addSoundEvent(PullToRefreshBase.State.REFRESHING, R.raw.refreshing_sound);
        pullRefreshGrid.setOnPullEventListener(soundListener);

        //创建集合
        mListItems = new LinkedList<String>();

        TextView tv = new TextView(this);
        tv.setGravity(Gravity.CENTER);
        tv.setText("还没有加载数据，请下拉/上拉刷新...");
        //设置没有内容的时候显示的视图
        pullRefreshGrid.setEmptyView(tv);

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);
        mGridView.setAdapter(mAdapter);
    }

    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return mStrings;
        }

        @Override
        protected void onPostExecute(String[] result) {
            mListItems.addFirst("Added after refresh...");
            //把数组转换成集合，并且添加到LinkedList<String>集合
            mListItems.addAll(Arrays.asList(result));
            mAdapter.notifyDataSetChanged();

            // Call onRefreshComplete when the list has been refreshed.
            //把下拉和上拉状态还原
            pullRefreshGrid.onRefreshComplete();




            super.onPostExecute(result);
        }
    }
}
