import static javax.media.opengl.GL.GL_POINTS;
import static javax.media.opengl.GL2.*;
import geom.Vector3f;

import javax.media.opengl.GL2;

import cggl.World;


public class BouncingBall extends cggl.SceneObject {

	private Vector3f position;
	private float[] color = new float[] { 1, .2f, .2f };

	private Vector3f direction;
	private Vector3f velocity = new Vector3f(0, 0, 0);
	private Vector3f gravity = new Vector3f(0, -9.8f, 0);
	
	private boolean showHelp = true;
	
	private float size = .05f;
	private float platform = -.8f;
	private final double CIRCLE_PRECISION = Math.PI / 10;

	public BouncingBall(Vector3f _position, Vector3f _direction)
	{
		this.position = _position;
		this.direction = _direction;
		velocity.add(_direction);
	}

	public void update(long deltaMs, float deltaS) 
	{
		if(World.Instance.Input.isKeyPressed('h', true)) {
			showHelp = !showHelp;
		}
		
		if(World.Instance.Input.isKeyPressed('a', false)) {
			velocity.addScaled(Vector3f.LEFT, deltaS);
		}
		if(World.Instance.Input.isKeyPressed('d', false)) {
			velocity.addScaled(Vector3f.RIGHT, deltaS);
		}
		if(World.Instance.Input.isKeyPressed('s', false)) {
			velocity.addScaled(Vector3f.DOWN, deltaS);
		}
		if(World.Instance.Input.isKeyPressed(' ', true)) {
			System.out.println("BAM!");
		}
		
		// v = v0 + a Dt				(a = -9.8m/s^2)
		velocity.addScaled(gravity, deltaS);

		// x = x0 + v Dt + 1/2 a Dt^2     (a = -9.8m/s^2)
		position.addScaled(velocity, deltaS).addScaled(gravity, 0.5*(deltaS*deltaS));
		
		if(position.y - size <= platform) {
			position.y = platform + size;
			velocity.y *= -1;
		}

	}

	@Override
	protected void drawInternal(GL2 gl) {

		if(showHelp) {
			// DEBUG: White 2D Text
			gl.glColor3f(1, 1, 1);
			String hudText = String.format("p: %s d: %s v: %s", position, direction, velocity);
			World.Instance.TextWriter.writeTextImediate(gl, 0.01, 0.05, hudText);
		}	
		
		// draw platform
		gl.glBegin(GL_LINES); {
			gl.glColor3f(1, 1, 1);
			gl.glVertex3f(-10, platform, 0);
			gl.glVertex3f(+10, platform, 0);
		}
		gl.glEnd();
		
		// Object draw 
		gl.glTranslatef(position.x, position.y, position.z);
		gl.glScaled(size, size, size);
		
		// Draw the car (horizontal aligned)
		gl.glColor3fv(color, 0);
		gl.glBegin(GL_TRIANGLE_FAN);
		{
			gl.glVertex3d( 0, 0, 0);
			for (double a = 0; a <= Math.PI * 2; a += CIRCLE_PRECISION) 
			{
				gl.glVertex3d( Math.cos(a) , Math.sin(a), 0);
			}
		}
		gl.glEnd();
		
		if(showHelp) {
			gl.glLineStipple(1, (short) 0x0F);
			gl.glBegin(GL_LINES);
			{
				gl.glColor3f(1, 1, 0);
				gl.glVertex3f(0, 0, 0);
				gl.glVertex3f(velocity.x, velocity.y, velocity.z);
	
				gl.glColor3f(0, 1, 1);
				gl.glVertex3f(0, 0, 0);
				gl.glVertex3f(gravity.x, gravity.y, gravity.z);
			}
			gl.glEnd();
			
			gl.glPointSize(3);
			gl.glColor3f(1, 1, 1);
			gl.glBegin(GL_POINTS);
			{
				gl.glVertex3f(0, 0, 0);
			}
			gl.glEnd();
		}
	}

}
