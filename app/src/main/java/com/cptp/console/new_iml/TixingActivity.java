package com.cptp.console.new_iml;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cptp.console.R;

import static com.cptp.console.keystone.KeystoneVertex.STONE_VALUE_FOR_FOUR;

public class TixingActivity extends BaseActivity {
    private static final int MIN_LEFT_TOP = -100;
    private TextView tvLeftTop;
    private TextView tvLeftBottom;
    private TextView tvRightTop;
    private TextView tvRightBottom;
    private Button btnLeftTop;
    private Button btnLeftBottom;
    private Button btnRightTop;
    private Button btnRightBottom;

    private Button[] btns = new Button[4];

    private int mSelectButtonIndex;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    @Override
    protected String getStoneKey() {
        return STONE_VALUE_FOR_FOUR;
    }


    private void initView() {

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
        updateTextValue();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        imageView.invalidate();
        updateTextValue();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void pressEnter() {
        btns[mSelectButtonIndex].setSelected(false);
        mSelectButtonIndex++;
        if (mSelectButtonIndex >= btns.length) {
            mSelectButtonIndex = 0;
        }
        btns[mSelectButtonIndex].setSelected(true);
    }



    @Override
    public void pressDown() {
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

    @Override
    public void pressUp() {
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

    @Override
    public void pressRight() {
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

    @Override
    public void pressLeft() {
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tixing_1;
    }

    private void updateTextValue(){
        tvLeftTop.setText(mKeystoneVertex.mTopLeft.toString());
        tvLeftBottom.setText(mKeystoneVertex.mBottomLeft.toString());
        tvRightTop.setText(mKeystoneVertex.mTopRight.toString());
        tvRightBottom.setText(mKeystoneVertex.mBottomRight.toString());
    }

}
