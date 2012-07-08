/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import org.newdawn.slick.Color;

/**
 *
 * @author matteo
 */
public class Label {

    Box shape;
    float w;
    float h;
    String text;
    int fontID;
    Color textCol, borderCol;

    public Label(Box box, String text,int font, Color tCol,Color bCol) {
        this.text = text;
        this.shape=box;
        this.fontID=font;
        this.textCol=tCol;
        this.borderCol=bCol;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean clicked(Position click) {
        if (shape.isHit(click.x, click.y)) {
            return true;
        } else {
            return false;
        }
    }


}
