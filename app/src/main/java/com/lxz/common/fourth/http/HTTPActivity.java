package com.lxz.common.fourth.http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lxz.common.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HTTPActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_show_note)
    TextView tvShowNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        ButterKnife.bind(this);
        tvTitle.setText("http协议");
        showNote();
    }

    private void showNote() {
        String requestNote = "http协议：对浏览器客户端和服务器端之间数据传输的格式规范。\n" +
                "一、http请求：包含请求行、请求头、请求实体。\n" +
                "1、请求行：包含http协议版本(1.0/1.1)、请求资源(url)、请求方式(get/post等)。\n" +
                "例如：GET /day09/hello HTTP/1.1\n" +
                "2、请求头：\n" +
                "Accept: text/html,image/*      -- 浏览器接受的数据类型\n" +
                "Accept-Charset: ISO-8859-1     -- 浏览器接受的编码格式\n" +
                "Accept-Encoding: gzip,compress  --浏览器接受的数据压缩格式\n" +
                "Accept-Language: en-us,zh-       --浏览器接受的语言\n" +
                "Host: www.it315.org:80          --（必须的）当前请求访问的目标地址（主机:端口）\n" +
                "If-Modified-Since: Tue, 11 Jul 2000 18:23:51 GMT  --浏览器最后的缓存时间\n" +
                "Referer: http://www.it315.org/index.jsp      -- 当前请求来自于哪里\n" +
                "User-Agent: Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0)  --浏览器类型\n" +
                "Cookie:name=eric                     -- 浏览器保存的cookie信息\n" +
                "Connection: close/Keep-Alive            -- 浏览器跟服务器连接状态。close: 连接关闭  keep-alive：保存连接。\n" +
                "Date: Tue, 11 Jul 2000 18:23:51 GMT      -- 请求发出的时间\n" +
                "3、请求实体：只有POST提交的参数会放到实体内容中\n";
        String responseNote = "\n二、http响应：包含响应行、响应头、响应实体\n" +
                "1、响应行：包含http协议版本、状态码、状态描述。\n" +
                "例如：HTTP/1.1 200 OK \n" +
                "2、响应头：\n" +
                "Location: http://www.it315.org/index.jsp   -表示重定向的地址，该头和302的状态码一起使用。\n" +
                "Server:apache tomcat                 ---表示服务器的类型\n" +
                "Content-Encoding: gzip                 -- 表示服务器发送给浏览器的数据压缩类型\n" +
                "Content-Length: 80                    --表示服务器发送给浏览器的数据长度\n" +
                "Content-Language: zh-cn               --表示服务器支持的语言\n" +
                "Content-Type: text/html; charset=GB2312   --表示服务器发送给浏览器的数据类型及内容编码\n" +
                "Last-Modified: Tue, 11 Jul 2000 18:23:51 GMT  --表示服务器资源的最后修改时间\n" +
                "Refresh: 1;url=http://www.it315.org     --表示定时刷新\n" +
                "Content-Disposition: attachment; filename=aaa.zip --表示告诉浏览器以下载方式打开资源（下载文件时用到）\n" +
                "Transfer-Encoding: chunked\n" +
                "Set-Cookie:SS=Q0=5Lb_nQ; path=/search   --表示服务器发送给浏览器的cookie信息（会话管理用到）\n" +
                "Expires: -1                           --表示通知浏览器不进行缓存\n" +
                "Cache-Control: no-cache\n" +
                "Pragma: no-cache\n" +
                "Connection: close/Keep-Alive           --表示服务器和浏览器的连接状态。close：关闭连接 keep-alive:保存连接\n";

        String diferentBetweenPostAndGet = "\n三、get和post的区别:\n" +
                "1、get请求方式:\n" +
                "a）地址栏（URI）会跟上参数数据。以？开头，多个参数之间以&分割。\n" +
                "b）GET提交参数数据有限制，不超过1KB。\n" +
                "c）GET方式不适合提交敏感密码。\n" +
                "d）注意： 浏览器直接访问的请求，默认提交方式是GET方式\n" +
                "2、post请求方式：\n" +
                "a）参数不会跟着URI后面。参数而是跟在请求的实体内容中。没有？开头，多个参数之间以&分割。\n" +
                "b）POST提交的参数数据没有限制。\n" +
                "c）POST方式提交敏感数据。\n";

        tvShowNote.setText(requestNote+responseNote+diferentBetweenPostAndGet);
    }
}
