package com.cptp.console.keystone;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;


public class KeystoneVertex {
    public static String STONE_VALUE = "STONE_VALUE_FOR_FOUR";


    public static final String PROP_KEYSTONE_TOP_LEFT = "persist.sys.keystone.lt";
    public static final String PROP_KEYSTONE_TOP_RIGHT = "persist.sys.keystone.rt";
    public static final String PROP_KEYSTONE_BOTTOM_LEFT = "persist.sys.keystone.lb";
    public static final String PROP_KEYSTONE_BOTTOM_RIGHT = "persist.sys.keystone.rb";
    public static final String PROP_KEYSTONE_UPDATE = "persist.sys.keystone.update";
    public static final String PROP_KEYSTONE_ANGLE_X = "persist.sys.keystone.angle.x";
    public static final String PROP_KEYSTONE_ANGLE_Y = "persist.sys.keystone.angle.y";

    public Vertex mTopLeft;
    public Vertex mTopRight;
    public Vertex mBottomLeft;
    public Vertex mBottomRight;
    private static int MAX_TOP_LEFT_Y = 200;
    private static int MAX_TOP_LEFT_X = 200;

    public void setScreenWidthHeight(Context context) {
        MAX_TOP_LEFT_X = context.getResources().getDisplayMetrics().widthPixels;
        MAX_TOP_LEFT_Y = context.getResources().getDisplayMetrics().heightPixels;
    }

    public void getAllKeystoneVertex() {
        String sTopLeft = SystemProperties.get(PROP_KEYSTONE_TOP_LEFT, "0,0");
        String sTopRight = SystemProperties.get(PROP_KEYSTONE_TOP_RIGHT, "0,0");
        String sBottomLeft = SystemProperties.get(PROP_KEYSTONE_BOTTOM_LEFT, "0,0");
        String sBottomRight = SystemProperties.get(PROP_KEYSTONE_BOTTOM_RIGHT, "0,0");


        Log.d("get-keystone", sTopLeft + " " + sTopRight + " " + sBottomLeft + " " + sBottomRight);

        mTopLeft = new Vertex(sTopLeft);
        mTopRight = new Vertex(sTopRight);
        mBottomLeft = new Vertex(sBottomLeft);
        mBottomRight = new Vertex(sBottomRight);
    }

    public void updateAllKeystoneVertex() {
        SystemProperties.set(PROP_KEYSTONE_TOP_LEFT, mTopLeft.toString());
        SystemProperties.set(PROP_KEYSTONE_TOP_RIGHT, mTopRight.toString());
        SystemProperties.set(PROP_KEYSTONE_BOTTOM_LEFT, mBottomLeft.toString());
        SystemProperties.set(PROP_KEYSTONE_BOTTOM_RIGHT, mBottomRight.toString());
        SystemProperties.set(PROP_KEYSTONE_UPDATE, "1");
        Log.d("set-keystone", "rk-debug setprop");
    }

    public void leftTopDoUp(SharedPreferences sp) {
        mTopLeft.y -= 5;
        if (mTopLeft.y < 0) {
            mTopLeft.y = 0;
        }
        SystemProperties.set(PROP_KEYSTONE_TOP_LEFT, mTopLeft.toString());
        SystemProperties.set(PROP_KEYSTONE_UPDATE, "1");
        updateSave(sp);
    }


    /**
     * 左上角减量
     */
    public void leftTopDoDown(SharedPreferences sp) {
        mTopLeft.y += 5;
        if (mTopLeft.y > MAX_TOP_LEFT_Y) {
            mTopLeft.y = MAX_TOP_LEFT_Y;
        }
        SystemProperties.set(PROP_KEYSTONE_TOP_LEFT, mTopLeft.toString());
        SystemProperties.set(PROP_KEYSTONE_UPDATE, "1");
        updateSave(sp);
    }

    /**
     * 左上角增量
     */
    public void leftTopDoLeft(SharedPreferences sp) {
        mTopLeft.x -= 5;
        if (mTopLeft.x < 0) {
            mTopLeft.x = 0;
        }
        SystemProperties.set(PROP_KEYSTONE_TOP_LEFT, mTopLeft.toString());
        SystemProperties.set(PROP_KEYSTONE_UPDATE, "1");
        updateSave(sp);
    }

    /**
     * 左上角减量
     */
    public void leftTopDoRight(SharedPreferences sp) {
        mTopLeft.x += 5;
        if (mTopLeft.x > MAX_TOP_LEFT_X) {
            mTopLeft.x = MAX_TOP_LEFT_X;
        }
        SystemProperties.set(PROP_KEYSTONE_TOP_LEFT, mTopLeft.toString());
        SystemProperties.set(PROP_KEYSTONE_UPDATE, "1");
        updateSave(sp);
    }

    /**
     * 右上角增量
     */
    public void rightTopDoUp(SharedPreferences sp) {
        mTopRight.y -= 5;
        if (mTopRight.y < 0) {
            mTopRight.y = 0;
        }
        SystemProperties.set(PROP_KEYSTONE_TOP_RIGHT, mTopRight.toString());
        SystemProperties.set(PROP_KEYSTONE_UPDATE, "1");
        updateSave(sp);
    }

    /**
     * 右上角减量
     */
    public void rightTopDoDown(SharedPreferences sp) {
        mTopRight.y += 5;
        if (mTopRight.y > MAX_TOP_LEFT_Y) {
            mTopRight.y = MAX_TOP_LEFT_Y;
        }
        SystemProperties.set(PROP_KEYSTONE_TOP_RIGHT, mTopRight.toString());
        SystemProperties.set(PROP_KEYSTONE_UPDATE, "1");
        updateSave(sp);
    }

