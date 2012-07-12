/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package marcotestjbox;

import framework.Kb;
import framework.Ms;
import framework.TimerHandler;
import framework.Window;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

/**
 *
 * @author Marco
 */
public class Game {
    long lastFrame;
    int fps;
    long lastFPS;
    World world;
    int velIt=6, posIt=2;
    Entity e;

    public Game() {
    }

    public void start() {

        // init OpenGL here
        boolean success = Window.initialise(800, 600);
        Window.game2=this;
        try {
            Display.create();
        } catch (LWJGLException ex) {
            ex.printStackTrace();
        }
        menuInitialize();//Menu initialize
        initGL(); // init OpenGL
        getDelta(); // call once before loop to initialise lastFrame
        lastFPS = getTime(); // call before loop to initialise fps timer

/***********************************************************************************************************/

        this.world=new World(new Vec2(0f,-10.0f), true);
        this.e=new Entity(1.0f,1.0f);

/***********************************************************************************************************/

        while (!Display.isCloseRequested()) {
            int delta = getDelta();
            Ms.update(delta);
            TimerHandler.update(delta);
            updateFPS();

            update(delta);
            renderGL();

            Display.update();
            Display.sync(60); // cap fps to 60fps
        }



        Display.destroy();
    }

    private void initGL() {

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        float lowerX = -25.0f, upperX = 25.0f, lowerY = -25.0f, upperY = 25.0f;
        GLU.gluOrtho2D(lowerX, upperX, lowerY, upperY);
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

    public void renderGL() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        e.draw();

    }

    public void update(int delta) {
        String read = Kb.getChars();
        world.step(delta, velIt, posIt);
    }

    public void menuInitialize(){
    }
}
