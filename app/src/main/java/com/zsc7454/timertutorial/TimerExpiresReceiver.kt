package com.zsc7454.timertutorial

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.zsc7454.timertutorial.util.NotificationUtil
import com.zsc7454.timertutorial.util.PrefUtil

class TimerExpiresReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
NotificationUtil.showTimerExpires(context)

        PrefUtil.setTimerState(MainActivity.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)
        
    }
}
