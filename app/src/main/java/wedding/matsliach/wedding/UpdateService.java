package wedding.matsliach.wedding;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
   /*     try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd");
// Please here set your event date//YYYY-MM-DD
            Date futureDate = dateFormat.parse("2017-12-25");
            Date currentDate = new Date();
            RemoteViews view = new RemoteViews(getPackageName(), R.layout.widget_updating);
            if (!currentDate.after(futureDate)) {
                long diff = futureDate.getTime()
                        - currentDate.getTime();
                long days = diff / (24 * 60 * 60 * 1000);
                diff -= days * (24 * 60 * 60 * 1000);
                long hours = diff / (60 * 60 * 1000);
                diff -= hours * (60 * 60 * 1000);
                long minutes = diff / (60 * 1000);
                diff -= minutes * (60 * 1000);
                long seconds = diff / 1000;


                view.setTextViewText(R.id.txtTimerDay, String.format("%02d", days));
                view.setTextViewText(R.id.txtTimerHour, String.format("%02d", hours));
                view.setTextViewText(R.id.txtTimerMinute, String.format("%02d", minutes));
                view.setTextViewText(R.id.txtTimerSecond, String.format("%02d", seconds));
                ComponentName theWidget = new ComponentName(this, Countdown.class);
                AppWidgetManager manager = AppWidgetManager.getInstance(this);
                manager.updateAppWidget(theWidget, view);
            } else {
                view.setViewVisibility(R.id.tvhappyevent, View.VISIBLE);
                view.setTextViewText(R.id.tvhappyevent, "The event started!");
                textViewGone();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

*/
        return super.onStartCommand(intent, flags, startId);
    }

}