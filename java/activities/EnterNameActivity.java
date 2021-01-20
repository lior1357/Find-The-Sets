package afinal.game.lior.findthesets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterNameActivity extends AppCompatActivity {
    private EditText edName;
    private Button btnToGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);

        btnToGame = (Button) findViewById(R.id.btnNameToGame);
        edName = (EditText) findViewById(R.id.edName);
    }

    public void enterName(View v) {
        String name = edName.getText().toString();
        Intent get = getIntent();
        if (get != null) {
            Bundle bundle = get.getExtras();
            bundle.putString("playerName", name);
            Intent i = new Intent(this, GameActivity.class);
            i.putExtras(bundle);
            startActivity(i);
        }
    }
}
