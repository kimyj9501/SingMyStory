package com.webtest.webtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by kyk on 15. 8. 12..
 */
public class SubActivity1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);
    }

    public void terminate(View view){
        finish();
    }
}
