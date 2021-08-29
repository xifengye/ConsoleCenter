package com.cptp.console.new_iml;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cptp.console.R;

import static com.cptp.console.keystone.KeystoneVertex.STONE_VALUE_FOR_ZOOM;

public class ZoomActivity extends BaseActivity {
    private SeekBar seekBar;
    private TextView tvScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        seekBar = findViewById(R.id.seekBar);
        tvScale = findViewById(R.id.tvScale);
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mKeystoneVertex.zoom(sp, progress);
                tvScale.setText(String.format("%d%%",progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar.setProgress(0);
    }

    @Override
    protected String getStoneKey() {
        return STONE_VALUE_FOR_ZOOM;
    }

    @Override
    protected void pressEnter() {

    }

    @Override
    protected void pressDown() {
        int progress = seekBar.getProgress() - 1;
        if (progress < 0) {
            progress = 0;
        }
        seekBar.setProgress(progress);
    }

    @Override
    protected void pressUp() {
        int progress = seekBar.getProgress() + 1;
        if (progress > 100) {
            progress = 100;
        }
        seekBar.setProgress(progress);
    }

    @Override
    protected void pressRight() {

    }

    @Override
    protected void pressLeft() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zoom2;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ZoomActivity.class);
        context.startActivity(starter);
    }
}