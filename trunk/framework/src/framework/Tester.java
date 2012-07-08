/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import framework.Ms.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 *
 * @author Marco
 */
public class Tester {

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

        while (!Display.isCloseRequested()) {
            Ms.update(0);
          
            String s = Kb.getChars();


            if (!s.equals("")){
                System.out.print(s);

            }


            Display.update();
        }

        Display.destroy();
    }
}