    /**
     * 右上角增量
     */
    public void rightTopDoLeft(SharedPreferences sp) {
        mTopRight.x -= 5;
        if (mTopRight.x < -MAX_TOP_LEFT_X) {
            mTopRight.x = -MAX_TOP_LEFT_X;
        }
        SystemProperties.set(PROP_KEYSTONE_TOP_RIGHT, mTopRight.toString());
        SystemProperties.set(PROP_KEYSTONE_UPDATE, "1");
        updateSave(sp);
    }

    /**
     * 右上角减量
     */
    public void rightTopDoRight(SharedPreferences sp) {
        mTopRight.x += 5;
        if (mTopRight.x > 0) {
            mTopRight.x = 0;
        }
        SystemProperties.set(PROP_KEYSTONE_TOP_RIGHT, mTopRight.toString());
        SystemProperties.set(PROP_KEYSTONE_UPDATE, "1");
        updateSave(sp);
    }

    /**
     * 左下角增量
     */
    public void leftBottomDoUp(SharedPreferences sp) {
        mBottomLeft.y -= 5;
        if (mBottomLeft.y < -MAX_TOP_LEFT_Y) {
            mBottomLeft.y = -MAX_TOP_LEFT_Y;
        }
        SystemProperties.set(PROP_KEYSTONE_BOTTOM_LEFT, mBottomLeft.toString());
        SystemProperties.set(PROP_KEYSTONE_UPDATE, "1");
        updateSave(sp);
    }

    /**
     * 左下角减量
     */
    public void leftBottomDoDown(SharedPreferences sp) {
        mBottomLeft.y += 5;
        if (mBottomLeft.y > 0) {
            mBottomLeft.y = 0;
        }
        SystemProperties.set(PROP_KEYSTONE_BOTTOM_LEFT, mBottomLeft.toString());
        SystemProperties.set(PROP_KEYSTONE_UPDATE, "1");
        updateSave(sp);
    }

    /**
     * 左下角增量
     */
    public void leftBottomDoLeft(SharedPreferences sp) {
        mBottomLeft.x -= 5;
        if (mBottomLeft.x < 0) {
            mBottomLeft.x = 0;
        }
        SystemProperties.set(PROP_KEYSTONE_BOTTOM_LEFT, mBottomLeft.toString());
        SystemProperties.set(PROP_KEYSTONE_UPDATE, "1");
        updateSave(sp);
    }

    /**
     * 左下角减量
     */
    public void leftBottomDoRight(SharedPreferences sp) {
        mBottomLeft.x += 5;
        if (mBottomLeft.x > MAX_TOP_LEFT_X) {
            mBottomLeft.x = MAX_TOP_LEFT_X;
        }
        SystemProperties.set(PROP_KEYSTONE_BOTTOM_LEFT, mBottomLeft.toString());
        SystemProperties.set(PROP_KEYSTONE_UPDATE, "1");
        updateSave(sp);
    }

    /**
     * 右下角增量
     */
    public void rightBottomDoUp(SharedPreferences sp) {

        mBottomRight.y -= 5;
        if (mBottomRight.y < -MAX_TOP_LEFT_Y) {
            mBottomRight.y = -MAX_TOP_LEFT_Y;
        }
        SystemProperties.set(PROP_KEYSTONE_BOTTOM_RIGHT, mBottomRight.toString());
        SystemProperties.set(PROP_KEYSTONE_UPDATE, "1");
        updateSave(sp);
    }

    /**
     * 右下角减量
     */
    public void rightBottomDoDown(SharedPreferences sp) {
        mBottomRight.y += 5;
        if (mBottomRight.y > 0) {
            mBottomRight.y = 0;
        }
        SystemProperties.set(PROP_KEYSTONE_BOTTOM_RIGHT, mBottomRight.toString());
        SystemProperties.set(PROP_KEYSTONE_UPDATE, "1");
        updateSave(sp);
    }

    /**
     * 右下角增量
     */
    public void rightBottomDoLeft(SharedPreferences sp) {
        mBottomRight.x -= 5;
        if (mBottomRight.x < -MAX_TOP_LEFT_X) {
            mBottomRight.x = -MAX_TOP_LEFT_X;
        }
        SystemProperties.set(PROP_KEYSTONE_BOTTOM_RIGHT, mBottomRight.toString());
        SystemProperties.set(PROP_KEYSTONE_UPDATE, "1");
        updateSave(sp);
    }

    /**
     * 右下角减量
     */
    public void rightBottomDoRight(SharedPreferences sp) {
        mBottomRight.x += 5;
        if (mBottomRight.x > 0) {
            mBottomRight.x = 0;
        }
        SystemProperties.set(PROP_KEYSTONE_BOTTOM_RIGHT, mBottomRight.toString());
        SystemProperties.set(PROP_KEYSTONE_UPDATE, "1");
        updateSave(sp);
    }

    public void reset(SharedPreferences sp) {
        mTopLeft.x = 0;
        mTopLeft.y = 0;
        mTopRight.x = 0;
        mTopRight.y = 0;
        mBottomLeft.x = 0;
        mBottomLeft.y = 0;
        mBottomRight.x = 0;
        mBottomRight.y = 0;
        updateAllKeystoneVertex();
        updateSave(sp);
    }

    private void updateSave(SharedPreferences sp) {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        sp.edit().putString(STONE_VALUE, json).apply();
    }

    @Override
    public String toString() {
        return "{" +
                "\"mTopLeft\":" + mTopLeft +
                ", \"mTopRight\":" + mTopRight +
                ", \"mBottomLeft\":" + mBottomLeft +
                ", \"mBottomRight\":" + mBottomRight +
                '}';
    }
}


