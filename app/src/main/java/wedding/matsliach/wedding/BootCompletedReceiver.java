package wedding.matsliach.wedding;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/**
 * Created by User on 20/11/2016.
 */
//Initialize the background services after turning on the device
public class BootCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, final Intent intent) {
        startReciever(context);
    }

    private void startReciever(Context context) {
        Intent intent1 = new Intent(context, MyReceiver.class);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT > 18)
            manager.setExact(AlarmManager.RTC_WAKEUP, 0, pendingIntent);
        else
            manager.set(AlarmManager.RTC_WAKEUP, 0, pendingIntent);
    }

}
