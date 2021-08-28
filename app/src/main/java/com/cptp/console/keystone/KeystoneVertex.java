package com.cptp.console.keystone;

//import android.os.SystemProperties;
import com.cptp.console.keystone.SystemProperties;
import android.util.Log;

public class KeystoneVertex {

    private static final String PROP_KEYSTONE_TOP_LEFT = "persist.sys.keystone.lt";
    private static final String PROP_KEYSTONE_TOP_RIGHT = "persist.sys.keystone.rt";
    private static final String PROP_KEYSTONE_BOTTOM_LEFT = "persist.sys.keystone.lb";
    private static final String PROP_KEYSTONE_BOTTOM_RIGHT = "persist.sys.keystone.rb";
    private static final String PROP_KEYSTONE_UPDATE = "persist.sys.keystone.update";
    private static final String PROP_KEYSTONE_ANGLE_X = "persist.sys.keystone.angle.x";
    private static final String PROP_KEYSTONE_ANGLE_Y = "persist.sys.keystone.angle.y";

    public Vertex vTopLeft;
    public Vertex vTopRight;
    public Vertex vBottomLeft;
    public Vertex vBottomRight;

    public void getAllKeystoneVertex() {
            String sTopLeft = SystemProperties.get(PROP_KEYSTONE_TOP_LEFT, "0,0");
            String sTopRight = SystemProperties.get(PROP_KEYSTONE_TOP_RIGHT, "0,0");
            String sBottomLeft = SystemProperties.get(PROP_KEYSTONE_BOTTOM_LEFT, "0,0");
            String sBottomRight = SystemProperties.get(PROP_KEYSTONE_BOTTOM_RIGHT, "0,0");


			Log.d("get-keystone", sTopLeft + " " + sTopRight + " " + sBottomLeft + " " + sBottomRight);
				
            vTopLeft = new Vertex(sTopLeft);
            vTopRight = new Vertex(sTopRight);
            vBottomLeft = new Vertex(sBottomLeft);
            vBottomRight = new Vertex(sBottomRight);
    }

    public void updateAllKeystoneVertex() {
        SystemProperties.set(PROP_KEYSTONE_TOP_LEFT, vTopLeft.toString());
        SystemProperties.set(PROP_KEYSTONE_TOP_RIGHT, vTopRight.toString());
        SystemProperties.set(PROP_KEYSTONE_BOTTOM_LEFT, vBottomLeft.toString());
        SystemProperties.set(PROP_KEYSTONE_BOTTOM_RIGHT, vBottomRight.toString());
        SystemProperties.set(PROP_KEYSTONE_UPDATE, "1");
        Log.d("set-keystone", "rk-debug setprop");
    }
}


