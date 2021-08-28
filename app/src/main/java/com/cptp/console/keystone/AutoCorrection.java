package com.cptp.console.keystone;

import android.hardware.SensorEvent;
import android.util.Log;
//import android.os.SystemProperties;
import com.cptp.console.keystone.SystemProperties;

public class AutoCorrection {

    private static final String PROP_KEYSTONE_DISPLAY_HEIGH = "persist.sys.keystone.display.h";
    private static final String PROP_KEYSTONE_DISPLAY_WIDTH = "persist.sys.keystone.display.w";

    // 1m distance => 1.2m width
    private static final float THROW_RATION = 1.0f / 1.2f;
    //
    private static int RESOLUTION_X = 1080;
    private static int RESOLUTION_Y = 1920;
    // 
    private static final float ANGLE_ALPHA = (float)Math.atan(THROW_RATION / 2.0f * ((float)RESOLUTION_Y / (float)RESOLUTION_X)) - 0.06f;

    private static final float SENSOR_VALUE_OFFSET_MIN = 0.1f;
    private static final float GRAVITY = 10.4f;

    private static final int SENSOR_VAL_NUM = 5;

    private float mLastXAxisAvgValue = 0;
    private float mLastZAxisAvgValue = 0;

    private int mState = 0;
    private long mCheckTimestamp = 0;

    private float[] xArray;
    private float[] zArray;
    private int mArrayIndex = 0;

    public AutoCorrection() {
        //RESOLUTION_X = SystemProperties.getInt(PROP_KEYSTONE_DISPLAY_WIDTH, 864);
        //RESOLUTION_Y = SystemProperties.getInt(PROP_KEYSTONE_DISPLAY_HEIGH, 480);
        RESOLUTION_X = Integer.parseInt(SystemProperties.get(PROP_KEYSTONE_DISPLAY_WIDTH, "1080"));
        RESOLUTION_Y = Integer.parseInt(SystemProperties.get(PROP_KEYSTONE_DISPLAY_HEIGH, "1920"));
        Log.i("keystone", "AutoCorrection - RESOLUTION_X=" + RESOLUTION_X + " RESOLUTION_Y=" + RESOLUTION_Y);
    }

