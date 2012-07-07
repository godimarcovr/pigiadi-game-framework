/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package framework;

/**
 *
 * @author Marco
 */
public class Timer {
    private long counter;
    private boolean isCD;

    public Timer(long ms, boolean CD){
        this.counter=ms;
        this.isCD=CD;
    }

    public void pass(long d){
        if(!isCD){
            counter+=d;
        }
        else{
            counter-=d;
        }
    }

    public boolean isTimeUp(){
        if(this.isCD && counter<=0){
            return true;
        }
        else{
            return false;
        }
    }
}
