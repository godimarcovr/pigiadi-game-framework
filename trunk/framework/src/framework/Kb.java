/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author matteo
 */
public class Kb {
    public static String old = "";
    public static String current = "";

    
    public static String getChars(){  
            if (Keyboard.getNumKeyboardEvents()>0){
                old ="";
            }
            Keyboard.next();
            if (Keyboard.getEventKeyState()) {    
                current = Keyboard.getKeyName(Keyboard.getEventKey());
                if (old != current && current!=null){
                    
                     old = current;  
                     
                     return (current);           
            }
            }
        return "";
    }
    public static boolean isPressed(String key){
        if (Keyboard.isKeyDown(Keyboard.getKeyIndex(key))){
            return true;
        }        
        return false;             
        }
    
    
    
}
