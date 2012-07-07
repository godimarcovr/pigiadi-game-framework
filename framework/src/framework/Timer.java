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

    public Timer(long ms){
        this.counter=ms;
        this.isCD=true;
    }

    public Timer(){
        this.counter=0;
        this.isCD=false;
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
