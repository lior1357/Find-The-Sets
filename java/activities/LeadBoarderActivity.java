package afinal.game.lior.findthesets;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class LeadBoarderActivity extends AppCompatActivity {

    private ListView lvScores;
    private SQLiteDatabase mainDb;
    private Button btnToLead;
    private DataBaseHelper dbHelper;
    private Spinner spinner;
    private ArrayList<String> spinnerChoices;
    ArrayAdapter<String> adapter;
    ArrayList<String> rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_boarder);
        spinner = (Spinner) findViewById(R.id.spinner);
        lvScores = (ListView) findViewById(R.id.lvScores);
        dbHelper = new DataBaseHelper(getApplicationContext());
        btnToLead = (Button) findViewById(R.id.btnScoreToMain);
        mainDb = dbHelper.getWritableDatabase();
        int id= R.layout.customlistview;
        rows = dbHelper.showScores("name");
        adapter = new ArrayAdapter<>(this, id, rows);
        lvScores.setAdapter(adapter);

        spinnerChoices = new ArrayList<>();
        spinnerChoices.add("name");
        spinnerChoices.add("score");
        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                spinnerChoices);
        spinner.setAdapter(spinAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected;
                selected = spinner.getSelectedItem().toString();
                rows = dbHelper.showScores(selected);
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void update()
    {
        adapter = new ArrayAdapter<String>(this, R.layout.customlistview, rows);
        lvScores.setAdapter(adapter);
    }

}
