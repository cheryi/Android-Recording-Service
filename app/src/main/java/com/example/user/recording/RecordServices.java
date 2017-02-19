package com.example.user.recording;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.Date;

/**
 * Created by USER on 2016/12/11.
 */
public class RecordServices extends Service {
    AudioRecordTest audioRecorder;
    private Handler handler1;

    private Runnable startrecord = new Runnable() {
        @Override
        public void run() {
            audioRecorder.starToRecording();
            handler1.postDelayed(savefile,30000);   //30秒存一次
        }
    };

    private Runnable savefile = new Runnable() {
        @Override
        public void run() {
            audioRecorder.stopRecording();
            handler1.postDelayed(startrecord,5000); //五秒後重啟
        }
    };

    @Override
    public IBinder onBind(Intent arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        audioRecorder = new AudioRecordTest();
        handler1 = new Handler();
        handler1.post(startrecord);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
}
