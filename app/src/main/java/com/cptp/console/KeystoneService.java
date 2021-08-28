package com.cptp.console;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import com.cptp.console.keystone.AutoCorrection;

public class KeystoneService extends Service {

    public static final String KEYSTONE_ACTION_START = "com.rockchip.keystone.ACTION.START";

    private SensorManager mSensorManager;
    private Sensor mSensor;

    private AutoCorrection mAutoCorrection;

    public KeystoneService() {
        mAutoCorrection = new AutoCorrection();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d("KeystoneService - onCreate");
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        startListenSensor();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d("KeystoneService - onDestroy");
        stopListenSensor();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    	  try {
        	String action = intent.getAction();
        	
	        if (action != null) {
	            LogUtil.d("KeystoneService - onStartCommand - " + action);
	            if (KEYSTONE_ACTION_START.equals(action)) {
	            }
	        }
        }
        catch(Exception e){
            System.out.println("@@@cptp----Exception:" + e.getMessage());
        }
        
        return super.onStartCommand(intent, flags, startId);
    }

    private SensorEventListener mSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
//            LogUtil.d("KeystoneService - onSensorChanged -"
//                    + "value: "
//                    + sensorEvent.values[0] + " "
//                    + sensorEvent.values[1] + " "
//                    + sensorEvent.values[2]);
            mAutoCorrection.onAccelerometerSensorChanged(sensorEvent);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    boolean startListenSensor() {
        if(mSensorManager != null && mSensor != null) {
            mSensorManager.registerListener(mSensorEventListener, mSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
            LogUtil.d("KeystoneService - Start Listen Sensor Data");
            return true;
        } else {
            LogUtil.d("KeystoneService - Start Listen Sensor Data Failure !");
            return false;
        }
    }

    boolean stopListenSensor() {
        if(mSensorManager != null && mSensor != null) {
            mSensorManager.unregisterListener(mSensorEventListener, mSensor);
            LogUtil.d("KeystoneService - Stop Listen Sensor Data");
            return true;
        } else {
            LogUtil.d("KeystoneService - Stop Listen Sensor Data Failure !");
            return false;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
