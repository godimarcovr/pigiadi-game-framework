/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package framework;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Marco
 */
public class TimerHandler {

    public static HashMap<Integer,Timer> tim= new HashMap<Integer,Timer>();
    private static int c=0;

    public static int createTimer(){
        tim.put(c, new Timer());
        c++;
        return c-1;
    }

    public static void update(int delta){
        Set<Entry<Integer, Timer>> keyset=tim.entrySet();
        for(Entry ent:keyset){
            ((Timer)ent.getValue()).pass(delta);
        }
    }

    public static boolean isTimeUp(int id){
        if(tim.get(id).isTimeUp()){
            return true;
        }
        else{
            return false;
        }
    }

    public static void removeTimer(int id){
        tim.remove(id);
    }
}
