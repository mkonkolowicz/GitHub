package com.example.mkonkolowicz.kubareminder;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.RelativeLayout;


public class MainActivity extends Activity {
    private RelativeLayout myLayout;
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

//Place eyes and view inside container and align with screen

    public class MyView extends View {
        public MyView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
        }

        //@Override
//        protected void onDraw(Canvas canvas) {
//            // TODO Auto-generated method stub
//            super.onDraw(canvas);
//            int x = getWidth();
//            int y = getHeight();
//            int radius;
//            radius = 100;
//            Paint paint = new Paint();
//            paint.setStyle(Paint.Style.FILL);
//            paint.setColor(Color.WHITE);
//            canvas.drawPaint(paint);
//            // Use Color.parseColor to define HTML colors
//            paint.setColor(Color.parseColor("#CD5C5C"));
//            canvas.drawCircle(x / 2, y / 2, radius, paint);
//        }

    }

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

        kubaReminderSound = soundPool.load(this,R.raw.kubacheckin,1);
        myLayout = (RelativeLayout)findViewById(R.id.mainLayout);
    }

    public void SendReminderActions(View v)
    {
        //TODO: while playing sound show eyes, hide otherwise
        //TODO: make sure eyes are centered on phone
        //TODO: Change soundpool to mediaplayer, implement on completion listener and hide eyes
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
        ImageView leftEye = (ImageView)findViewById(R.id.leftEye);
        ImageView rightEye = (ImageView)findViewById(R.id.rightEye);
        if(leftEye.getVisibility() == View.VISIBLE && rightEye.getVisibility() == View.VISIBLE)
        {
            fadeOutAnimation();
        }
        else
        {
            fadeInAnimation();
        }

    }

    public void fadeInAnimation()
    {
        ImageView leftI = (ImageView)findViewById(R.id.leftEye);
        ImageView rightI = (ImageView)findViewById(R.id.rightEye);

        leftI.setVisibility(View.INVISIBLE);
        rightI.setVisibility(View.INVISIBLE);

        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        leftI.startAnimation(myFadeInAnimation);
        rightI.startAnimation(myFadeInAnimation);
        leftI.setVisibility(View.VISIBLE);
        rightI.setVisibility(View.VISIBLE);

    }

    public void fadeOutAnimation()
    {
        ImageView leftI = (ImageView)findViewById(R.id.leftEye);
        ImageView rightI = (ImageView)findViewById(R.id.rightEye);

        leftI.setVisibility(View.VISIBLE);
        rightI.setVisibility(View.VISIBLE);

        Animation myFadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        leftI.startAnimation(myFadeOutAnimation);
        rightI.startAnimation(myFadeOutAnimation);
        leftI.setVisibility(View.INVISIBLE);
        rightI.setVisibility(View.INVISIBLE);

    }

    public void onAnimationEnd(Animation animation)
    {
//        startActivity(new Intent(SplashActivity.this, MenuActivity.class));
//        SplashActivity.this.finish();
//        i.setVisibility(View.INVISIBLE);
    }



}
