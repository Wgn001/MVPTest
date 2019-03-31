package gn.example.mvptest.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import gn.example.mvptest.R;

public class WebActivity extends BaseActivity {

    private static final String TAG = "WebActivity";
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        webView=findViewById(R.id.news_web);
        Intent intent=getIntent();
        String url = intent.getStringExtra("url");
        Log.i(TAG,url);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}
