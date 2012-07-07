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
public class Input {
    
    public static class mouse{
    public static boolean state = false;
    
        
    public static int getX(){
        return Mouse.getX();
    }
    
    public static int getY(){
        return  Mouse.getY();
    }
    
    public static int[] getPosition(){
        return new int[]{Mouse.getX(),Mouse.getY()};
    }
    
    public static boolean isClicked(int button){
        if (Mouse.isButtonDown(button)&&state != true){
            state = true;
            return true;
        }else {state = false; return false;}
    }
        
    }
    
}
