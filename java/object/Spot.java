package afinal.game.lior.findthesets;

/**
 * Created by user on 04/11/2017.
 */

public class Spot {
    private int row;
    private int col;

    public Spot(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {

        return row;
    }
}
