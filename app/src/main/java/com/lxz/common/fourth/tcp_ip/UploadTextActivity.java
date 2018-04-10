package com.lxz.common.fourth.tcp_ip;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.lxz.common.R;
import com.lxz.common.third.commontools.T;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UploadTextActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_upload)
    Button btnUpload;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    T.showShort(UploadTextActivity.this,result);
                    break;
            }
        }
    };
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_text);
        ButterKnife.bind(this);
        tvTitle.setText("TCP上传文本");
        createServerSocket();
    }

    private void createSocket() {
        new Thread(){
            @Override
            public void run() {
                try {
                    Socket s = new Socket("127.0.0.1", 10006);

                    BufferedReader bufr =
                            new BufferedReader(new FileReader(getApplicationContext().getPackageResourcePath()+"Test.java"));


                    PrintWriter out = new PrintWriter(s.getOutputStream(), true);


                    String line = null;
                    while ((line = bufr.readLine()) != null) {
                        out.println(line);
                        result = line;
                    }

                    s.shutdownOutput();//关闭客户端的输出流。相当于给流中加入一个结束标记-1.


                    BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));

                    String str = bufIn.readLine();
                    System.out.println(str);
                    handler.sendEmptyMessage(0);

                    bufr.close();
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private void createServerSocket() {
        new Thread(){
            @Override
            public void run() {
                try {
                    ServerSocket ss = new ServerSocket(10006);
                    while (true) {
                        Socket s = ss.accept();
                        String ip = s.getInetAddress().getHostAddress();
                        System.out.println(ip + "....connected");


                        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));

                        PrintWriter out = new PrintWriter(new FileWriter("server.txt"), true);

                        String line = null;

                        while ((line = bufIn.readLine()) != null) {
                            //if("over".equals(line))
                            //break;
                            out.println(line);
                        }

                        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
                        pw.println("上传成功");


                //        out.close();
                //        s.close();
                //        ss.close();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    @OnClick(R.id.btn_upload)
    public void onViewClicked() {
        createSocket();
    }
}
