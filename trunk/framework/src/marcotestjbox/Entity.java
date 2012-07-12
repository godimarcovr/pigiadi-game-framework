/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package marcotestjbox;

import framework.Window;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

/**
 *
 * @author Marco
 */
public class Entity {

    public PolygonShape s;
    public Fixture fix;
    public float dx, dy;

    public Entity(float dx, float dy) {
        /*this.s = new PolygonShape();
        this.s.setAsBox(5, 25f);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type=BodyType.DYNAMIC;
        bodyDef.position.set(0.0f, 0.0f);

        FixtureDef fixdef=new FixtureDef();
        fixdef.shape=this.s;
        fixdef.density=1.0f;
        fixdef.friction=0.3f;

        this.fix=Window.game2.world.createBody(bodyDef).createFixture(fixdef);*/
        FixtureDef fd = new FixtureDef();
        PolygonShape sd = new PolygonShape();
        sd.setAsBox(0.125f, 2f);
        fd.shape = sd;
        fd.density = 25.0f;

        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        float friction = .5f;
        int numPerRow = 25;

        fd.friction = friction;
          bd.position = new Vec2(0,0);
          bd.angle = 0f;
          Body myBody = Window.game2.world.createBody(bd);
          myBody.createFixture(fd);

        this.dx = dx;
        this.dy = dy;
    }

    public void draw() {

        Color.white.bind();
        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0, 0);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        {
            for (Vec2 object : s.getVertices()) {
                GL11.glVertex2f(object.x, object.y);
            }
            GL11.glEnd();
            GL11.glPopMatrix();
        }

    }
}
