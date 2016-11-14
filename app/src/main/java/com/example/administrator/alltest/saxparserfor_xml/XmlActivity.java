package com.example.administrator.alltest.saxparserfor_xml;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.administrator.alltest.R;
import com.example.administrator.alltest.domparserfor_xml.DomParser;
import com.example.administrator.alltest.pullparserfor_xml.PullBookParser;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class XmlActivity extends AppCompatActivity {


    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);
        unbinder=ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
    @OnClick({R.id.dombtn,R.id.pullbtn,R.id.saxbtn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.saxbtn:
                beginParser();
                break;
            case R.id.pullbtn:
                pullParser();
                break;
            case R.id.dombtn:
                domParser();
                break;
        }
    }

    private void beginParser(){
        try {
            InputStream in=getAssets().open("book.xml");
            SaxParser saxParser=new SaxParser();
            List<Book> listBooks= saxParser.goSaxParser(in);
            for(Book book:listBooks){
                Log.e("tag",book.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pullParser(){
        try {
            InputStream in=getAssets().open("book.xml");
            PullBookParser pullBookParser=new PullBookParser();
            List<Book> books=pullBookParser.parser(in);
            for(Book book:books){
                Log.e("pullParser",book.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    private void domParser(){
        try {
            InputStream in=getAssets().open("book.xml");
            DomParser domParser=new DomParser();
            List<Book> books=domParser.parse(in);
            for(Book book:books){
                Log.e("domparser",book.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
