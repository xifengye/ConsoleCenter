package com.cptp.console;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;

import com.cptp.console.keystone.KeystoneDemo;
import com.cptp.console.keystone.SystemProperties;
import com.cptp.console.new_iml.TixingActivity;
import com.cptp.console.new_iml.ZoomActivity;
import com.cptp.console.utils.AdjustColorCompute;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class HomeActivity extends Activity {
    static final String sSharedPreferencesKey = "com.cptp.console.prefs";
    static String DB_CONTRAST_VALUE = "db_contrast";
    static String DB_LIGHT_VALUE = "db_light";
    static String DB_HWROTATION_VALUE = "db_hwrotation";
    static String DB_GAMMA_VALUE = "db_gamma";
    private Button btn_tixing;
    private Button btn_tixing1;
    //private Button btn_suofang;
    private Button btn_daozhi;
    private SeekBar mSeekbarLiangdu;
    private SeekBar mSeekbarContrast;
    //    private SeekBar mSeekbarGamma;
    AdjustColorCompute mColorCompute;
    private int mLight = 10;
    private int mContrast = 10;
    private int mGamma = 200;
    private SharedPreferences sp;
    private Button mBtnDef;
    public int hwrotation;

		/*add qhy 20200528*/
	  private void displaySystemInfo() {
	      try {
	          if (!"ChipTrip".equals(SystemProperties.get("ro.pcba.company.name"))) {
	              finish();
	          }
	          String pwd = "";
	          if (!(pwd = SysProp.get("ro.pcba.company.name", ""))
	                  .equals("ChipTrip")) {
	              System.exit(0);
	          }
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }
	  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mColorCompute = new AdjustColorCompute(this);
        sp = getSharedPreferences(sSharedPreferencesKey, Context.MODE_PRIVATE);
        initView();
        restorePref();
        
        displaySystemInfo(); //add qhy 20200528
    }


    public void initView() {
        View btn_zoom = (Button) findViewById(R.id.btn_zoom);
        btn_zoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZoomActivity.start(HomeActivity.this);
            }
        });
        btn_daozhi = (Button) findViewById(R.id.btn_daozhi);
        btn_daozhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execRootCmd();
            }
        });
/*
        btn_suofang = (Button) findViewById(R.id.btn_suofang);
        btn_suofang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ZoomActivity.class));
            }
        });
*/ //del qhy 20200601 delete zoom
        btn_tixing = (Button) findViewById(R.id.btn_tixing);
        btn_tixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, com.cptp.console.TixingActivity.class));
            }
        });
        btn_tixing1 = (Button) findViewById(R.id.btn_tixing1);
        btn_tixing1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, TixingActivity.class));
            }
        });
        btn_tixing1.requestFocus();
        mSeekbarLiangdu = (SeekBar) findViewById(R.id.seekbar_liangdu);

        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        float process = localLayoutParams.screenBrightness * 255;
        mSeekbarLiangdu.setProgress((int) process);
        mSeekbarLiangdu.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                AdjustColorCompute.USE_MAP_TABLE = true;
                mColorCompute.computeLUTData(progress, mContrast);
                mLight = progress;

                SharedPreferences.Editor editor = sp.edit();
                editor.putInt(DB_LIGHT_VALUE, mLight);
                editor.apply();
                mColorCompute.extendXRGBData();
                mColorCompute.writeToFile();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mSeekbarContrast = (SeekBar) findViewById(R.id.seekbar_contrast);
        mSeekbarContrast.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                AdjustColorCompute.USE_MAP_TABLE = true;
                mColorCompute.computeLUTData(mLight, progress);
                mContrast = progress;

                SharedPreferences.Editor editor = sp.edit();
                editor.putInt(DB_CONTRAST_VALUE, mContrast);
                editor.apply();
                mColorCompute.extendXRGBData();
                mColorCompute.writeToFile();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

//        mSeekbarGamma = (SeekBar) findViewById(R.id.seekbar_gamma);
//        mSeekbarGamma.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                if (progress < 10) {
//                    return;
//                }
//                mGamma = progress;
//                if (AdjustColorCompute.USE_MAP_TABLE) {
//                    AdjustColorCompute.USE_MAP_TABLE = false;
//                }
//                mColorCompute.computeGamma(progress / 200.0f);
//
//                mColorCompute.extendXRGBData();
//                mColorCompute.writeToFile();
//                SharedPreferences.Editor editor = sp.edit();
//                editor.putInt(DB_GAMMA_VALUE, mGamma);
//                editor.apply();
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//            }
//        });


        mBtnDef = (Button) findViewById(R.id.btn_def);
        mBtnDef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeystoneDemo.defValue(sp);
                mSeekbarLiangdu.setProgress(10);
                mSeekbarContrast.setProgress(10);

                hwrotation = 0;
                execRootCmd();
//                mSeekbarGamma.setProgress(200);
            }
        });
    }

    public void flip(){
        String rotation = null;
        Log.d("cptp-direction", "hwrotation = " + hwrotation);
        if (hwrotation == 0) {
            rotation = "0";
            hwrotation = 180;
        } else if (hwrotation == 180) {
            rotation = "2";
            hwrotation = 0;
        }

        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(DB_HWROTATION_VALUE, hwrotation);
        editor.apply();

        //String cmd_disable ="content insert --uri content://settings/system --bind name:s:accelerometer_rotation --bind value:i:0"; //diable auto
        String cmd = "content insert --uri content://settings/system --bind name:s:user_rotation --bind value:i:" + rotation;
        try {
            //java.lang.Process  proc = Runtime.getRuntime().exec(new String[]{"sh","-c",cmd_disable});
            //proc.waitFor();
            java.lang.Process proc = Runtime.getRuntime().exec(new String[]{"sh", "-c", cmd});
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String execRootCmd() {
        String rotation = null;
        Log.d("cptp-direction", "hwrotation = " + hwrotation);
        if (hwrotation == 0) {
            rotation = "0";
            hwrotation = 180;
        } else if (hwrotation == 180) {
            rotation = "2";
            hwrotation = 0;
        }

        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(DB_HWROTATION_VALUE, hwrotation);
        editor.apply();

        String cmd = "content insert --uri content://settings/system --bind name:s:user_rotation --bind value:i:" + rotation;
        String result = "";
        DataOutputStream dos = null;
        DataInputStream dis = null;

        try {
            //Process p = Runtime.getRuntime().exec("/system/xbin/su");
            Process p = Runtime.getRuntime().exec("sh");
            dos = new DataOutputStream(p.getOutputStream());
            dis = new DataInputStream(p.getInputStream());

            dos.writeBytes(cmd + "\n");
            dos.flush();
            dos.writeBytes("exit\n");
            dos.flush();
            String line = null;

            BufferedReader br = new BufferedReader(new InputStreamReader(dis));
            while ((line = br.readLine()) != null) {
                result += line;
            }
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void restorePref() {
        int restoreLight = sp.getInt(DB_LIGHT_VALUE, AdjustColorCompute.default_light);
        int restoreContrast = sp.getInt(DB_CONTRAST_VALUE, AdjustColorCompute.default_contrast);
        hwrotation = sp.getInt(DB_HWROTATION_VALUE, 0);
//        int restoreGamma = sp.getInt(DB_GAMMA_VALUE, AdjustColorCompute.default_gamma);
        // set the seekbar progress
        mSeekbarLiangdu.setProgress(restoreLight);
        mSeekbarContrast.setProgress(restoreContrast);
//        mSeekbarGamma.setProgress(restoreGamma);
        mColorCompute.computeLUTData(restoreLight, restoreContrast);
    }
}
