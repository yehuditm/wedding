package wedding.matsliach.wedding;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.WallpaperManager;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pending = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long timeNext = System.currentTimeMillis() + 60 * 1000;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date futureDate = dateFormat.parse(MainActivity.THE_DATE);
            futureDate.setHours(19);
            futureDate.setMinutes(15);
            Date currentDate = new Date();
            RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.widget_updating);
            if (!currentDate.after(futureDate)) {
                AppWidgetManager manager1 = AppWidgetManager.getInstance(context);
                if (Build.VERSION.SDK_INT > 18)
                    manager.setExact(AlarmManager.RTC_WAKEUP, timeNext, pending);
                else
                    manager.set(AlarmManager.RTC_WAKEUP, timeNext, pending);
                long diff = futureDate.getTime()
                        - currentDate.getTime();
                long days = diff / (24 * 60 * 60 * 1000);
                diff -= days * (24 * 60 * 60 * 1000);
                long hours = diff / (60 * 60 * 1000);
                diff -= hours * (60 * 60 * 1000);
                long minutes = diff / (60 * 1000);
                diff -= minutes * (60 * 1000);

                view.setTextViewText(R.id.txtTimer, String.format("%02d %s %02d %s%02d %s", days, "ימים", hours, "שעות ו", minutes, "דקות"));
                ComponentName theWidget = new ComponentName(context, Countdown.class);
                manager1.updateAppWidget(theWidget, view);

                if (14 > hours && (0 == minutes || 1 == minutes)) {
                    WallpaperManager myWallpaperManager
                            = WallpaperManager.getInstance(context);
                    try {
                        String s = "e" + days;
                        int resourceID = context.getResources().getIdentifier(s, "drawable", context.getPackageName());
                        if (resourceID != 0)
                            myWallpaperManager.setResource(resourceID);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
               if (10 == hours && 0 == minutes) {
                    notif(context, (int) days);
               }
            } else {
                view.setViewVisibility(R.id.txtTimer, View.VISIBLE);
                view.setTextViewText(R.id.txtTimer, "החתונה התחילה:)");
                textViewGone(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void notif(Context context, int number) {
        Intent intent2 = new Intent(context, NotificationReceiverActivity.class);
//        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent2, 0);
        PendingIntent prevPending = PendingIntent.getActivity(context, 1,
                new Intent(context, NotificationReceiverActivity.class).putExtra("key", number),
                PendingIntent.FLAG_UPDATE_CURRENT);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(context)
                .setContentTitle("טיפ חדש")
                .setSmallIcon(R.drawable.wedding)
                .setContentText("לחצי כאן כדי לראות:)")
                .setContentIntent(prevPending).build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;
        noti.defaults = Notification.DEFAULT_ALL;

        String s = "w" + number;
        int resourceID = context.getResources().getIdentifier(s, "drawable", context.getPackageName());
        if (resourceID != 0)
            notificationManager.notify(0, noti);


    }

    public void textViewGone(Context context) {
        RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.widget_updating);
        view.setViewVisibility(R.id.txtTimer, View.GONE);
    }
}
