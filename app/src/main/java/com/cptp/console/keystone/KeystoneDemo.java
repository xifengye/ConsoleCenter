package com.cptp.console.keystone;

import android.content.SharedPreferences;

import com.cptp.console.TixingActivity;

public class KeystoneDemo {

    public static final int KEYSTONE_CORRECTION_STEP = 10;

    public static void defValue(SharedPreferences sp){
        KeystoneVertex kv = new KeystoneVertex();
        kv.getAllKeystoneVertex();
        kv.vTopLeft.x = 0;
        kv.vTopLeft.y = 0;
        kv.vTopRight.x = 0;
        kv.vTopRight.y = 0;
        kv.vBottomLeft.x = 0;
        kv.vBottomLeft.y = 0;
        kv.vBottomRight.x = 0;
        kv.vBottomRight.y = 0;
        kv.updateAllKeystoneVertex();
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(TixingActivity.TOPLEFT,10);
        editor.putInt(TixingActivity.TOPRIGHT,10);
        editor.putInt(TixingActivity.DOWNLEFT,10);
        editor.putInt(TixingActivity.DOWNRIGHT,10);
        editor.apply();
    }

    public static int topLeft(int progress) {
        KeystoneVertex kv = new KeystoneVertex();
        kv.getAllKeystoneVertex();
        kv.vTopLeft.x = progress * KEYSTONE_CORRECTION_STEP;
        kv.vTopLeft.y = -(progress * KEYSTONE_CORRECTION_STEP);
        kv.updateAllKeystoneVertex();
        return 0;
    }

    public static int topRight(int progress) {
        KeystoneVertex kv = new KeystoneVertex();
        kv.getAllKeystoneVertex();
        kv.vTopRight.x = -(progress * KEYSTONE_CORRECTION_STEP);
        kv.vTopRight.y = -(progress * KEYSTONE_CORRECTION_STEP);
        kv.updateAllKeystoneVertex();
        return 0;
    }

    public static int bottomLeft(int progress) {
        KeystoneVertex kv = new KeystoneVertex();
        kv.getAllKeystoneVertex();
        kv.vBottomLeft.x = progress * KEYSTONE_CORRECTION_STEP;
        kv.vBottomLeft.y = progress * KEYSTONE_CORRECTION_STEP;
        kv.updateAllKeystoneVertex();
        return 0;
    }

    public static int bottomRight(int progress) {
        KeystoneVertex kv = new KeystoneVertex();
        kv.getAllKeystoneVertex();
        kv.vBottomRight.x = -(progress * KEYSTONE_CORRECTION_STEP);
        kv.vBottomRight.y = progress * KEYSTONE_CORRECTION_STEP;
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
        kv.vTopLeft.x = value;
        kv.vTopRight.x = -value;
        kv.updateAllKeystoneVertex();
        return 0;
    }


    public static int adjustBottomTo(int value) {
        KeystoneVertex kv = new KeystoneVertex();
        kv.getAllKeystoneVertex();
        kv.vBottomLeft.x = value;
        kv.vBottomRight.x = -value;
        kv.updateAllKeystoneVertex();
        return 0;
    }

    public static int adjustLeftTo(int value) {
        KeystoneVertex kv = new KeystoneVertex();
        kv.getAllKeystoneVertex();
        kv.vTopLeft.y = value;
        kv.vBottomLeft.y = -value;
        kv.updateAllKeystoneVertex();
        return 0;
    }

    public static int adjustRightTo(int value) {
        KeystoneVertex kv = new KeystoneVertex();
        kv.getAllKeystoneVertex();
        kv.vTopRight.y = value;
        kv.vBottomRight.y = -value;
        kv.updateAllKeystoneVertex();
        return 0;
    }
}
