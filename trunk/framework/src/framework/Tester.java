/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import framework.Ms.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

/**
 *
 * @author Marco
 */
public class Tester {

    long lastFrame;
    int fps;
    long lastFPS;
    
    public Tester() {
        
    }

    public void start(){

        // init OpenGL here
        boolean success=Window.initialise(800,600);
        try {
            Display.create();
        } catch (LWJGLException ex) {
            ex.printStackTrace();
        }

        initGL(); // init OpenGL
        getDelta(); // call once before loop to initialise lastFrame
        lastFPS = getTime(); // call before loop to initialise fps timer
        while (!Display.isCloseRequested()) {
            int delta = getDelta();
            Ms.update(delta);
            updateFPS();
            System.out.print(Ms.clickedRTime()+"\n");
            String s = Kb.getChars();
            renderGL();

            if (!s.equals("")){
                System.out.print(s);
            }
            Display.update();
           Display.sync(60); // cap fps to 60fps
        }
        


        Display.destroy();
    }
    
    
    
    private void initGL() {

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GLU.gluOrtho2D(0, 800, 600, 0);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        //GL11.glEnable(GL11.GL_BLEND);
        //GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    private int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;

        return delta;
    }

    private long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    private void updateFPS() {
        if (getTime() - lastFPS > 1000) {
            Display.setTitle("FPS: " + fps);
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
    }
    
     //DRAW
    private void renderGL() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

    }
}
