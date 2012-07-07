/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;
import org.lwjgl.input.Mouse;
/**
 *
 * @author matteo
 */
public class Ms {
    public static float[] position ;
    
        
    public static float getX(){
        return Mouse.getX();
    }
    
    public static float getY(){
        return  Mouse.getY();
    }
    
    public static float[] getPosition(){
        position = new float[]{Mouse.getX(),Mouse.getY()};
        return position;
    }
    

     
    public static void update(){

    }
    
}
