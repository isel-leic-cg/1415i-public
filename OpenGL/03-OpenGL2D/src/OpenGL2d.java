import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import static javax.media.opengl.GL.*;
import static javax.media.opengl.GL2.*;

public class OpenGL2d implements GLEventListener {

	public static void main(String[] args) 
	{
		OpenGL2d app = new OpenGL2d();
		app.start();
	}
	
	public void start() 
	{
		GLCanvas canvas = new GLCanvas();
		canvas.addGLEventListener(this);
		
		JFrame frame = new JFrame("Hello OpenGL");
		frame.setSize(400, 400);
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(canvas);
		frame.setVisible(true);
	}

	@Override
	public void init(GLAutoDrawable drawable) {
	}

	@Override
	public void display(GLAutoDrawable drawable) 
	{	
		float[] colors = {  1, 1, 1 // white 
						  , 1, 0, 0 // red
						  , 1, 1, 0 // yellow
		};		
		int WHITE = 0, RED = 3, YELLOW = 6;
		
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL_COLOR_BUFFER_BIT);
		
		gl.glColor3fv(colors, WHITE); 
		gl.glRectd(-.9, .9, -.5, .5);
		
		gl.glPointSize(10);
		gl.glBegin(GL_POINTS);
			gl.glColor3fv(colors, WHITE);
			gl.glVertex3d(-0.8, 0.1, 0);
			gl.glVertex3d(-0.8, 0.4, 0);
			gl.glVertex3d(-0.6, 0.3, 0);
		gl.glEnd();

		gl.glEnable(GL_LINE_STIPPLE);
		gl.glLineStipple(1, (short) 0x00C0);
		gl.glLineWidth(2);
		gl.glPointSize(5);
		gl.glBegin(GL_LINES);
			gl.glColor3fv(colors, WHITE);
			gl.glVertex3d(-0.8, -0.1, 0);
			gl.glVertex3d(-0.8, -0.4, 0);

			gl.glColor3fv(colors, YELLOW);
			gl.glVertex3d(-0.6, -0.3, 0);
			gl.glVertex3d(-0.3, -0.6, 0);
		gl.glEnd();
		
		// ... draw triangles et all
		
		gl.glBegin(GL_POLYGON);
			gl.glColor3fv(colors, RED);
			gl.glVertex3d(0.25, 0.25, 0);
			gl.glVertex3d(0.75, 0.25, 0);
			gl.glVertex3d(0.75, 0.4, 0);
			
			gl.glColor3fv(colors, YELLOW);
			gl.glVertex3d(0.5, .9, 0);

			gl.glColor3fv(colors, RED);
			gl.glVertex3d(0.25, 0.4, 0);
		gl.glEnd();

		gl.glColor3fv(colors, WHITE);
		
		gl.glFlush();
	}


	@Override public void dispose(GLAutoDrawable drawable) { }
	@Override public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) { }
	
}
