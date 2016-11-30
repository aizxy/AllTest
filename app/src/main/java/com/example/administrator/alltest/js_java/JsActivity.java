package com.example.administrator.alltest.js_java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.alltest.R;

public class JsActivity extends AppCompatActivity {

    private WebView webView;
    private Button btn;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js);
        webView= (WebView) findViewById(R.id.js_webView);
        webView.getSettings().setJavaScriptEnabled(true);
        tv= (TextView) findViewById(R.id.js_text);
        webView.addJavascriptInterface(new AndroidBridge(tv),"android");
        webView.loadUrl("file:///android_asset/index.html");
        btn= (Button) findViewById(R.id.js_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("javascript:useJs()");
            }
        });
    }
}

class AndroidBridge{
    private TextView tv;
    public AndroidBridge(TextView tv){
        this.tv=tv;
    }
    @JavascriptInterface
    public void changeText(){
        tv.setText("收到！！!");
    }
}
