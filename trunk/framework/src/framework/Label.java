/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

/**
 *
 * @author matteo
 */
public class Label {
    Position pos;
    Box shape;
    float w;
    float h;
    String text;
    
    public Label(Position pos, float w, float h, String text) {
        this.pos = pos;
        this.w = w;
        this.h = h;
        this.text = text;
        this.shape.x= pos.x;
        this.shape.y = pos.y;
        this.shape.w = w;
        this.shape.h = h;
    }
    
    public void setText(String text){
        this.text = text;
    }
    
    public String getText(){
        return text;
    }
    
    public boolean clicked(Position pos){
        if (shape.isHit(pos.x, pos.y)){
            return true;
        }else {return false;}
    }
    
    
    
}
