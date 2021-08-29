package com.cptp.console.keystone;

import android.content.SharedPreferences;

import com.cptp.console.TixingActivity;

public class KeystoneDemo {

    public static final int KEYSTONE_CORRECTION_STEP = 1;
    public static final int KEYSTONE_CORRECTION_DEFAULT = 0;

    public static void defValue(SharedPreferences sp){
        KeystoneVertex kv = new KeystoneVertex();
        kv.getAllKeystoneVertex();
        kv.mTopLeft.x = 0;
        kv.mTopLeft.y = 0;
        kv.mTopRight.x = 0;
        kv.mTopRight.y = 0;
        kv.mBottomLeft.x = 0;
        kv.mBottomLeft.y = 0;
        kv.mBottomRight.x = 0;
        kv.mBottomRight.y = 0;
        kv.updateAllKeystoneVertex();
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(TixingActivity.TOPLEFT,KEYSTONE_CORRECTION_DEFAULT);
        editor.putInt(TixingActivity.TOPRIGHT,KEYSTONE_CORRECTION_DEFAULT);
        editor.putInt(TixingActivity.DOWNLEFT,KEYSTONE_CORRECTION_DEFAULT);
        editor.putInt(TixingActivity.DOWNRIGHT,KEYSTONE_CORRECTION_DEFAULT);
        editor.apply();
    }

    public static int topLeft(int progress) {
        KeystoneVertex kv = new KeystoneVertex();
        kv.getAllKeystoneVertex();
        kv.mTopLeft.x = progress * KEYSTONE_CORRECTION_STEP;
        kv.mTopLeft.y = -(progress * KEYSTONE_CORRECTION_STEP);
        kv.updateAllKeystoneVertex();
        return 0;
    }

    public static int topRight(int progress) {
        KeystoneVertex kv = new KeystoneVertex();
        kv.getAllKeystoneVertex();
        kv.mTopRight.x = -(progress * KEYSTONE_CORRECTION_STEP);
        kv.mTopRight.y = -(progress * KEYSTONE_CORRECTION_STEP);
        kv.updateAllKeystoneVertex();
        return 0;
    }

    public static int bottomLeft(int progress) {
        KeystoneVertex kv = new KeystoneVertex();
        kv.getAllKeystoneVertex();
        kv.mBottomLeft.x = progress * KEYSTONE_CORRECTION_STEP;
        kv.mBottomLeft.y = progress * KEYSTONE_CORRECTION_STEP;
        kv.updateAllKeystoneVertex();
        return 0;
    }

    public static int bottomRight(int progress) {
        KeystoneVertex kv = new KeystoneVertex();
        kv.getAllKeystoneVertex();
        kv.mBottomRight.x = -(progress * KEYSTONE_CORRECTION_STEP);
        kv.mBottomRight.y = progress * KEYSTONE_CORRECTION_STEP;
        kv.updateAllKeystoneVertex();
        return 0;
    }


    public static KeystoneVertex getVertex() {
        KeystoneVertex kv = new KeystoneVertex();
        kv.getAllKeystoneVertex();
        return kv;
    }

    public static int adjustTopTo(int value) {
        KeystoneVertex kv = new KeystoneVertex();
        kv.getAllKeystoneVertex();
        kv.mTopLeft.x = value;
        kv.mTopRight.x = -value;
        kv.updateAllKeystoneVertex();
        return 0;
    }


    public static int adjustBottomTo(int value) {
        KeystoneVertex kv = new KeystoneVertex();
        kv.getAllKeystoneVertex();
        kv.mBottomLeft.x = value;
        kv.mBottomRight.x = -value;
        kv.updateAllKeystoneVertex();
        return 0;
    }

    public static int adjustLeftTo(int value) {
        KeystoneVertex kv = new KeystoneVertex();
        kv.getAllKeystoneVertex();
        kv.mTopLeft.y = value;
        kv.mBottomLeft.y = -value;
        kv.updateAllKeystoneVertex();
        return 0;
    }

    public static int adjustRightTo(int value) {
        KeystoneVertex kv = new KeystoneVertex();
        kv.getAllKeystoneVertex();
        kv.mTopRight.y = value;
        kv.mBottomRight.y = -value;
        kv.updateAllKeystoneVertex();
        return 0;
    }
}
