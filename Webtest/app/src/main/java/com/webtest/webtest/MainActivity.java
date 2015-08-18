package com.webtest.webtest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Environment;
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

import java.io.File;

public class MainActivity extends Activity {
    WebView myWebView;
    private Handler mHandler;
    private boolean mFlag = false;
    //final Activity activity = this;
    //ProgressDialog mProgress;
    public MyProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String str = Environment.getExternalStorageState();
        if ( str.equals(Environment.MEDIA_MOUNTED)) {

            String dirPath = "/sdcard/SMS";
            File file = new File(dirPath);
            if( !file.exists() )  // 원하는 경로에 폴더가 있는지 확인
                file.mkdirs();
        }
        else {
            Toast.makeText(MainActivity.this, "SD Card 인식 실패", Toast.LENGTH_SHORT).show();
        }

        myWebView = (WebView) findViewById(R.id.webView1);
        myWebView.getSettings().setJavaScriptEnabled(true); // �ڹٽ�ũ��Ʈ�� ����� �� �ֵ��� ���ݴϴ�.
        myWebView.setHorizontalScrollBarEnabled(false);
        myWebView.setVerticalScrollBarEnabled(false);
        myWebView.loadUrl("http://singmystory.zz.mu");     // ?�̰��� URL (http://abc/abc.html) �� �־� �մϴ�.
        progressDialog = MyProgressDialog.show(this,"","",true,true,null);
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
    /*@Override
    public void onBackPressed(){
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }*/

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

        /*@Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            if(mProgress==null){
                mProgress = new ProgressDialog(activity);
                mProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgress.setTitle("Loading...");
                mProgress.setMessage("Please wait for few second...");
                mProgress.setCancelable(false);
                mProgress.setButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        mProgress.dismiss();
                    }
                });
                mProgress.show();
            }
        }*/
        @Override
        public void onPageFinished(WebView view, String url){
            /*if(mProgress.isShowing()){
                mProgress.dismiss();
            }*/
            myWebView.setVisibility(View.VISIBLE);
            if (progressDialog!=null)
            progressDialog.dismiss();
        }
    }
}