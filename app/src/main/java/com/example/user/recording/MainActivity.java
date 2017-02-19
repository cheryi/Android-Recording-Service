package com.example.user.recording;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //使用手機裝置
    private static final int REQUEST_MIC=1;
    private static final int REQUEST_SD=2;

    //使用者回復權限請求時
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case REQUEST_SD:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //取得權限，進行檔案存取
                } else {
                    //使用者拒絕權限，停用檔案存取功能
                    new AlertDialog.Builder(this).setMessage("Need your permission").setPositiveButton("OK",null).show();
                }
                return;
            case REQUEST_MIC:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //取得權限
                } else {
                    //使用者拒絕權限，停用
                    new AlertDialog.Builder(this).setMessage("Need your permission").setPositiveButton("OK",null).show();
                }
                return;
        }
    }

    AudioRecordTest recorder = new AudioRecordTest();
    Intent ServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //查看權限
        int permission_MIC= ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        if(permission_MIC!= PackageManager.PERMISSION_GRANTED){
            //未取得權限,向使用者要求
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},REQUEST_MIC);
        }
        else {
            //已有權限
        }
        int permission_SD= ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission_SD != PackageManager.PERMISSION_GRANTED) {
            // 無權限，向使用者請求
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_SD);
        }else{
            //已有權限
        }


        ServiceIntent = new Intent(this,RecordServices.class);
        startService(ServiceIntent);

        //start的功能
        Button start = (Button)findViewById(R.id.startbtn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //recorder.startRecording();
            }
        });

        //stop的功能
        Button stop = (Button) findViewById(R.id.stopbtn);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recorder.stopRecording();
            }
        });

    }
}
