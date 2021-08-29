package com.cptp.console.keystone;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;


public class KeystoneVertex {
    public static String STONE_VALUE_FOR_FOUR = "STONE_VALUE_FOR_FOUR";
    public static String STONE_VALUE_FOR_ZOOM = "STONE_VALUE_FOR_ZOOM";
    public static String STONE_VALUE_FOR_DIRECTION = "STONE_VALUE_FOR_DIRECTION";


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
    private int mScreenWidth = 200;
    private int mScreenHeight = 200;
    private String spKey;
    private int directY;
    private int directX;
    private int mMaxDirectY = 60;
    private int mMaxDirectX = 40;

    public void setSpKey(String spKey) {
        this.spKey = spKey;
    }

    public void setScreenWidthHeight(Context context) {
        mScreenWidth = context.getResources().getDisplayMetrics().widthPixels;
        mScreenHeight = context.getResources().getDisplayMetrics().heightPixels;
        mMaxDirectY = 50;//mScreenHeight / 5;
        mMaxDirectX = 50;//mScreenWidth / 5;
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
        if (mTopLeft.y > mScreenHeight) {
            mTopLeft.y = mScreenHeight;
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
        if (mTopLeft.x > mScreenWidth) {
            mTopLeft.x = mScreenWidth;
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
        if (mTopRight.y > mScreenHeight) {
            mTopRight.y = mScreenHeight;
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
        if (mTopRight.x < -mScreenWidth) {
            mTopRight.x = -mScreenWidth;
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
        if (mBottomLeft.y < -mScreenHeight) {
            mBottomLeft.y = -mScreenHeight;
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
        if (mBottomLeft.x > mScreenWidth) {
            mBottomLeft.x = mScreenWidth;
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
        if (mBottomRight.y < -mScreenHeight) {
            mBottomRight.y = -mScreenHeight;
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
        if (mBottomRight.x < -mScreenWidth) {
            mBottomRight.x = -mScreenWidth;
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
        directX = 0;
        directY = 0;
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
        sp.edit().putString(spKey, json).apply();
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

    public void zoom(SharedPreferences sp, int progress) {
        int xScale = (int) (mScreenWidth * progress / 100.0f) / 2;
        int yScale = (int) (mScreenHeight * progress / 100.0f) / 2;
        mTopLeft.x = xScale;
        mTopLeft.y = yScale;
        mTopRight.x = -xScale;
        mTopRight.y = yScale;
        mBottomLeft.x = xScale;
        mBottomLeft.y = -yScale;
        mBottomRight.x = -xScale;
        mBottomRight.y = -yScale;
        updateAllKeystoneVertex();
        updateSave(sp);
    }

    public void directUp(SharedPreferences sp) {
        directY += 1;
        if (directY > mMaxDirectY) {
            directY = mMaxDirectY;
            return;
        }
        if (directY > 0) {
            setDirectTopLeftX(mTopLeft.x + 1);
            setDirectTopLeftY(mTopLeft.y + 1);
            setDirectTopRightX(mTopRight.x - 1);
            setDirectTopRightY(mTopRight.y + 1);
        } else {
            setDirectBottomLeftX(mBottomLeft.x - 1);
            setDirectBottomLeftY(mBottomLeft.y + 1);
            setDirectBottomRightX(mBottomRight.x + 1);
            setDirectBottomRightY(mBottomRight.y + 1);
        }
        updateAllKeystoneVertex();
        updateSave(sp);
    }

    public void directDown(SharedPreferences sp) {
        directY -= 1;
        if (directY < (-mMaxDirectY)) {
            directY = -mMaxDirectY;
            return;
        }
        if (directY >=0) {
            setDirectTopLeftX(mTopLeft.x - 1);
            setDirectTopLeftY(mTopLeft.y - 1);
            setDirectTopRightX(mTopRight.x + 1);
            setDirectTopRightY(mTopRight.y - 1);
        } else {
            setDirectBottomLeftX(mBottomLeft.x + 1);
            setDirectBottomLeftY(mBottomLeft.y - 1);
            setDirectBottomRightX(mBottomRight.x - 1);
            setDirectBottomRightY(mBottomRight.y - 1);
        }
        updateAllKeystoneVertex();
        updateSave(sp);
    }

    public void directRight(SharedPreferences sp) {
        directX -= 1;
        if (directX < -mMaxDirectX) {
            directX = -mMaxDirectX;
            return;
        }
        if (directX >= 0) {
            setDirectTopLeftX(mTopLeft.x - 1);
            setDirectBottomLeftX(mBottomLeft.x - 1);
            setDirectTopLeftY(mTopLeft.y - 1);
            setDirectBottomLeftY(mBottomLeft.y + 1);
        } else {
            setDirectTopRightX(mTopRight.x - 1);
            setDirectBottomRightX(mBottomRight.x - 1);
            setDirectTopRightY(mTopRight.y + 1);
            setDirectBottomRightY(mBottomRight.y - 1);
        }
        updateAllKeystoneVertex();
        updateSave(sp);
    }

    public void directLeft(SharedPreferences sp) {
        directX += 1;
        if (directX > mMaxDirectX) {
            directX = mMaxDirectX;
            return;
        }
        if (directX > 0) {
            setDirectTopLeftX(mTopLeft.x + 1);
            setDirectTopLeftY(mTopLeft.y + 1);
            setDirectBottomLeftX(mBottomLeft.x + 1);
            setDirectBottomLeftY(mBottomLeft.y - 1);
        } else {
            setDirectTopRightX(mTopRight.x + 1);
            setDirectTopRightY(mTopRight.y - 1);
            setDirectBottomRightX(mBottomRight.x + 1);
            setDirectBottomRightY(mBottomRight.y + 1);
        }
        updateAllKeystoneVertex();
        updateSave(sp);
    }

    private boolean setDirectTopLeftX(int value) {
        boolean getMax = false;
        if (value > mMaxDirectX) {
            value = mMaxDirectX;
            getMax = true;
        }
        if (value < -mMaxDirectX) {
            value = -mMaxDirectX;
            getMax = true;
        }
        mTopLeft.x = value;
        return getMax;
    }

    private boolean setDirectTopLeftY(int value) {
        boolean getMax = false;
        if (value > mMaxDirectY) {
            value = mMaxDirectY;
            getMax = true;
        }
        if (value < -mMaxDirectY) {
            value = -mMaxDirectY;
            getMax = true;
        }
        mTopLeft.y = value;
        return getMax;
    }

    private boolean setDirectBottomLeftX(int value) {
        boolean getMax = false;
        if (value > mMaxDirectX) {
            value = mMaxDirectX;
            getMax = true;
        }
        if (value < -mMaxDirectX) {
            value = -mMaxDirectX;
            getMax = true;
        }
        mBottomLeft.x = value;
        return getMax;
    }

    private boolean setDirectBottomLeftY(int value) {
        boolean getMax = false;
        if (value > mMaxDirectY) {
            value = mMaxDirectY;
            getMax = true;
        }
        if (value < -mMaxDirectY) {
            value = -mMaxDirectY;
            getMax = true;
        }
        mBottomLeft.y = value;
        return getMax;
    }

    private boolean setDirectTopRightX(int value) {
        boolean getMax = false;
        if (value > mMaxDirectX) {
            value = mMaxDirectX;
            getMax = true;
        }
        if (value < -mMaxDirectX) {
            value = -mMaxDirectX;
            getMax = true;
        }
        mTopRight.x = value;
        return getMax;
    }

    private boolean setDirectTopRightY(int value) {
        boolean getMax = false;
        if (value > mMaxDirectY) {
            value = mMaxDirectY;
            getMax = true;
        }
        if (value < -mMaxDirectY) {
            value = -mMaxDirectY;
            getMax = true;
        }
        mTopRight.y = value;
        return getMax;
    }

    private boolean setDirectBottomRightX(int value) {
        boolean getMax = false;
        if (value > mMaxDirectX) {
            value = mMaxDirectX;
            getMax = true;
        }
        if (value < -mMaxDirectX) {
            value = -mMaxDirectX;
            getMax = true;
        }
        mBottomRight.x = value;
        return getMax;
    }

    private boolean setDirectBottomRightY(int value) {
        boolean getMax = false;
        if (value > mMaxDirectY) {
            value = mMaxDirectY;
            getMax = true;
        }
        if (value < -mMaxDirectY) {
            value = -mMaxDirectY;
            getMax = true;
        }
        mBottomRight.y = value;
        return getMax;
    }
}


