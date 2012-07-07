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
    private static float[] lPosition ;
    private static boolean lClicked;
    private static long lTime;
    
        
    public static float getX(){
        return Mouse.getX();
    }
    
    public static float getY(){
        return  Mouse.getY();
    }
    
    public static float[] getPosition(){
        
        return new float[]{Mouse.getX(),Mouse.getY()};
    }
    
    public static boolean isClicked(){
        if (Mouse.isButtonDown(0)) {
            lClicked = true;
            lPosition = getPosition();
            return true;
        } else {
            lClicked = false;
            return false;
        }
    }
    
    public static boolean wasClicked(){
        if (lTime > 0 && lClicked){
            return true;
        } else { return false;}
    }
    
    public static float[] whereWasClicked(){
            return lPosition;
     }
    
    public static long clickedTime(){
        return lTime;
    }
    
    public static void lControl(){
        
        if(lClicked){
            lTime+=1;
        }else {
            lTime = 0;
        }
        isClicked();
    }
    
     
    public static void update(){
        
        lControl();
        System.out.print(lTime+"\n");
    }
    
}
