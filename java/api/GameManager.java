package afinal.game.lior.findthesets;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Vibrator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 16/10/2017.
 */

public class GameManager {
    private GameActivity activity;
    private Card[][] board;
    private ArrayList<Card> cards;
    private ArrayList<Card> selected;
    private boolean deckOverOnce = false;
    private int lastPos; //where in deck
    private int countSelected = 0;
    private int sets = 0;
    private String message = "";


    public String getMessage() {
        return message;
    }

    public void setLastPos(int lastPos) {
        this.lastPos = lastPos;
    }

    public int getLastPos() {

        return lastPos;
    }

    public GameManager(GameActivity activity, ArrayList<Card> cards) {
        this.activity = activity;
        this.cards = cards;
        this.board = new Card[3][4];
        this.lastPos = 0;
        this.selected = new ArrayList<Card>();
    }


    public boolean isSet(Card c1, Card c2, Card c3) {
        //gets three carsd selected by the player
        //return if the three cards are a set or not

        return (((c1.amountValue() + c2.amountValue() + c3.amountValue()) % 3 == 0) &&
                ((c1.colorValue() + c2.colorValue() + c3.colorValue()) % 3 == 0) &&
                ((c1.shapeValue() + c2.shapeValue() + c3.shapeValue()) % 3 == 0) &&
                ((c1.designValue() + c2.designValue() + c3.designValue()) % 3 == 0));
    }

    public void shuffleDeck() {
        //shuffles Deck of cards
        Random rnd = new Random();
        for (int j = cards.size() - 1; j > 0; j--) {
            int index = rnd.nextInt(j + 1);
            Card a = cards.get(index);
            cards.set(index, cards.get(j));
            cards.set(j, a);
        }
    }

    public void rndBoard() {
        //fills board with random cards
        shuffleDeck();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                board[j][i] = cards.get(lastPos);
                if (this.lastPos < 81)
                    this.lastPos++;
            }
        }
    }

    public int getCountSelected() {
        return countSelected;
    }

    public boolean select(Card c) {
        //gets card and its indexes on the board
        //selects the card and returns if a set was made
        boolean isSet = false;

        if (countSelected < 3) {
            c.setSelected(!c.isSelected());
            if (c.isSelected()) {
                countSelected++;
                message = countSelected + "" + c.toString();
                selected.add(c);
            } else {
                message = countSelected + " ";
                countSelected--;
                activity.defaultColor(c);
                selected.remove(c);
            }
        }

        if (selected.size() == 3) {
            Card c1 = selected.get(0);
            Card c2 = selected.get(1);
            Card c3 = selected.get(2);

            if (isSet(c1, c2, c3)) {
                sets++;
                isSet = true;
            }

            else
            {
                activity.vibrate();
                activity.defaultColor();
            }
            countSelected = 0;
            selected.clear();
        }
        return isSet;
    }

    public ArrayList<Card> getSelected() {
        //returns arraylist of the cards that the set contains
        return selected;
    }

    public Card nextCard()
    {
        Card c = this.cards.get(this.lastPos);
        if(lastPos < 81 && lastPos > 69)
        {
            c.setOnBoardWhenDeckOver(true);
        }

        this.lastPos++;
        if(lastPos > 80)
        {
            deckOverOnce=true;
            deckisOver();
        }

        if(deckOverOnce && thereAreFirstCards()) {
            while (c.isOnBoardWhenDeckOver()) {
                c.setOnBoardWhenDeckOver(false);
                this.lastPos++;
                c = this.cards.get(lastPos);
            }
            if (!thereAreFirstCards())
                deckOverOnce = false;
        }
        return c;
    }

    private boolean thereAreFirstCards()
    {
        board = activity.getCardBoard();
        boolean ok = true;
        for(int i = 0; i < 4 && ok; i++)
        {
            for(int j = 0; j < 3 && ok ; j++)
            {
                if(board[j][i].isOnBoardWhenDeckOver())
                {
                    ok = false;
                }
            }
        }

        return ok;
    }

    private void deckisOver()
    {
        //shuffleWhenDeckIsOver();
        lastPos = 0;
        Card c= this.cards.get(this.lastPos);
    }


    public ArrayList<Set> allSets()
    {
        board = activity.getCardBoard();
        ArrayList<Card> cards = new ArrayList<>();
        Card c1, c2, c3;
        int a1, a2, a3; // pointers
        ArrayList<Set> sets = new ArrayList<>();
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 3;j++)
            {
                cards.add(board[j][i]);
            }
        }

        for(a1 = 0; a1 < 12; a1++)
        {
            c1 = cards.get(a1);
            for(a2 = a1+1; a2 < 12; a2++)
            {
                    c2 = cards.get(a2);
                    for (a3 = a2 + 1; a3 < 12; a3++) {
                        c3 = cards.get(a3);
                        if (isSet(cards.get(a1), cards.get(a2), cards.get(a3))) {
                            sets.add(new Set(cards.get(a1), cards.get(a2), cards.get(a3)));
                        }
                    }
            }
        }
        return sets;
    }

}
