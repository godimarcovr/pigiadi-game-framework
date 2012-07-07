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
    private static float[] position ;
    private static boolean lClicked;
    private static long lTime;
    
        
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
    
    public static boolean isClicked(){
        if (Mouse.isButtonDown(0)) {
            lClicked = true;
            return true;
        } else {
            lClicked = false;
            return false;
        }
    }
    
    
    
    
    public static void lControl(){
        isClicked();
        if(lClicked){
            lTime+=1;
        }else {
            lTime = 0;
        }
    }
    
     
    public static void update(){
        
        lControl();
        System.out.print(lTime+"\n");
    }
    
}
