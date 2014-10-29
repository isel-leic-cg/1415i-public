import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import com.jogamp.opengl.util.FPSAnimator;

import static javax.media.opengl.GL.*;
import static javax.media.opengl.GL2.*;

public class RotatingSquareFPSAnimatorApp implements GLEventListener {

	public static void main(String[] args) 
	{
		RotatingSquareFPSAnimatorApp app = new RotatingSquareFPSAnimatorApp();
		app.start();
	}

	private float angle = 30;
	private long lastTimeMs = 0;
	
	public void start() 
	{
		GLCanvas canvas = new GLCanvas();
		canvas.addGLEventListener(this);
		
		FPSAnimator animator = new FPSAnimator(canvas, 60);
		animator.start();
		
		JFrame frame = new JFrame("Hello OpenGL");
		frame.setSize(400, 400);
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(canvas);
		frame.setVisible(true);
	}

	@Override
	public void init(GLAutoDrawable drawable) 
	{
		GL gl = drawable.getGL();
		gl.glClearColor(0, 0, 0, 0);
	}
	
	@Override
	public void display(GLAutoDrawable drawable) 
	{
		if(lastTimeMs == 0) lastTimeMs = System.currentTimeMillis();
		long currTimeMs = System.currentTimeMillis();
		long deltaMs = currTimeMs - lastTimeMs;
		lastTimeMs = currTimeMs;
		
		System.out.println(deltaMs);
		
		// update
		angle += 5;
		
		// draw
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL_COLOR_BUFFER_BIT);
		
		gl.glLoadIdentity();
		gl.glRotatef(angle , 0, 0, 1);
		
		gl.glColor3f(1f, 1f, 0);
		gl.glBegin(GL_POLYGON);
			gl.glVertex3f(0.25f, 0.25f, 0);
			gl.glVertex3f(0.75f, 0.25f, 0);
			gl.glVertex3f(0.75f, 0.75f, 0);
			gl.glVertex3f(0.25f, 0.75f, 0);
		gl.glEnd();
		
		gl.glFlush();
	}


	@Override
	public void dispose(GLAutoDrawable drawable) { }

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) 
	{
	}
	
}
