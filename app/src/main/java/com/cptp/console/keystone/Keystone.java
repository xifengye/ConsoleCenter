package com.cptp.console.keystone;
import com.cptp.console.keystone.SystemProperties;
import android.util.Log;

public class Keystone {

    private static final String PROP_KEYSTONE_UPDATE = "persist.sys.keystone.update";
    private static final String PROP_KEYSTONE_ANGLE_X = "persist.sys.keystone.angle.x";
    private static final String PROP_KEYSTONE_ANGLE_Y = "persist.sys.keystone.angle.y";
    private static final String PROP_KEYSTONE_OFFSET_X = "persist.sys.keystone.offset.x";
    private static final String PROP_KEYSTONE_OFFSET_Y = "persist.sys.keystone.offset.y";


    public float mAngleX = 0.0f;
    public float mAngleY = 0.0f;
    public float mOffsetX = 0.0f;
    public float mOffsetY = 0.0f;


    public void getAll() {
        String sAngleX = SystemProperties.get(PROP_KEYSTONE_ANGLE_X, "0.0");
        String sAngleY = SystemProperties.get(PROP_KEYSTONE_ANGLE_Y, "0.0");
        String sOffsetX = SystemProperties.get(PROP_KEYSTONE_OFFSET_X, "0.0");
        String sOffsetY = SystemProperties.get(PROP_KEYSTONE_OFFSET_Y, "0.0");
        Log.d("get-keystone", "x:"+ sAngleX + " y:" + sAngleY + " of_x:" + sOffsetX + " of_y:" + sOffsetY);

        mAngleX = Float.parseFloat(sAngleX);
        mAngleY = Float.parseFloat(sAngleY);
        mOffsetX = Float.parseFloat(sOffsetX);
        mOffsetY = Float.parseFloat(sOffsetY);
        Log.d("get-keystone", "x:"+ mAngleX + " y:" + mAngleY + " of_x:" + mOffsetX + " of_y:" + mOffsetY);
    }

    public void updateAll() {
        SystemProperties.set(PROP_KEYSTONE_ANGLE_X, Float.toString(mAngleX));
        SystemProperties.set(PROP_KEYSTONE_ANGLE_Y, Float.toString(mAngleY));
        SystemProperties.set(PROP_KEYSTONE_OFFSET_X, Float.toString(mOffsetX));
        SystemProperties.set(PROP_KEYSTONE_OFFSET_Y, Float.toString(mOffsetY));
        SystemProperties.set(PROP_KEYSTONE_UPDATE, "1");
        Log.d("set-keystone", "rk-debug setprop");
    }

    public int updateAngleX(float fvalue) {
        mAngleX = fvalue;
        updateAll();
        return 0;
    }
    public int updateAngleY(float fvalue) {
        mAngleY = fvalue;
        updateAll();
        return 0;
    }
    public int updateOffsetX(float fvalue) {
        mOffsetX = fvalue;
        updateAll();
        return 0;
    }
    public int updateOffsetY(float fvalue) {
        mOffsetY = fvalue;
        updateAll();
        return 0;
    }

}
