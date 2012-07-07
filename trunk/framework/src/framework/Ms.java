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
    private static float[] lPosition = new float[]{0,0};
    private static boolean lClicked;
    private static long lTime;
    private static float[] rPosition = new float[]{0,0};
    private static boolean rClicked;
    private static long rTime;
    
        
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
        if (lTime > 0){
            return true;
        } else { return false;}
    }
    
    public static float[] whereWasClicked(){
            return lPosition;
     }
    
    public static long clickedTime(){
        return lTime;
    }
    
    public static boolean isRClicked(){
        if (Mouse.isButtonDown(1)) {
            rClicked = true;
            rPosition = getPosition();
            return true;
        } else {
            rClicked = false;
            return false;
        }
    }
    
    public static boolean wasRClicked(){
        if (rTime > 0){
            return true;
        } else { return false;}
    }
    
    public static float[] whereWasRClicked(){
            return rPosition;
     }
    
    public static long clickedRTime(){
        return rTime;
    }
      
    public static void lControl(int delta){
        
        if(lClicked){
            lTime+=1;
        }else {
            lTime = 0;
        }
        isClicked();
    }
       
        public static void rControl(int delta){
        
        if(rClicked){
            rTime+=1;
        }else {
            rTime = 0;
        }
        isRClicked();
    }
        
    public static void update(int delta){
        
        lControl(delta);
        rControl(delta);
      }
    
}
