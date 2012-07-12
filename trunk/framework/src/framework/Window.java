/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import org.jbox2d.dynamics.World;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


/**
 *
 * @author Marco
 */
public class Window {

    public static int w, h;
    public static Tester game;
    public static Game game2;


    public static boolean initialise(int width, int heigth) {
        try {
            Display.setDisplayMode(new DisplayMode(width, heigth));
            w=width;
            h=heigth;
            return true;
        } catch (LWJGLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static World getWorld(){
        return Window.game2.world;
    }
}
