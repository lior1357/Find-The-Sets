package afinal.game.lior.findthesets;

/**
 * Created by user on 23/10/2017.
 */

public class Set {
    private Card[] cards;
    private String spots;
    public Set(Card c1, Card c2, Card c3)
    {
        this.cards = new Card[3];
        this.cards[0] = c1;
        this.cards[1] = c2;
        this.cards[2] = c3;
    }

    public Card getFirst()
    {
        return this.cards[0];
    }
    public Card getSecond()
    {
        return this.cards[1];
    }
    public Card getThird()
    {
        return this.cards[2];
    }

}
