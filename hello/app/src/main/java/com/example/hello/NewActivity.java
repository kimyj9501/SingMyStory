package com.example.hello;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class NewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
    }
    public void onBackButtonClicked(View v){
        Toast.makeText(getApplicationContext(),"돌아가기를 눌렀다.",Toast.LENGTH_LONG).show();
        finish();
    }

}
