package afinal.game.lior.findthesets;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by eliav on 06/04/17.
 */
public class MusicService extends Service {

    private MediaPlayer mp;

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.firstsong);
        mp.setLooping(true); // Set looping
        mp.setVolume(0.5f,0.5f);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent!=null)
        {
            Bundle extras = intent.getExtras();
            if(extras!=null)
            {
                boolean sound = extras.getBoolean("sound");
                if(sound)
                    mp.start();
                else
                    mp.pause();
            }
            else
                mp.start();
        }
        else
            mp.start();

        return flags;
    }

    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }

    public void onStop() {

    }
    public void onPause() {
    }

    @Override
    public void onDestroy() {
        mp.stop();
        mp.release();
    }

    @Override
    public void onLowMemory() {

    }
}
