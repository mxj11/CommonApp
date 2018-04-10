package com.lxz.common.fourth.tcp_ip;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lxz.common.R;
import com.lxz.common.third.commontools.L;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import android.os.Handler;
import android.util.Log;

public class CSVistorActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_send_content)
    EditText etSendContent;
    @Bind(R.id.btn_send)
    Button btnSend;
    @Bind(R.id.tv_return)
    TextView tvReturn;
    @Bind(R.id.tv_rec)
    TextView tvRec;
    private Socket socket;
    private OutputStream out;
    private ServerSocket serverSocket;
    private String contentFromC;
    private String contentFromS;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    tvRec.setText("服务端收到客户端数据："+contentFromC);
                    break;
                case 1:
                    tvReturn.setText("服务端给客户回馈的数据："+contentFromS);
                    break;
            }
        }
    };
    private Socket s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csvistor);
        ButterKnife.bind(this);
        createServerSocket();
//        createSocket();
    }

    /**
     * 创建服务端
     */
    private void createServerSocket() {
        new Thread(){
            @Override
            public void run() {
                try {
                    //服务端
                    //S1：创建ServerSocket,并监听指定端口
                    ServerSocket ss = new ServerSocket(10004);
                    System.out.println("connecting....");
                    while (true){
                        //S2：获取连接过来的客户端Socket对象
                        Socket s = ss.accept();

                        String ip = s.getInetAddress().getHostAddress();
                        System.out.println(ip+"....connected");
                        //S3：从socket中获取客户端中的输入流对象
                        InputStream in = s.getInputStream();
                        //S4：创建缓冲区
                        byte[] buf = new byte[1024];

                        //S5：读取文本
                        int len = in.read(buf);
                        contentFromC = new String(buf,0,len);
                        System.out.println(contentFromC);
                        handler.sendEmptyMessage(0);


                        //服务端向客户端回复，从Socket中获取输出流
                        OutputStream out = s.getOutputStream();
                        //写入数据，发送
                        out.write("我已收到客户端来信了，我是服务端".getBytes());
//                        s.close();
//                        ss.close();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    /**
     * 创建客户端
     */
    private void createSocket(final String content) {
        new Thread(){
            @Override
            public void run() {
                try {
                    //客户端
                    //C1：创建Socket服务，并指定ip和端口
                    s = new Socket("127.0.0.1",10004);
                    //C2：在socket中获取输出流（目的：将数据写入流中，发送给服务端）
                    OutputStream out = s.getOutputStream();
                    //C3：将数据写入流中,即通过socket发送给服务器端
                    out.write(content.getBytes());

                    //获取服务端回馈输入流
                    InputStream in = s.getInputStream();
                    //创建缓冲区
                    byte[] buf = new byte[1024];

                    int len = in.read(buf);
                    contentFromS = new String(buf,0,len);
                    System.out.println(contentFromS);
                    handler.sendEmptyMessage(1);

                    s.close();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        String content = etSendContent.getText().toString().trim();
        if(!TextUtils.isEmpty(content)){
            createSocket(content);
        }else{
            Toast.makeText(this, "不能输入空", Toast.LENGTH_SHORT).show();
        }
    }

}
