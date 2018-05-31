package wedding.matsliach.wedding;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class NotificationReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_receiver);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        String s = "w" + getIntent().getIntExtra("key",1);
        int resourceID = getResources().getIdentifier(s, "drawable", getPackageName());
        if(resourceID!=0)
            imageView.setImageDrawable(getDrawable(resourceID));
        /*if(9==getIntent().getIntExtra("key",1)){
            TextView t=(TextView)findViewById(R.id.text);
            t.setText("העצה הכי הכי מכולן:)");
            MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.ztukk);
            mediaPlayer.start();
        }*/
    }
}
