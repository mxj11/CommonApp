package com.lxz.common.fourth.udp;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.LinearLayoutManager;

import com.lxz.common.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.lxz.common.app.MyApplication;
import com.lxz.common.third.commontools.InputUtil;
import com.lxz.common.third.commontools.L;
import com.lxz.common.third.commontools.NetUtils;
import com.lxz.common.third.commontools.OutputUtil;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Handler;
import android.widget.Toast;

/**
 * 定义一个udp发送端。
 思路：
 1，建立updsocket服务。
 2，提供数据，并将数据封装到数据包中。
 3，通过socket服务的发送功能，将数据包发出去。
 4，关闭资源。

 定义udp的接收端。
 思路：
 1，定义udpsocket服务。通常会监听一个端口。其实就是给这个接收网络应用程序定义数字标识。
 方便于明确哪些数据过来该应用程序可以处理。

 2，定义一个数据包，因为要存储接收到的字节数据。
 因为数据包对象中有更多功能可以提取字节数据中的不同数据信息。
 3，通过socket服务的receive方法将收到的数据存入已定义好的数据包中。
 4，通过数据包对象的特有功能。将这些不同的数据取出。打印在控制台上。
 5，关闭资源。
 */
public class UDPChatActvity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rv_chat)
    RecyclerView rvChat;
    @Bind(R.id.et_text)
    EditText etText;
    @Bind(R.id.btn_send)
    AppCompatButton btnSend;
    private DatagramSocket sendSocket;
    private DatagramSocket receSocket;
    private List<Map<Integer,String>> chatContents = new ArrayList<>();
    private ChatReclerViewAdapter adapter;
    private OkHttpClient client = new OkHttpClient();
    private boolean isSendClient = false;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    setAdapter();
                    etText.setText("");
                    break;
                case 3:
                    writeListIntoSDcard(chatContents);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udpchat_actvity);
        ButterKnife.bind(this);
        tvTitle.setText("聊天室");
        try {
            sendSocket = new DatagramSocket();
            receSocket = new DatagramSocket(10001);
            receive();
            List<Map<Integer, String>> list = readListFromSDcard();
            if(list != null){//sd卡有数据
                chatContents = list;
            }
            setAdapter();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private void setAdapter() {
        rvChat.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        if(adapter == null){
            adapter = new ChatReclerViewAdapter(this,chatContents);
            rvChat.setAdapter(adapter);
        }else {
            adapter.setChatContents(chatContents);
//            adapter.notifyDataSetChanged();
//            rvChat.smoothScrollToPosition(adapter.getItemCount()-1);
//            adapter.notifyItemInserted(chatContents.size());
            rvChat.scrollToPosition(adapter.getItemCount()-1);

        }
        rvChat.setItemAnimator(new DefaultItemAnimator());
    }

    private void receive() {
        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        byte[] buf = new byte[1024];
                        DatagramPacket dp = new DatagramPacket(buf,buf.length);
                        receSocket.receive(dp);
                        L.e("ip:"+dp.getAddress().getHostAddress());
                        L.e("data:"+new String(dp.getData(),0,dp.getLength()));
                        HashMap<Integer,String> map = new HashMap<Integer, String>();
                        map.put(1,new String(dp.getData(),0,dp.getLength()));
                        if(!isSendClient){
                            chatContents.add(map);
                            handler.sendEmptyMessage(3);
                        }
                        isSendClient = false;
                        handler.sendEmptyMessage(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        send();
    }

    private void send() {
        final String content = etText.getText().toString().trim();
        if(!TextUtils.isEmpty(content)){
            isSendClient = true;
            HashMap map = new HashMap<Integer, String>();
            map.put(0,content);
            chatContents.add(map);
            setAdapter();
            writeListIntoSDcard(chatContents);
            etText.setText("");
            final byte[] buf = content.getBytes();
            new Thread(){
                @Override
                public void run() {
                    try {
                        String broadcastIP = NetUtils.getBroadcastIP(MyApplication.getMyApplicationInstance());
                        System.out.println(broadcastIP);
                        DatagramPacket dp = new DatagramPacket(buf,buf.length, InetAddress.getByName(broadcastIP),10001);
                        sendSocket.send(dp);

                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        }else{

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(sendSocket != null){
            sendSocket.close();
        }
        if(receSocket != null){
            receSocket.close();
        }
    }

    /**
     * write into sdcard (object)
     * @param list
     */
    private void writeListIntoSDcard(List<Map<Integer,String>> list){
        if (new OutputUtil<Map<Integer,String>>().writeListIntoSDcard("chat.out", list)) {
//            Toast.makeText(UDPChatActvity.this, "写入SD卡成功", Toast.LENGTH_SHORT).show();
            L.e("写入SD卡成功");
        }else {
//            Toast.makeText(UDPChatActvity.this, "写入SD卡失败", Toast.LENGTH_SHORT).show();
            L.e("写入SD卡失败");
        }


    }
    /**
     * rean from sdcard (集合)
     */
    private List<Map<Integer,String>> readListFromSDcard(){
        List<Map<Integer,String>> list = new InputUtil<Map<Integer,String>>().readListFromSdCard("chat.out");
        if (list == null) {
            L.e("SD卡读取失败");
//            Toast.makeText(UDPChatActvity.this, "SD卡读取失败", Toast.LENGTH_SHORT).show();
        } else {
//            StringBuffer sb = new StringBuffer();
//            for (int i = 0; i < list.size(); i++) {
//
//            }
            L.e("SD卡读取成功");
//            Toast.makeText(UDPChatActvity.this, "SD卡读取成功"+sb, Toast.LENGTH_SHORT).show();
        }
        return list;

    }
}
