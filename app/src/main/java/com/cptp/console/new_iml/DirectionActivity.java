package com.cptp.console.new_iml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cptp.console.R;

import static com.cptp.console.keystone.KeystoneVertex.STONE_VALUE_FOR_DIRECTION;

public class DirectionActivity extends BaseActivity {

    @Override
    protected String getStoneKey() {
        return STONE_VALUE_FOR_DIRECTION;
    }

    @Override
    protected void pressEnter() {

    }

    @Override
    protected void pressDown() {
        mKeystoneVertex.directDown(sp);
    }

    @Override
    protected void pressUp() {
        mKeystoneVertex.directUp(sp);
    }

    @Override
    protected void pressRight() {
        mKeystoneVertex.directRight(sp);
    }

    @Override
    protected void pressLeft() {
        mKeystoneVertex.directLeft(sp);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_direction;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, DirectionActivity.class);
        context.startActivity(starter);
    }
}