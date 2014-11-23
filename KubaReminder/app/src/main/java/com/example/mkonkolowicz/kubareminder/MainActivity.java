package com.example.mkonkolowicz.kubareminder;

import android.app.Activity;
import android.content.DialogInterface;
import android.media.SoundPool;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.Touch;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {
    private FrameLayout myLayout;
    private DialogInterface.OnClickListener I;
    private SoundPool soundPool;
    private int soundID;
    boolean plays = false;
    boolean loaded = false;
    float actVolume;
    float maxVolume;
    float volume;
    AudioManager audioManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume/maxVolume;

        soundPool = new SoundPool(10,AudioManager.STREAM_MUSIC,0);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i2)
            {
                loaded = true;
            }
        });
        soundID = soundPool.load(this,R.raw.shirebaggins,1);
        myLayout = (FrameLayout)findViewById(R.id.mainLayout);
    }

    public void PlaySound(View v)
    {
        if(loaded)
        {
            soundPool.play(soundID,volume,volume,1,0,1f);
        }
    }

    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        //return true;
    //}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
