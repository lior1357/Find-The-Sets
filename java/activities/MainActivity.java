package afinal.game.lior.findthesets;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    private RelativeLayout rel;
    private ConstraintLayout background;
    private Button photobtn;
    private Button game;
    private Button instructions;
    //private ListView lv;
    private ArrayList<Card> al;
    private DataBaseHelper dbHelper;
    private SQLiteDatabase mainDb;
    private Context context;
    public static MusicService musicService;
    private boolean sound = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        background = (ConstraintLayout) findViewById(R.id.background);
        photobtn = (Button) findViewById(R.id.bgButton);
        photobtn.setOnLongClickListener(this);
        dbHelper = new DataBaseHelper(getApplicationContext());
        game = (Button) findViewById(R.id.btngame);
        instructions = (Button) findViewById(R.id.instructions);
        instructions.setOnClickListener(this);
        game.setOnClickListener(this);
        //lv = (ListView)findViewById(R.id.lv);
        mainDb = dbHelper.getWritableDatabase();

        registerReceiver(new BatteryCheck(), new
                IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        musicService = new MusicService();
        startService(new Intent(this, MusicService.class));

        al = new ArrayList<Card>();
        al.add(new Card("red", "squiggle", "solid", 1, "c1111"));
        al.add(new Card("red", "squiggle", "solid", 2, "c1112"));
        al.add(new Card("red", "squiggle", "solid", 3, "c1113"));
        al.add(new Card("red", "squiggle", "striped", 1, "c1121"));
        al.add(new Card("red", "squiggle", "striped", 2, "c1122"));
        al.add(new Card("red", "squiggle", "striped", 3, "c1123"));
        al.add(new Card("red", "squiggle", "open", 1, "c1131"));
        al.add(new Card("red", "squiggle", "open", 2, "c1132"));
        al.add(new Card("red", "squiggle", "open", 3, "c1133"));
        al.add(new Card("green", "squiggle", "solid", 1, "c1211"));
        al.add(new Card("green", "squiggle", "solid", 2, "c1212"));
        al.add(new Card("green", "squiggle", "solid", 3, "c1213"));
        al.add(new Card("green", "squiggle", "striped", 1, "c1221"));
        al.add(new Card("green", "squiggle", "striped", 2, "c1222"));
        al.add(new Card("green", "squiggle", "striped", 3, "c1223"));
        al.add(new Card("green", "squiggle", "open", 1, "c1231"));
        al.add(new Card("green", "squiggle", "open", 2, "c1232"));
        al.add(new Card("green", "squiggle", "open", 3, "c1233"));
        al.add(new Card("purple", "squiggle", "solid", 1, "c1311"));
        al.add(new Card("purple", "squiggle", "solid", 2, "c1312"));
        al.add(new Card("purple", "squiggle", "solid", 3, "c1313"));
        al.add(new Card("purple", "squiggle", "striped", 1, "c1321"));
        al.add(new Card("purple", "squiggle", "striped", 2, "c1322"));
        al.add(new Card("purple", "squiggle", "striped", 3, "c1323"));
        al.add(new Card("purple", "squiggle", "open", 1, "c1331"));
        al.add(new Card("purple", "squiggle", "open", 2, "c1332"));
        al.add(new Card("purple", "squiggle", "open", 3, "c1333"));
        al.add(new Card("red", "diamond", "solid", 1, "c2111"));
        al.add(new Card("red", "diamond", "solid", 2, "c2112"));
        al.add(new Card("red", "diamond", "solid", 3, "c2113"));
        al.add(new Card("red", "diamond", "striped", 1, "c2121"));
        al.add(new Card("red", "diamond", "striped", 2, "c2122"));
        al.add(new Card("red", "diamond", "striped", 3, "c2123"));
        al.add(new Card("red", "diamond", "open", 1, "c2131"));
        al.add(new Card("red", "diamond", "open", 2, "c2132"));
        al.add(new Card("red", "diamond", "open", 3, "c2133"));
        al.add(new Card("green", "diamond", "solid", 1, "c2211"));
        al.add(new Card("green", "diamond", "solid", 2, "c2212"));
        al.add(new Card("green", "diamond", "solid", 3, "c2213"));
        al.add(new Card("green", "diamond", "striped", 1, "c2221"));
        al.add(new Card("green", "diamond", "striped", 2, "c2222"));
        al.add(new Card("green", "diamond", "striped", 3, "c2223"));
        al.add(new Card("green", "diamond", "open", 1, "c2231"));
        al.add(new Card("green", "diamond", "open", 2, "c2232"));
        al.add(new Card("green", "diamond", "open", 3, "c2233"));
        al.add(new Card("purple", "diamond", "solid", 1, "c2311"));
        al.add(new Card("purple", "diamond", "solid", 2, "c2312"));
        al.add(new Card("purple", "diamond", "solid", 3, "c2313"));
        al.add(new Card("purple", "diamond", "striped", 1, "c2321"));
        al.add(new Card("purple", "diamond", "striped", 2, "c2322"));
        al.add(new Card("purple", "diamond", "striped", 3, "c2323"));
        al.add(new Card("purple", "diamond", "open", 1, "c2331"));
        al.add(new Card("purple", "diamond", "open", 2, "c2332"));
        al.add(new Card("purple", "diamond", "open", 3, "c2333"));

        al.add(new Card("red", "oval", "solid", 1, "c3111"));
        al.add(new Card("red", "oval", "solid", 2, "c3112"));
        al.add(new Card("red", "oval", "solid", 3, "c3113"));
        al.add(new Card("red", "oval", "striped", 1, "c3121"));
        al.add(new Card("red", "oval", "striped", 2, "c3122"));
        al.add(new Card("red", "oval", "striped", 3, "c3123"));
        al.add(new Card("red", "oval", "open", 1, "c3131"));
        al.add(new Card("red", "oval", "open", 2, "c3132"));
        al.add(new Card("red", "oval", "open", 3, "c3133"));

        al.add(new Card("green", "oval", "solid", 1, "c3211"));
        al.add(new Card("green", "oval", "solid", 2, "c3212"));
        al.add(new Card("green", "oval", "solid", 3, "c3213"));
        al.add(new Card("green", "oval", "striped", 1, "c3221"));
        al.add(new Card("green", "oval", "striped", 2, "c3222"));
        al.add(new Card("green", "oval", "striped", 3, "c3223"));
        al.add(new Card("green", "oval", "open", 1, "c3231"));
        al.add(new Card("green", "oval", "open", 2, "c3232"));
        al.add(new Card("green", "oval", "open", 3, "c3233"));

        al.add(new Card("purple", "oval", "solid", 1, "c3311"));
        al.add(new Card("purple", "oval", "solid", 2, "c3312"));
        al.add(new Card("purple", "oval", "solid", 3, "c3313"));
        al.add(new Card("purple", "oval", "striped", 1, "c3321"));
        al.add(new Card("purple", "oval", "striped", 2, "c3322"));
        al.add(new Card("purple", "oval", "striped", 3, "c3323"));
        al.add(new Card("purple", "oval", "open", 1, "c3331"));
        al.add(new Card("purple", "oval", "open", 2, "c3332"));
        al.add(new Card("purple", "oval", "open", 3, "c3333"));


        int myPremmision = 1;
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //Should the request be displayed
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        myPremmision);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent call = null;
        if (item.getItemId() == R.id.call) {
            call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + ""));
            startActivity(call);
        }

        else
        {
            if(item.getItemId() == R.id.mute)
            {
                if(sound) {
                    sound = false;
                    item.setTitle("UnMute");
                }
                else
                {
                    sound = true;
                    item.setTitle("Mute");
                }

                Intent i = new Intent(this, MusicService.class);
                i.putExtra("sound", sound);
                startService(i);
            }
        }
        return true;
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btngame) {
            Intent intent = new Intent(this, EnterNameActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("cards", al);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        if (v.getId() == R.id.instructions) {
            Intent intent = new Intent(this, InstructionsActivity.class);
            startActivity(intent);
        }
    }

    public void getPhoto(View v) {
        //brings user to gallery to select image for background of screen
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0);
    }

    public boolean onLongClick(View v) {   //for deleting background image and returning to default background
        // TODO Auto-generated method stub
        AlertDialog.Builder info = new AlertDialog.Builder(this);
        info.setTitle("Remove Background Image?");
        info.setMessage("Are you sure you wish to revert to the default background?");
        info.setCancelable(true);
        info.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) { //erases current background image location

                try {
                    FileOutputStream fos = openFileOutput("imageFile", Context.MODE_PRIVATE);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    BufferedWriter writer = new BufferedWriter(osw);
                    writer.close();
                    osw.close();
                    fos.close();
                    background.setBackgroundColor(Color.WHITE);
                } catch (Exception e) {

// TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        info.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        info.show();


        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            try {
                Uri selectedImage = data.getData();
                grantUriPermission("com.example.remmberthesong", selectedImage,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION);
                InputStream imageStream;
                imageStream = getContentResolver().openInputStream(selectedImage);
                Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                Drawable image = new BitmapDrawable(getResources(), bitmap);
                background.setBackground(image);
//saves location of background image
                FileOutputStream fos = openFileOutput("imageFile", Context.MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter writer = new BufferedWriter(osw);
                String imageUri = selectedImage.toString();
                writer.append(imageUri);
                writer.close();
                osw.close();
                fos.close();
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

    }}

    @Override
    public void onBackPressed() {
        stopService(new Intent(this, MusicService.class));
        finishAffinity();
        finish();
        moveTaskToBack(true);
    }
}
