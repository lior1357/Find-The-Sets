package afinal.game.lior.findthesets;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 12/11/2017.
 */

public class AppPreferences {
    private SharedPreferences _sharedPrefs;
    private SharedPreferences.Editor _prefsEditor;
    private String HIGH_SCORE = "highScore";

    /**
     * the constructor of the class
     * @param context - the app content
     */
    public AppPreferences(Context context) {
        this._sharedPrefs = context.getSharedPreferences(AppPreferences.class.getSimpleName(), Activity.MODE_PRIVATE);
        this._prefsEditor = _sharedPrefs.edit();
    }

    public void putInt(int score)
    {
        _prefsEditor.putInt(HIGH_SCORE, score);
        _prefsEditor.commit();
    }

    public int getInt()
    {
        return _sharedPrefs.getInt(HIGH_SCORE, 0);
    }
}
