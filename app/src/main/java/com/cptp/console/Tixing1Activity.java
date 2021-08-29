package com.cptp.console;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cptp.console.keystone.KeystoneDemo;
import com.cptp.console.keystone.KeystoneVertex;
import com.google.gson.Gson;

import static com.cptp.console.keystone.KeystoneDemo.KEYSTONE_CORRECTION_DEFAULT;
import static com.cptp.console.keystone.KeystoneDemo.KEYSTONE_CORRECTION_STEP;

public class Tixing1Activity extends Activity {
    static final String sSharedPreferencesKey = "com.cptp.console.prefs";
    private static final int MIN_LEFT_TOP = -100;
    private TextView tvLeftTop;
    private TextView tvLeftBottom;
    private TextView tvRightTop;
    private TextView tvRightBottom;
    private Button btnLeftTop;
    private Button btnLeftBottom;
    private Button btnRightTop;
    private Button btnRightBottom;
    View imageView;

//    private int leftTopY;
//    private int leftTopX;
//    private int rightTop;
//    private int leftBottom;
//    private int rightBottom;

    private Button[] btns = new Button[4];

    private int mSelectButtonIndex;
    private SharedPreferences sp;
//    public static String TOPLEFT = "TOPLEFT";
//    public static String TOPRIGHT = "TOPRIGHT";
//    public static String DOWNLEFT = "DOWNLEFT";
//    public static String DOWNRIGHT = "DOWNRIGHT";

    public static String STONE_VALUE = "STONE_VALUE_FOR_FOUR";

    private KeystoneVertex mKeystoneVertex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixing_1);
        sp = getSharedPreferences(sSharedPreferencesKey, Context.MODE_PRIVATE);
        initData();
        initView();
    }

    private void initData() {
        String keystoneString = sp.getString(STONE_VALUE, "");
        if (TextUtils.isEmpty(keystoneString)) {
            mKeystoneVertex = new KeystoneVertex();
            mKeystoneVertex.getAllKeystoneVertex();
        } else {
            Gson gson = new Gson();
            mKeystoneVertex = gson.fromJson(keystoneString, KeystoneVertex.class);
        }
        mKeystoneVertex.setScreenWidthHeight(getApplicationContext());
    }

    private void initView() {
        imageView = findViewById(R.id.imageView);

        tvLeftTop = findViewById(R.id.tvLeftTop);
        tvLeftBottom = findViewById(R.id.tvLeftBottom);
        tvRightTop = findViewById(R.id.tvRightTop);
        tvRightBottom = findViewById(R.id.tvRightBottom);

        btnLeftTop = findViewById(R.id.btnLeftTop);
        btnLeftBottom = findViewById(R.id.btnLeftBottom);
        btnRightTop = findViewById(R.id.btnRightTop);
        btnRightBottom = findViewById(R.id.btnRightBottom);

        btns[0] = btnLeftTop;
        btns[1] = btnRightTop;
        btns[2] = btnLeftBottom;
        btns[3] = btnRightBottom;

        btns[mSelectButtonIndex].setSelected(true);
    }

    private void doReset() {
        mKeystoneVertex.reset(sp);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            pressLeft();
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            pressRight();
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            pressUp();
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            pressDown();
        } else if (keyCode == KeyEvent.KEYCODE_ENTER) {
            selectNext();
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
            doReset();
        }
        imageView.invalidate();
        updateTextValue();
        Log.e("Stone", mKeystoneVertex.toString());
        return super.onKeyDown(keyCode, event);
    }

    private void selectNext() {
        btns[mSelectButtonIndex].setSelected(false);
        mSelectButtonIndex++;
        if (mSelectButtonIndex >= btns.length) {
            mSelectButtonIndex = 0;
        }
        btns[mSelectButtonIndex].setSelected(true);
    }

    private void pressDown() {
        if (btns[mSelectButtonIndex] == btnLeftTop) {
            mKeystoneVertex.leftTopDoDown(sp);
        } else if (btns[mSelectButtonIndex] == btnLeftBottom) {
            mKeystoneVertex.leftBottomDoDown(sp);
        } else if (btns[mSelectButtonIndex] == btnRightTop) {
            mKeystoneVertex.rightTopDoDown(sp);
        } else if (btns[mSelectButtonIndex] == btnRightBottom) {
            mKeystoneVertex.rightBottomDoDown(sp);
        }
    }

    private void pressUp() {
        if (btns[mSelectButtonIndex] == btnLeftTop) {
            mKeystoneVertex.leftTopDoUp(sp);
        } else if (btns[mSelectButtonIndex] == btnLeftBottom) {
            mKeystoneVertex.leftBottomDoUp(sp);
        } else if (btns[mSelectButtonIndex] == btnRightTop) {
            mKeystoneVertex.rightTopDoUp(sp);
        } else if (btns[mSelectButtonIndex] == btnRightBottom) {
            mKeystoneVertex.rightBottomDoUp(sp);
        }
    }

    private void pressRight() {
        if (btns[mSelectButtonIndex] == btnLeftTop) {
            mKeystoneVertex.leftTopDoRight(sp);
        } else if (btns[mSelectButtonIndex] == btnLeftBottom) {
            mKeystoneVertex.leftBottomDoRight(sp);
        } else if (btns[mSelectButtonIndex] == btnRightTop) {
            mKeystoneVertex.rightTopDoRight(sp);
        } else if (btns[mSelectButtonIndex] == btnRightBottom) {
            mKeystoneVertex.rightBottomDoRight(sp);
        }
    }

    private void pressLeft() {
        if (btns[mSelectButtonIndex] == btnLeftTop) {
            mKeystoneVertex.leftTopDoLeft(sp);
        } else if (btns[mSelectButtonIndex] == btnLeftBottom) {
            mKeystoneVertex.leftBottomDoLeft(sp);
        } else if (btns[mSelectButtonIndex] == btnRightTop) {
            mKeystoneVertex.rightTopDoLeft(sp);
        } else if (btns[mSelectButtonIndex] == btnRightBottom) {
            mKeystoneVertex.rightBottomDoLeft(sp);
        }
    }

    private void updateTextValue(){
        tvLeftTop.setText(mKeystoneVertex.mTopLeft.toString());
        tvLeftBottom.setText(mKeystoneVertex.mBottomLeft.toString());
        tvRightTop.setText(mKeystoneVertex.mTopRight.toString());
        tvRightBottom.setText(mKeystoneVertex.mBottomRight.toString());
    }

}
