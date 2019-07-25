package com.zsc7454.timertutorial

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.zsc7454.timertutorial.util.NotificationUtil
import com.zsc7454.timertutorial.util.PrefUtil

class TimerNotificationActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            AppConstants.ACTION_STOP->{
                MainActivity.removeAlarm(context)
                PrefUtil.setTimerState(MainActivity.TimerState.Stopped,context)
                NotificationUtil.hideTimerNotification(context)

            }
            AppConstants.ACTION_PAUSE->{
                var secondsRemaining = PrefUtil.getSecondsRemaining(context)
                val alarmSetTime = PrefUtil.getAlarmSetTime(context)
                val nowSeconds = MainActivity.nowSeconds

                secondsRemaining -= nowSeconds - alarmSetTime

                PrefUtil.setSecondsRemaining(secondsRemaining, context)

                MainActivity.removeAlarm(context)

                PrefUtil.setTimerState(MainActivity.TimerState.Paused,context)
                NotificationUtil.showTimerPause(context)


            }
            AppConstants.ACTION_RESUME->{
                val secondsRemaining = PrefUtil.getSecondsRemaining(context)
                val wakeupTime = MainActivity.setAlarm(context, MainActivity.nowSeconds, secondsRemaining)

                PrefUtil.setTimerState(MainActivity.TimerState.Running, context)
                NotificationUtil.showTimerRunning(context,wakeupTime)
            }
            AppConstants.ACTION_START->{
                val minutesRemaining = PrefUtil.getTimerLength(context)
                val secondsRemaining = minutesRemaining * 60L
                val wakeupTime = MainActivity.setAlarm(context, MainActivity.nowSeconds, secondsRemaining)
                PrefUtil.setTimerState(MainActivity.TimerState.Running, context)
                PrefUtil.setSecondsRemaining(secondsRemaining,context)
                NotificationUtil.showTimerRunning(context,wakeupTime)
            }

        }
    }
}
