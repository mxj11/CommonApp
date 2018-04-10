package com.lxz.common.fourth.tcp_ip;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lxz.common.R;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServerTextConvertorActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_send_content)
    EditText etSendContent;
    @Bind(R.id.btn_send)
    Button btnSend;
    @Bind(R.id.tv_tran)
    TextView tvTran;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    tvTran.setText(result);
                    break;
            }
        }
    };
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_text_convertor);
        ButterKnife.bind(this);
        tvTitle.setText("客户端发送文本到服务端转换大写");
        createServerSockdet();
    }

    private void createSocket(final String content){
        new Thread(){
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("127.0.0.1",10005);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(content.getBytes())));
                    //获取Socket输出流
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);

                    //定义一个socket读取流，读取服务端返回的大写信息。
                    BufferedReader bufIn =
                            new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String line = null;
                    while ((line = reader.readLine()) != null){
                        //写出数据到socket
                        writer.println(line);

                        //读取服务端转换后的数据
                        result = bufIn.readLine();
                        System.out.println("result:"+result);
                    }
                    handler.sendEmptyMessage(0);
                    reader.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private void createServerSockdet() {
        new Thread(){
            @Override
            public void run() {
                try {
                    ServerSocket ss = new ServerSocket(10005);
                    while (true){
                        Socket socket = ss.accept();

                        InputStream in = socket.getInputStream();
                        //获取客户端发来数据
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                        //获取Socket输出流
                        PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);

                        //读一行，转换一行
                        String line = null;
                        while ((line = reader.readLine()) != null){
                            System.out.println("读取一行数据:"+line);
                            writer.println(line.toUpperCase());
                        }
                    }
//                    socket.close();
//                    ss.close();

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
        }
    }
}
