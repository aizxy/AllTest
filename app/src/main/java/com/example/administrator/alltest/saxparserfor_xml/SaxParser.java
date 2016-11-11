package com.example.administrator.alltest.saxparserfor_xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Administrator on 2016/11/10 0010.
 */

public class SaxParser {
    public List<Book> goSaxParser(InputStream in){
        SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
        MyParserHandler myParser=new MyParserHandler();
        try {
            SAXParser saxParser=saxParserFactory.newSAXParser();
            saxParser.parse(in,myParser);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myParser.getBooks();

    }

    class MyParserHandler extends DefaultHandler{
        private List<Book> books;
        private StringBuilder stringBuilder;
        private Book book;

        public List<Book> getBooks(){
            return books;
        }
        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            books=new ArrayList<>();
            stringBuilder=new StringBuilder();

        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            stringBuilder.append(ch,start,length);
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if(localName.equals("book")){
                book=new Book();
            }
            stringBuilder.setLength(0);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if(localName.equals("id")){
                book.setId(Integer.parseInt(stringBuilder.toString()));
            }else if(localName.equals("name")){
                book.setName(stringBuilder.toString());
            }else if(localName.equals("price")){
                book.setPrice(Float.parseFloat(stringBuilder.toString()));
            }else if(localName.equals("book")){
                books.add(book);
            }
        }
    }
}
