package com.cptp.console;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class KeystoneReceiver extends BroadcastReceiver {
    public KeystoneReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.d("SmartEchoReceiver - onReceive");
        if(intent == null) {
            return;
        }
        String action = intent.getAction();
        if(Intent.ACTION_BOOT_COMPLETED.equals(action)
                || "com.rockchip.keystone.START".equals(action)) {
            LogUtil.d("==================== SmartEchoService Start ======================");
            Intent i = new Intent(context, KeystoneService.class);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setAction(KeystoneService.KEYSTONE_ACTION_START);
            context.startService(i);
        }
    }
}
