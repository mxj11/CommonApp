package com.lxz.common.fourth.xml;


import android.content.Context;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by lxz on 2017/10/30 0030.
 *
 * 	SAX解析原理： 加载一点，读取一点，处理一点。对内存要求比较低。
 */

public class SAXParser {
    private List<Contact> list = new ArrayList<Contact>();
    //用于临时存储当前读到的标签名
    private String curTag;
    private Contact contact;

    public List<Contact> saxParser(Context context){
        try {
            //1.创建SAXParser对象
            javax.xml.parsers.SAXParser parser= SAXParserFactory.newInstance().newSAXParser();
            //2.调用parse方法
            InputStream in = context.getAssets().open("contact.xml");
            parser.parse(in,new DefaultHandler(){
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    curTag = qName;
                    //读取到contact的开始标签创建Contact对象
                    if("contact".equals(qName)){
                        contact = new Contact();

                        //设置id值
                        contact.setId(attributes.getValue("id"));
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    //当前文本内容
                    String content = new String(ch,start,length);

                    if("name".equals(curTag)){
                        contact.setName(content);
                    }

                    if("age".equals(curTag)){
                        contact.setAge(content);
                    }

                    if("phone".equals(curTag)){
                        contact.setPhone(content);
                    }

                    if("email".equals(curTag)){
                        contact.setEmail(content);
                    }

                    if("qq".equals(curTag)){
                        contact.setQq(content);
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    //设置空时为了避免空格换行设置到对象的属性中
                    curTag = null;
                    //读到contact的结束标签放入List中
                    if("contact".equals(qName)){
                        list.add(contact);
                    }
                }
            });


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return list;
    }
}
