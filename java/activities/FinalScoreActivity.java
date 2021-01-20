package afinal.game.lior.findthesets;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FinalScoreActivity extends AppCompatActivity {
    private int score;
    private String name;
    private SQLiteDatabase mainDb;
    private TextView tvHighScore;
    private DataBaseHelper dbHelper;
    private AppPreferences appPrefs;
    private Button btnShare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);
        dbHelper = new DataBaseHelper(getApplicationContext());
        tvHighScore =(TextView)findViewById(R.id.tvHighScore);
        btnShare = (Button)findViewById(R.id.btnShare);
        appPrefs = new AppPreferences(getApplicationContext());   //accessing shared preferences
        Intent get = getIntent();
        if(get!= null)
        {
            name = get.getStringExtra("playerName");        //getting player name as parameter
            score = get.getIntExtra("score", 0); //getting score as parameter
        }

        tvHighScore.setText("score: " + score);
        int currentHigh = appPrefs.getInt();
        if(score > currentHigh)
        {
            currentHigh = score;
            appPrefs.putInt(currentHigh);
            Toast.makeText(this, "new high score!", Toast.LENGTH_LONG).show();
        }

        mainDb = dbHelper.getWritableDatabase();
        dbHelper.addData(name, score);   //add score to database
    }

    public void toMain(View v)
    {
        //when button clicked starts main activity
        Intent intent = new Intent(this, LeadBoarderActivity.class);
        startActivity(intent);
    }

    public void share(View v)
    {
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setData(Uri.parse("sms:"));
        sendIntent.putExtra("sms_body", "I found " + score +  " sets in 'Find The Sets'! Think you can beat me?");
        startActivity(sendIntent);
    }


}
