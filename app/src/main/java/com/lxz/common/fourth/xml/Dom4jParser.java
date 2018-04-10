package com.lxz.common.fourth.xml;

import android.content.Context;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lxz on 2017/10/30 0030.
 *
 * DOM解析原理：一次性把xml文档加载进内存，然后在内存中构建Document树。
 对内存要求比较要。
 缺点： 不适合读取大容量的xml文件，容易导致内存溢出。

 *
 *
 * 节点：
 Iterator  Element.nodeIterator();  //获取当前标签节点下的所有子节点

 标签：
 Element  Document.getRootElement();  //获取xml文档的根标签
 Element   ELement.element("标签名") //指定名称的第一个子标签
 Iterator<Element> Element.elementIterator("标签名");// 指定名称的所有子标签
 List<Element>	 Element.elements(); //获取所有子标签

 属性：
 String   Element.attributeValue("属性名") //获取指定名称的属性值
 Attribute    Element.attribute("属性名")；//获取指定名称的属性对象
 Attribute.getName()  //获取属性名称
 Attibute.getValue()  //获取属性值
 List<Attribute>	 Element.attributes();  //获取所有属性对象
 Iterator<Attribute>		Element.attibuteIterator(); //获取所有属性对象

 文本：
 Element.getText();  //获取当前标签的文本
 Element.elementText("标签名") //获取当前标签的指定名称的子标签的文本内容

 2.1 写出内容到xml文档
 XMLWriter writer = new XMLWriter(OutputStream, OutputForamt)
 wirter.write(Document);

 2.2 修改xml文档的API
 增加：
 DocumentHelper.createDocument()  增加文档
 addElement("名称")  增加标签
 addAttribute("名称"，“值”)  增加属性
 修改：
 Attribute.setValue("值")  修改属性值
 Element.addAtribute("同名的属性名","值")  修改同名的属性值
 Element.setText("内容")  修改文本内容
 删除
 Element.detach();  删除标签
 Attribute.detach();  删除属性


 */

public class Dom4jParser {
    //Dom4j解析xml
    public List<Contact> dom4jparser(Context context){
        List<Contact> list = null;
        //创建一个xml解析器
        SAXReader reader = new SAXReader();
        try {
            //读取xml文件
            InputStream in = context.getAssets().open("contact.xml");
            Document document = reader.read(in);
            Iterator<Element> it = document.getRootElement().elementIterator("contact");
            list = new ArrayList<>();
            while (it.hasNext()){
                Element elem = it.next();
                Contact contact = new Contact();
                contact.setId(elem.attributeValue("id"));
                contact.setName(elem.elementText("name"));
                contact.setAge(elem.elementText("age"));
                contact.setPhone(elem.elementText("phone"));
                contact.setEmail(elem.elementText("email"));
                contact.setQq(elem.elementText("qq"));
                list.add(contact);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
