package com.example.mkonkolowicz.kubareminder;

import android.app.Activity;
import android.content.DialogInterface;
import android.media.SoundPool;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation;


public class MainActivity extends Activity {
    private FrameLayout myLayout;
    private DialogInterface.OnClickListener I;
    private SoundPool soundPool;
    private int soundID;
    private int kubaReminderSound;
    boolean plays = false;
    boolean loaded = false;
    float actVolume;
    float maxVolume;
    float volume;
    AudioManager audioManager;
    ImageView leftEye;
    ImageView rightEye;


    public class MyView extends View {
        public MyView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            super.onDraw(canvas);
            int x = getWidth();
            int y = getHeight();
            int radius;
            radius = 100;
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);
            // Use Color.parseColor to define HTML colors
            paint.setColor(Color.parseColor("#CD5C5C"));
            canvas.drawCircle(x / 2, y / 2, radius, paint);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ImageView eyeView = new ImageView(this);
//        eyeView.setImageResource(R.drawable.eye);
//        eyeView.setAdjustViewBounds(true);
//
//        setContentView(eyeView);

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
        kubaReminderSound = soundPool.load(this,R.raw.kubacheckin,1);
        myLayout = (FrameLayout)findViewById(R.id.mainLayout);


    }

    protected void SendReminderActions(View v)
    {
        //TODO: while playing sound show eyes, hide otherwise
        //TODO: make sure eyes are centered on phone
        playSound();
        showEyes();
    }

    public void playSound()
    {
        if(loaded)
        {
            soundPool.play(kubaReminderSound,volume,volume,1,0,1f);

        }
    }

    public void showEyes()
    {
        //Eye leftEye = new Eye(this);
        //Eye rightEye = new Eye(this);
        leftEye = (ImageView)findViewById(R.id.leftEye);
        rightEye = (ImageView)findViewById(R.id.rightEye);

        //Animation myFadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);


        if(leftEye.getVisibility()!=View.VISIBLE && rightEye.getVisibility() != View.VISIBLE)
        {
            //leftEye.startAnimation(myFadeInAnimation);
            //rightEye.startAnimation(myFadeInAnimation);
        }

    }



}
