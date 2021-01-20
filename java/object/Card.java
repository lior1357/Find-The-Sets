package afinal.game.lior.findthesets;

import java.io.Serializable;

/**
 * Created by user on 16/10/2017.
 */

public class Card implements Serializable {
    private String color;
    private String shape;
    private String design;
    private int amount;
    //numeric value to every aspect
    private int amountValue;
    private int colorValue;
    private int shapeValue;
    private int designValue;
    private String imgUrl;
    private boolean selected;
    private boolean onBoardWhenDeckOver;
    private String btnNum;//where is the card on board

    public Card(String color, String shape, String design, int amount, String imgUrl) {    //constructor
        this.color = color;
        this.onBoardWhenDeckOver = false;
        this.shape = shape;
        this.design = design;
        this.amount = amount;
        this.imgUrl = imgUrl;
        this.amountValue = amountValue();
        this.colorValue=colorValue();
        this.shapeValue=shapeValue();
        this.designValue=designValue();
        this.selected = false;
    }

    public String getShape() {
        return shape;
    }

    public String getColor() { return color; }

    public String getDesign() {
        return design;
    }

    public int amountValue() {
        return amount;
    }

    public int shapeValue() {
        int value = 0;
        if (this.shape.equals("diamond"))
            value++;
        if (this.shape.equals("squiggle"))
            value += 2;
        if (this.shape.equals("oval"))
            value = 3;
        return value;
    }

    public int designValue() {
        int value = 0;
        if (this.design.equals("open"))
            value = 1;
        if (this.design.equals("striped"))
            value = 2;
        if (this.design.equals("solid"))
            value = 3;
        return value;
    }

    public int colorValue()
    {
        int value = 0;
        if(this.color.equals("red"))
            value =1;
        if(this.color.equals("purple"))
            value =2;
        if(this.color.equals("green"))
            value =3;
        return value;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String toString()
    {
        return this.color + " " + this.amount + " " + this.design + " " + this.shape + " " + this.isSelected();
    }

    public void setBtnNum(String btnNum) {
        this.btnNum = btnNum;
    }

    public String getBtnNum() { return btnNum; }

    public void setOnBoardWhenDeckOver(boolean onBoardWhenDeckOver) {
        this.onBoardWhenDeckOver = onBoardWhenDeckOver;
    }

    public boolean isOnBoardWhenDeckOver() {
        return onBoardWhenDeckOver;
    }
}
