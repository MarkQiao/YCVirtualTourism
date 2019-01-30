package com.e_trans.virtualtourism.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.e_trans.virtualtourism.R;
import com.e_trans.virtualtourism.utils.StatusBarCompat;

import java.util.Stack;

import static com.e_trans.virtualtourism.Base.Url.getURLWEB;


public class WebViewActivity extends  AppCompatActivity {
        private String[] titles = {"贵州旅游", "魅力贵州", "梯田", "古村"};
        private WebView webview;
        private MyWebViewClient webViewClient;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.webview_activity);
            StatusBarCompat.translucentStatusBar(this, true);
            webview = (WebView) findViewById(R.id.webview);
            webViewClient = new MyWebViewClient();
            webview.getSettings().setJavaScriptEnabled(true);
            webview.setWebViewClient(webViewClient);
            webview.loadUrl(getURLWEB( getIntent().getIntExtra("type",0)));

            //http://m.auyou.cn/city/situational.asp?areano=060101  概括
            //http://m.auyou.cn/city/situational.asp?areano=060101  概括
            //http://m.auyou.cn/jingdian/list.asp?areano=060101  景点门票
        }


        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            return pageGoBack(webview,webViewClient);
        }

        public boolean pageGoBack(WebView web, MyWebViewClient client) {
            final String url = client.popLastPageUrl();
            if (url != null) {
                web.loadUrl(url);
                return true;
            }
            finish();
            return false;
        }

        class MyWebViewClient extends WebViewClient {
            /**
             * 记录URL的栈
             */
            private final Stack<String> mUrls = new Stack<>();
            /**
             * 判断页面是否加载完成
             */
            private boolean mIsLoading;
            private String mUrlBeforeRedirect;

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (mIsLoading && mUrls.size() > 0) {
                    mUrlBeforeRedirect = mUrls.pop();
                }
                recordUrl(url);
                this.mIsLoading = true;
            }

            /**
             * 记录非重定向链接, 避免刷新页面造成的重复入栈
             *
             * @param url 链接
             */
            private void recordUrl(String url) {
                //这里还可以根据自身业务来屏蔽一些链接被放入URL栈
                if (!TextUtils.isEmpty(url) && !url.equalsIgnoreCase(getLastPageUrl())) {
                    mUrls.push(url);
                } else if (!TextUtils.isEmpty(mUrlBeforeRedirect)) {
                    mUrls.push(mUrlBeforeRedirect);
                    mUrlBeforeRedirect = null;
                }
            }

            /**
             * 获取上一页的链接
             **/
            private synchronized String getLastPageUrl() {
                return mUrls.size() > 0 ? mUrls.peek() : null;
            }

            /**
             * 推出上一页链接
             */
            public String popLastPageUrl() {
                if (mUrls.size() >= 2) {
                    mUrls.pop(); //当前url
                    return mUrls.pop();
                }
                return null;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (this.mIsLoading) {
                    this.mIsLoading = false;
                }
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        }
    }
