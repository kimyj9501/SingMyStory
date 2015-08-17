package com.webtest.webtest;

/**
 * Created by kyk on 15. 8. 17..
 */
import android.os.Handler;
import android.os.Message;
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
import android.widget.Toast;

public class Sub4Activity extends Activity {
    WebView myWebView;
    private Handler mHandler;
    private boolean mFlag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub3);
        myWebView = (WebView) findViewById(R.id.webView1);
        myWebView.getSettings().setJavaScriptEnabled(true); // �ڹٽ�ũ��Ʈ�� ����� �� �ֵ��� ���ݴϴ�.
        myWebView.setHorizontalScrollBarEnabled(false);
        myWebView.setVerticalScrollBarEnabled(false);
        myWebView.loadUrl("http://singmystory.zz.mu/wp-admin/profile.php");     // ?�̰��� URL (http://abc/abc.html) �� �־� �մϴ�.
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
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what ==0){
                    mFlag = false;
                }
            }
        };
    }
    public void onClick0(View v){
        Intent intent_iB0 = new Intent(getApplicationContext(), Sub1Activity.class);
        startActivity(intent_iB0);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }
    public void onClick1(View v){
        Intent intent_iB1 = new Intent(getApplicationContext(), Sub2Activity.class);
        startActivity(intent_iB1);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }
    public  void onClick2(View v){
        Intent intent_iB2 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent_iB2);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }
    public void onClick3(View v){
        Intent intent_iB3 = new Intent(getApplicationContext(), Sub3Activity.class);
        startActivity(intent_iB3);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }
    public void onClick4(View v){
        Intent intent_iB4 = new Intent(getApplicationContext(), Sub4Activity.class);
        startActivity(intent_iB4);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }
    // ����̽� ����� back ��ư �ν� ===========================================
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (myWebView.canGoBack()) {
                myWebView.goBack();
                return false;
            } else {
                if (!mFlag) {
                    Toast.makeText(this, "'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
                    mFlag = true;
                    mHandler.sendEmptyMessageDelayed(0, 2000);
                    return false;
                } else {
                    finish();
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    // �� �� ������ URL �ν�
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
            }
            else {
                view.loadUrl(url);
                return true;
            }
        }
    }
}