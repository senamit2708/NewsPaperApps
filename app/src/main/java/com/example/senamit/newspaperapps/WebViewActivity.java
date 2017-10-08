package com.example.senamit.newspaperapps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    public static final String LOG_TAG = WebViewActivity.class.getSimpleName();

    WebView webView;

    String url = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Intent intent = getIntent();

       url= intent.getStringExtra("key");


        Log.i(LOG_TAG,"url is "+ url);
        webView = (WebView)findViewById(R.id.webview);

        startWebView(url);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }

    private void startWebView(String url) {

        Log.i(LOG_TAG, "inside startwebview method  "+url );

        webView.setWebViewClient(new WebViewClient(){


            ProgressDialog progressDialog;

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                if (progressDialog==null){
                    progressDialog = new ProgressDialog(WebViewActivity.this);
                    progressDialog.setMessage("Loading.....");
                    progressDialog.show();
                }
            }


            @Override
            public void onPageCommitVisible(WebView view, String url) {
                try {


                    Log.i(LOG_TAG,"inside page commit");
                    if (progressDialog.isShowing()) {

                        progressDialog.dismiss();
                        progressDialog = null;
                    }
                }catch (Exception exception){
                    exception.printStackTrace();
                }
                Log.i(LOG_TAG,"page commited");
            }




            //
//            public void onPageFinished(WebView view, String url) {
//                try {
//
//
//                    if (progressDialog.isShowing()) {
//
//                        progressDialog.dismiss();
//                        progressDialog = null;
//                    }
//                }catch (Exception exception){
//                    exception.printStackTrace();
//                }
//            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);


    }
}