    public void onAccelerometerSensorChanged(SensorEvent event) {
        float xValue = event.values[0];
        float yValue = event.values[1];
        float zValue = event.values[2];
        long timestamp = event.timestamp;

        switch (mState) {
            case 0: { // check change
                float xValueOffset = Math.abs(xValue - mLastXAxisAvgValue);
                float zValueOffset = Math.abs(zValue - mLastZAxisAvgValue);
                if (xValueOffset > SENSOR_VALUE_OFFSET_MIN && zValueOffset > SENSOR_VALUE_OFFSET_MIN) {
                    Log.i("keystone", "onAccelerometerSensorChanged - case0 - x: " + xValue + " - " + mLastXAxisAvgValue + " = " + xValueOffset
                            + "  z: " + zValue + " - " + mLastZAxisAvgValue + " = " + zValueOffset);
                    Log.i("keystone", "onAccelerometerSensorChanged - mState 0->1");
                    mState = 1;
                    mCheckTimestamp = timestamp;
                }
                break;
            }
            case 1: { // check again
                if ((timestamp - mCheckTimestamp) > 1000L) {
                    float xValueOffset = Math.abs(xValue - mLastXAxisAvgValue);
                    float zValueOffset = Math.abs(zValue - mLastZAxisAvgValue);
                    Log.i("keystone", "onAccelerometerSensorChanged - case1- x: " + xValue + " - " + mLastXAxisAvgValue + " = " + xValueOffset
                        + "  z:" + zValue + " - " + mLastZAxisAvgValue + " = " + zValueOffset);
                    if (xValueOffset > SENSOR_VALUE_OFFSET_MIN && zValueOffset > SENSOR_VALUE_OFFSET_MIN) {
                        Log.i("keystone", "onAccelerometerSensorChanged - mState 1->2");
                        mState = 2;
                        mArrayIndex = 0;
                    } else {
                        Log.i("keystone", "onAccelerometerSensorChanged - mState 1->0");
                        mState = 0;
                    }
                }
                break;
            }
            case 2: { // confirmed change
                if (xArray == null || zArray == null) {
                    xArray = new float[SENSOR_VAL_NUM];
                    zArray = new float[SENSOR_VAL_NUM];
                }
                if (mArrayIndex < SENSOR_VAL_NUM) {
//                    Log.i("keystone", "onAccelerometerSensorChanged - case2 - mArrayIndex:" + mArrayIndex + " xValue:" + xValue + " zValue" + zValue);
                    xArray[mArrayIndex] = xValue;
                    zArray[mArrayIndex] = zValue;
                    mArrayIndex++;
                } else {
                    float xsum = 0;
                    float zsum = 0;
                    for (int i = 0; i < SENSOR_VAL_NUM; i++) {
                        xsum += xArray[i];
                        zsum += zArray[i];
                    }
                    mLastXAxisAvgValue = xsum / SENSOR_VAL_NUM;
                    mLastZAxisAvgValue = zsum / SENSOR_VAL_NUM;
                    Log.i("keystone", "onAccelerometerSensorChanged - case2 - xsum:" + xsum + " zsum:" + zsum + " mLastXAxisAvgValue:" + mLastXAxisAvgValue + " mLastZAxisAvgValue:" + mLastZAxisAvgValue);
                    float angleOfSlope = calXZAngleOfSlope(mLastXAxisAvgValue, mLastZAxisAvgValue);
                    if (Math.abs(angleOfSlope) - 0.7f > 0.0001f) {
                        mState = 0;
                        break;
                    }
                    float rationOfTopAndBottom = calKeystoneRationOfTopAndBottom(angleOfSlope);
                    correctKeystone(rationOfTopAndBottom);
                    mState = 0;
                    Log.i("keystone", "onAccelerometerSensorChanged - mState 2->0");
                }
                break;
            }
            default:
                mState = 0;
        }
    
    }

    float calXZAngleOfSlope(float xValue, float zValue) {
        float anglex = (float) Math.asin(xValue/GRAVITY);        
        float anglez = (float) Math.acos(zValue/GRAVITY);
        float angle;
        if (anglex > 0) {
            angle = -(anglex + anglez) / 2.0f;
        } else {
            angle = (-anglex + anglez) / 2.0f;
        }
        Log.i("keystone", "AutoCorrection - calXZAngleOfSlope - anglex:" + anglex + " anglez:" + anglez + " angleAvg:" + angle);
        return angle;
    }

    float calKeystoneRationOfTopAndBottom(float angleOfSlope) {
        float ration = (float)Math.cos(angleOfSlope - ANGLE_ALPHA) / (float)Math.cos(angleOfSlope + ANGLE_ALPHA);
        Log.i("keystone", "AutoCorrection - calKeystoneRationOfTopAndBottom - angleOfSlope:" + angleOfSlope + " ANGLE_ALPHA:" + ANGLE_ALPHA + " ration:" + ration);
        return ration;
    }

    void correctKeystone(float ration) {
        Log.i("keystone", "AutoCorrection - correctKeystone - ration:" + ration);
        float rationDiff = ration - 1.0f;
        if (rationDiff > 0) {
            int adjustValue = (int) ((Math.abs(ration - 1.0f) / (2.0f * ration)) * RESOLUTION_X);
            Log.i("keystone", "AutoCorrection - correctKeystone - Keystone.adjustTopTo: " + (adjustValue));
            //Keystone.adjustTopTo(adjustValue);
            //Keystone.adjustBottomTo(0);
        } else if (rationDiff < 0) {
            float rationInvert = 1.0f / ration;
            int adjustValue = (int) ((Math.abs(rationInvert - 1.0f) / (2.0f * rationInvert)) * RESOLUTION_X);
            Log.i("keystone", "AutoCorrection - correctKeystone - Keystone.adjustBottomTo: " + (adjustValue));
            //Keystone.adjustTopTo(0);
            //Keystone.adjustBottomTo(adjustValue);
        }
    }


}



