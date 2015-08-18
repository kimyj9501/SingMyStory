package com.webtest.webtest;

/**
 * Created by kyk on 15. 8. 18..
 */
import java.io.File;
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SDCardFilesActivity extends Activity{
    private ListView fFind_ListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sdcard_search);


        initView();

        String ext = Environment.getExternalStorageState();
        if(ext.equals(Environment.MEDIA_MOUNTED)){
            findFolder();
        }else{
            Log.i("MAIN","Not find SDCard");
        }
    }

    private void initView(){
        fFind_ListView = (ListView)findViewById(R.id.Find_ListView);
    }

    private void findFolder(){
        ArrayList<String> fName = new ArrayList<String>();
        File files = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        ArrayAdapter<String> filelist = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,fName);
        if(files.listFiles().length>0){
            for(File file : files.listFiles()){
                fName.add(file.getName());
            }
        }
        files = null;
        fFind_ListView.setAdapter(filelist);
    }
}