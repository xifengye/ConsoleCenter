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

import com.cptp.console.utils.AdjustColorCompute;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ZoomActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    static final String sSharedPreferencesKey = "com.cptp.console.prefs";
    private static final String TAG = "ScreenScaleActivity";
    private TextView mTvTitle;
    private SeekBar mSeekbarTop;
    private Button mBtnMoren;
    private SharedPreferences sp;

    private static String DB_ZOOM = "DB_ZOOM";

    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);
        sp = getSharedPreferences(sSharedPreferencesKey, Context.MODE_PRIVATE);
        initView();
        restorePref();
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mSeekbarTop = (SeekBar) findViewById(R.id.seekbar_top);
        mSeekbarTop.setOnSeekBarChangeListener(this);
        mBtnMoren = (Button) findViewById(R.id.btn_moren);
        mBtnMoren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbarTop.setProgress(50);
            }
        });
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        SharedPreferences.Editor editor = sp.edit();
        switch (seekBar.getId()) {
            case R.id.seekbar_top:
                this.progress = progress;
                editor.putInt(DB_ZOOM, progress);
                break;
            default:
                break;
        }
        editor.apply();

        execRootCmd();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    public String execRootCmd() {
        String result = "";
        DataOutputStream dos = null;
        DataInputStream dis = null;

        try {
            Process p = Runtime.getRuntime().exec("su");
            dos = new DataOutputStream(p.getOutputStream());
            dis = new DataInputStream(p.getInputStream());
            String cmd = "wm overscan " + progress * 20 + "," + progress * 20 + "," + progress * 20 + "," + progress * 20 + "";

            dos.writeBytes(cmd + "\n");
            dos.flush();
            dos.writeBytes("exit\n");
            dos.flush();
            String line = null;

            BufferedReader br = new BufferedReader(new InputStreamReader(dis));
            while ((line = br.readLine()) != null) {
                result += line;
            }
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private void restorePref() {
        progress = sp.getInt(DB_ZOOM, 0);
        mSeekbarTop.setProgress(progress);
    }
}
