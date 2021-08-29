package com.cptp.console;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cptp.console.keystone.KeystoneDemo;

public class TixingActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    static final String sSharedPreferencesKey = "com.cptp.console.prefs";
    private Button mBtnMoren;

    private SeekBar mSeekbarTopLeft;
    private SeekBar mSeekbarTopRight;
    private SeekBar mSeekbarDownLeft;
    private SeekBar mSeekbarDownRight;
    private SharedPreferences sp;
    public static String TOPLEFT = "TOPLEFT";
    public static String TOPRIGHT = "TOPRIGHT";
    public static String DOWNLEFT = "DOWNLEFT";
    public static String DOWNRIGHT = "DOWNRIGHT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixing);
        sp = getSharedPreferences(sSharedPreferencesKey, Context.MODE_PRIVATE);
        initView();
    }

    private void initView() {
        int topLeft = sp.getInt(TOPLEFT,10);
        int topRight = sp.getInt(TOPRIGHT,10);
        int downLeft = sp.getInt(DOWNLEFT,10);
        int downRight = sp.getInt(DOWNRIGHT,10);

        mSeekbarTopLeft = (SeekBar) findViewById(R.id.seekbar_top_left);
        mSeekbarTopRight = (SeekBar) findViewById(R.id.seekbar_top_right);
        mSeekbarDownLeft = (SeekBar) findViewById(R.id.seekbar_down_left);
        mSeekbarDownRight = (SeekBar) findViewById(R.id.seekbar_down_right);

        mSeekbarTopLeft.setOnSeekBarChangeListener(this);
        mSeekbarTopRight.setOnSeekBarChangeListener(this);
        mSeekbarDownLeft.setOnSeekBarChangeListener(this);
        mSeekbarDownRight.setOnSeekBarChangeListener(this);

        mSeekbarTopLeft.setProgress(topLeft);
        mSeekbarTopRight.setProgress(topRight);
        mSeekbarDownLeft.setProgress(downLeft);
        mSeekbarDownRight.setProgress(downRight);

        mBtnMoren = (Button) findViewById(R.id.btn_moren);
        mBtnMoren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeystoneDemo.defValue(sp);
                mSeekbarTopLeft.setProgress(10);
                mSeekbarTopRight.setProgress(10);
                mSeekbarDownLeft.setProgress(10);
                mSeekbarDownRight.setProgress(10);
            }
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        SharedPreferences.Editor editor = sp.edit();
        int value = progress - 10;
        switch (seekBar.getId()) {
            case R.id.seekbar_top_left:
                KeystoneDemo.topLeft(value);
                editor.putInt(TOPLEFT,progress);
                Log.d("Stone","value="+value+",progress="+progress);
                break;
            case R.id.seekbar_top_right:
                KeystoneDemo.topRight(value);
                editor.putInt(TOPRIGHT,progress);
                break;
            case R.id.seekbar_down_left:
                KeystoneDemo.bottomLeft(value);
                editor.putInt(DOWNLEFT,progress);
                break;
            case R.id.seekbar_down_right:
                KeystoneDemo.bottomRight(value);
                editor.putInt(DOWNRIGHT,progress);
                break;
            default:
                break;
        }
        editor.apply();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
