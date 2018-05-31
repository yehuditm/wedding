package wedding.matsliach.wedding;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
public class MainActivity extends ActionBarActivity {
    public static String THE_DATE = "2018-02-04";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startReciever();

        finish();
    }

    private void startReciever() {
        Intent intent1 = new Intent(this, MyReceiver.class);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT > 18)
            manager.setExact(AlarmManager.RTC_WAKEUP, 0, pendingIntent);
        else
            manager.set(AlarmManager.RTC_WAKEUP,0, pendingIntent);
    }






}