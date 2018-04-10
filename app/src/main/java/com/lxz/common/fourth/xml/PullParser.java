package com.lxz.common.fourth.xml;

import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxz on 2017/10/30 0030.
 */

public class PullParser {
    private List<Contact> list = null;
    private Contact contact = null;
    public List<Contact> pullParser(Context context){
        try {
            //1、创建XmlPullParser解析器
            XmlPullParser parser = Xml.newPullParser();
            InputStream in = context.getAssets().open("contact.xml");
            parser.setInput(in,"utf-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
//                        list = new ArrayList<Contact>();
                    break;
                    case XmlPullParser.START_TAG:
                        String tag = parser.getName();
                        if(tag.equalsIgnoreCase("contactList")){
                            list = new ArrayList<Contact>();
                        }else if(tag.equalsIgnoreCase("contact")){
                            contact = new Contact();
                            contact.setId(parser.getAttributeValue(null,"id"));
                        }else if(tag.equalsIgnoreCase("name")){
                            contact.setName(parser.nextText());
                        }else if(tag.equalsIgnoreCase("age")){
                            contact.setAge(parser.nextText());
                        }else if(tag.equalsIgnoreCase("phone")){
                            contact.setPhone(parser.nextText());
                        }else if(tag.equalsIgnoreCase("email")){
                            contact.setEmail(parser.nextText());
                        }else if(tag.equalsIgnoreCase("qq")){
                            contact.setQq(parser.nextText());
                        }

                    break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("contact")){
                            list.add(contact);
                            contact = null;
                        }
                    break;
                }
                eventType = parser.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return list;
    }
}
