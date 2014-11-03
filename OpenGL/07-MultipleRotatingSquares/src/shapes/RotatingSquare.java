package shapes;

import static javax.media.opengl.GL2.*;
import javax.media.opengl.GL2;

/**
 * Quadrado que roda (angle) em torno de um ponto C
 * a uma velocidade ROTATING_SPEED_PER_SECOND.
 * 
 * 
 * 	NOTA: Para rodar em torno de um ponto
 *  
 *        P' = T(Cx, Cy) * R(angle) * T(-Cx, -Cy) * P
 */
public class RotatingSquare {

	private float angle = 0;
	private final float cx;
	private final float cy;
	private final float rotatingSpeedPerSecond;
	private final float sz;
	
	private final float[] color;
	
	public RotatingSquare() 
	{
		this(0.5f, 0.5f, .5f, new float[] { 1, 1, 0 }, 180);
	}
	
	public RotatingSquare(float cx, float cy, float sz, 
						  float[] color,
						  float rotatingSpeedPerSecond) 
	{
		this.cx = cx;
		this.cy = cy;
		this.sz = sz;
		this.color = color;
		this.rotatingSpeedPerSecond = rotatingSpeedPerSecond;
	}
	
	public void update(long deltaMs, float deltaS) 
	{
		angle += rotatingSpeedPerSecond * deltaS;
	}

	public void display(GL2 gl) 
	{
		// TIP: Try to comment the following line ;-)
		gl.glLoadIdentity();
		
		gl.glTranslatef(cx, cy, 0);
		gl.glRotatef(angle, 0, 0, 1);
		
		gl.glColor3fv(color, 0);
		gl.glBegin(GL_POLYGON);
			gl.glVertex3f(-sz/2, -sz/2, 0);	// Lower Left
			gl.glVertex3f(+sz/2, -sz/2, 0);	// Lower Right
			gl.glVertex3f(+sz/2, +sz/2, 0);	// Upper Right
			gl.glVertex3f(-sz/2, +sz/2, 0);	// Upper left
		gl.glEnd();
		
		gl.glPointSize(6);
		gl.glColor3f(0, 0, 0);
		gl.glBegin(GL_POINTS);
		gl.glVertex3f(0,  0, 0);
		gl.glEnd();
	}

}
