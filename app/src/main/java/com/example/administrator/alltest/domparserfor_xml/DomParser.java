package com.example.administrator.alltest.domparserfor_xml;

import android.util.Log;

import com.example.administrator.alltest.saxparserfor_xml.Book;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Administrator on 2016/11/14 0014.
 */

public class DomParser {

    public List<Book> parse(InputStream in) throws ParserConfigurationException, IOException, SAXException {
        List<Book> books=new ArrayList<>();
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=factory.newDocumentBuilder();
        Document doc=builder.parse(in);
        Element rootElement=doc.getDocumentElement();
        NodeList items=rootElement.getElementsByTagName("book");

        for(int i=0;i<items.getLength();i++){
            Book book=new Book();
            Node item=items.item(i);
            for(int k=0;k<item.getAttributes().getLength();k++){
                if(item.getAttributes().item(k).getNodeName().equals("aaa")){
                    Log.e("aaa","aaa = " + item.getAttributes().item(k).getNodeValue());
                }
            }

            NodeList properties=item.getChildNodes();
            for(int j=0;j<properties.getLength();j++){
                Node property=properties.item(j);
                String nodeName=property.getNodeName();
                if(nodeName.equals("id")){
                    book.setId(Integer.parseInt(property.getFirstChild().getNodeValue()));
                }else if(nodeName.equals("name")){
                    book.setName(property.getFirstChild().getNodeValue());
                }else if(nodeName.equals("price")){
                    book.setPrice(Float.parseFloat(property.getFirstChild().getNodeValue()));
                }
            }
            books.add(book);
        }
        return books;
    }
}
