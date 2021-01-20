package afinal.game.lior.findthesets;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton[][] board = new ImageButton[3][4];
    private int countSets, hintsLeft = 3;
    private TextView tvTimer, tvCheck, tvScore;
    private Card[][] cardBoard = new Card[3][4];
    private GameManager gameManager;
    private String name = "";
    private Spot[] setSpots;
    private Button btnHint, btnFinishGame;
    private boolean hintAvailable = true;
    private ArrayList<Card> cards;
    ArrayList<Set> availableSets;
    private ArrayList<Card> selected = new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //matching the buttons with IDs
        btnFinishGame = (Button) findViewById(R.id.btnFinishGame);
        tvCheck = (TextView) findViewById(R.id.tvCheck);
        tvTimer = (TextView) findViewById(R.id.tvTimer);
        tvScore = (TextView) findViewById(R.id.tvScore);
        btnHint = (Button) findViewById(R.id.btnHint);

        //setting onClickListeners to buttons

        //set score to 0
        tvScore.setText("0 sets found");

        //matching imageButtons with IDs and setting onClickListeners
        String str;
        int resID;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                str = "btn" + i + j;
                resID = getResources().getIdentifier(str, "id", getPackageName());
                board[j][i] = (ImageButton) findViewById(resID);
                board[j][i].setOnClickListener(this);
            }
        }

        //getting card deck as Serializable from MainActivity
        Intent intent = getIntent();
        if (intent != null) {
            cards = (ArrayList<Card>) intent.getSerializableExtra("cards");
            name = (String) intent.getStringExtra("playerName");
        }

        //creating game manager with the card deck
        gameManager = new GameManager(this, cards);
        gameManager.shuffleDeck();
        fillBoard();
        availableSets = gameManager.allSets();
        int size = availableSets.size();
        //check if the aren't sny sets in board
        while (size == 0) {
            gameManager.shuffleDeck();
            availableSets = gameManager.allSets();
            size = availableSets.size();
            fillBoard();
        }

        //showing how many sets available
        tvCheck.setText(size + " sets available");

        //setting 2 minute timer for game
        new CountDownTimer(120000, 1000) {
            public void onTick(long millisUntilFinished) {
                if ((millisUntilFinished / 1000) % 60 < 10) {
                    tvTimer.setText((millisUntilFinished / 1000) / 60 + ":0" + (millisUntilFinished / 1000) % 60);
                } else
                    tvTimer.setText((millisUntilFinished / 1000) / 60 + ":" + (millisUntilFinished / 1000) % 60);
            }

            public void onFinish() {
                finishGame();
            }
        }.start();

    }

    public void onClick(View v) {
        boolean found = false;
        for (int i = 0; i < 4 && !found; i++) {
            for (int j = 0; j < 3 && !found; j++) {
                int id = board[j][i].getId();
                if (v.getId() == id) {
                    found = true;
                    board[j][i].setBackgroundColor(Color.GREEN);
                    if (gameManager.select(cardBoard[j][i])) { //if set is made
                        setSpots = change(); //the set's cards' spots
                        hintAvailable = true;
                        availableSets = gameManager.allSets();
                        countSets++;
                        if(countSets == 1)
                            tvScore.setText("1 set found");
                        else
                            tvScore.setText(countSets + " sets found");
                        int size = availableSets.size();
                        //check if there aren't any sets in board
                        if (size == 0) {
                            while (availableSets.size() == 0) {
                                change(setSpots);
                                availableSets = gameManager.allSets();
                            }
                        }

                        tvCheck.setText(availableSets.size() + " sets available");
                    }
                }
            }
        }
    }

    public Spot[] change() {
        Spot[] spots = new Spot[3];
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                    if (cardBoard[j][i].isSelected()) {
                        cardBoard[j][i].setSelected(false);
                        cardBoard[j][i] = gameManager.nextCard();
                        spots[count]=new Spot(j,i);
                        count++;

                        int resID;
                        String str = cardBoard[j][i].getImgUrl();
                        resID = getResources().getIdentifier(str, "mipmap", getPackageName());
                        board[j][i].setImageResource(resID);
                        board[j][i].setBackgroundColor(Color.WHITE);
                    }
                }
            }
            return spots;
        }

        public void change(Spot[] spots) {
            int row, col;
            for (int i = 0; i < 3; i++) {
                row = spots[i].getRow();
                col = spots[i].getCol();
                cardBoard[row][col] = gameManager.nextCard();

                int resID;
                String str = cardBoard[row][col].getImgUrl();
                resID = getResources().getIdentifier(str, "mipmap", getPackageName());
                board[row][col].setImageResource(resID);
                board[row][col].setBackgroundColor(Color.WHITE);
            }
        }

    public void defaultColor(Card c)
    {
        boolean found = false;
           for(int i = 0; i < 4 && !false; i++)
           {
               for(int j = 0; j < 3 && !false; j++)
               {
                   if(cardBoard[j][i] == c)
                       board[j][i].setBackgroundColor(Color.WHITE);
               }
           }
    }

    public void defaultColor()
    {
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 3 ; j++)
            {
                if(cardBoard[j][i].isSelected()) {
                    board[j][i].setBackgroundColor(Color.WHITE);
                    cardBoard[j][i].setSelected(false);
                }
            }
        }
    }

    public void fillBoard()
    {
        int resID2;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                cardBoard[j][i] = gameManager.nextCard();
                resID2 = getResources().getIdentifier(cards.get(gameManager.getLastPos() - 1).getImgUrl(), "mipmap", getPackageName());
                board[j][i].setImageResource(resID2);
            }
        }

    }

    public Spot findCard(Card c)
    {
        Spot s=new Spot(4,4);
        boolean found = false;
        for(int i = 0; i < 4 && !found; i++)
        {
            for(int j = 0; j < 3 && !found ; j++)
            {
                if(cardBoard[j][i].toString().equals(c.toString()))
                {
                    s = new Spot(j, i);
                    found = true;
                }
            }
        }

        return s;
    }

    public void vibrate()
    {
        Vibrator vi = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        vi.vibrate(500);
    }

    public void finishGame()
    {
        Intent in = new Intent(this, FinalScoreActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("score", countSets);
        bundle.putString("playerName", name);
        in.putExtras(bundle);
        finish();
        startActivity(in);
    }

    public void showHint(View v)
    {
        if (hintAvailable) {
            // if(hintsLeft > 0) {
            Spot s = findCard(availableSets.get(0).getFirst());
            int row = s.getRow();
            int col = s.getCol();
            board[row][col].setBackgroundColor(Color.BLUE);

            s = findCard(availableSets.get(0).getSecond());
            row = s.getRow();
            col = s.getCol();
            board[row][col].setBackgroundColor(Color.BLUE);

            s = findCard(availableSets.get(0).getThird());
            row = s.getRow();
            col = s.getCol();
            board[row][col].setBackgroundColor(Color.BLUE);
            //  hintsLeft--;
            // }
            hintAvailable = false;
        }
    }

    public Card[][] getCardBoard() {
        return cardBoard;
    }

    public void endGame(View v)
    {
        finishGame();
    }
}
