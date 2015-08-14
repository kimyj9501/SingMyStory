package com.webtest.webtest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
/**
 * Created by kyk on 15. 8. 13..
 */
public class SubActivity2 extends Activity {
    WebView myWebView;
    public static Activity subActivity2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        myWebView = (WebView) findViewById(R.id.webView3);
        myWebView.getSettings().setJavaScriptEnabled(true); // �ڹٽ�ũ��Ʈ�� ����� �� �ֵ��� ���ݴϴ�.
        myWebView.loadUrl("http://singmystory.zz.mu/story");     // ?�̰��� URL (http://abc/abc.html) �� �־� �մϴ�.
        // file:///abc/abc.html ó�� ����� �� �ֽ��ϴ�.
        myWebView.setWebViewClient(new myWebViewClient());
        // �ٿ� �ε� �� �� �ֵ��� ���ִ� �Լ� ==================================
        myWebView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                // �ٿ�ε� ���� �� ������ ���
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setType(mimetype);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        /*SubActivity1 subActivity1 = (SubActivity1)SubActivity1.subActivity1;
        if(subActivity1 != null){
            subActivity1.finish();
        }
        subActivity2 = SubActivity2.this;*/
    }
    //button click event
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bottomButton1:

                break;
            case R.id.bottomButton2:
                Intent intent1 = new Intent(this, SubActivity1.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent1);
                break;
            case R.id.bottomButton3:
                Intent intent5 = new Intent(this, MainActivity.class);
                intent5.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent5);
                break;
            case R.id.bottomButton4:
                Intent intent2 = new Intent(this, SubActivity2.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent2);
                break;
            case R.id.bottomButton5:

                break;
        }
    }

    private class myWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // ��ȭ�ɱ� (�۹̼Ǽ���) Intent.ACTION_CALL , Intent.ACTION_DIAL
            if (url.contains("tel:")) {
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(url)));
                return true;
            }
            // ���� ������ Intent.ACTION_SENDTO
            else if (url.contains("mailto:")) {
                startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse(url)));
                return true;
            } else {
                view.loadUrl(url);
                return true;
            }
        }
    }
}
