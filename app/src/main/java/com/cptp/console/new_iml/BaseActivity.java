package com.cptp.console.new_iml;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;

import androidx.annotation.Nullable;

import com.cptp.console.keystone.KeystoneVertex;
import com.google.gson.Gson;

/**
 * @类名 BaseActivity
 * @描述
 * @作者 xifengye
 * @创建时间 2021/8/29 15:25
 * @邮箱 ye_xi_feng@163.com
 */
public abstract class BaseActivity extends Activity {
    static final String sSharedPreferencesKey = "com.cptp.console.prefs";

    protected KeystoneVertex mKeystoneVertex;
    protected SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences(sSharedPreferencesKey, Context.MODE_PRIVATE);
        initData();
    }

    public void initData() {
        String keystoneString = sp.getString(getStoneKey(), "");
        if (TextUtils.isEmpty(keystoneString)) {
            mKeystoneVertex = new KeystoneVertex();
            mKeystoneVertex.getAllKeystoneVertex();
        } else {
            Gson gson = new Gson();
            mKeystoneVertex = gson.fromJson(keystoneString, KeystoneVertex.class);
        }
        mKeystoneVertex.setSpKey(getStoneKey());
        mKeystoneVertex.setScreenWidthHeight(getApplicationContext());
    }

    protected abstract String getStoneKey();

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
            pressEnter();
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
            pressMenu();
        }
        Log.e("Stone", mKeystoneVertex.toString());
        return super.onKeyDown(keyCode, event);
    }

    protected abstract void pressMenu();

    protected abstract void pressEnter();

    protected abstract void pressDown();

    protected abstract void pressUp();

    protected abstract void pressRight();

    protected abstract void pressLeft();

}
