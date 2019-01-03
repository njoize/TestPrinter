package com.projectshoponline.app_new_tabhost;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


public class Page_Web_1 extends AppCompatActivity {


    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_web_1);
        //////////////////
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        ////////////////

        //Button
        Button b_next_1 = (Button) findViewById(R.id.b_next_1);
        b_next_1.setOnClickListener (new View.OnClickListener() {

            public void onClick(View V) {
                Intent intent =  new Intent(V.getContext(), TabHostMENU.class);
                startActivityForResult(intent, 0);
            }

        });//Button




        myWebView = (WebView) findViewById(R.id.webview);
        // Configure related browser settings
        myWebView.getSettings().setLoadsImagesAutomatically(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // Configure the client to use when opening URLs
        myWebView.setWebViewClient(new MyBrowser());
        // Load the initial URL
        myWebView.loadUrl("http://brainwakecafe.com/app/login.php");
    }

    // Manages the behavior when URLs are loaded
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
