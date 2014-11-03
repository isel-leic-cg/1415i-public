import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import shapes.RotatingSquare;

import com.jogamp.opengl.util.FPSAnimator;

import static javax.media.opengl.GL2.*;

public class MultipleRotatingSquaresApp implements GLEventListener {

	public static void main(String[] args) 
	{
		MultipleRotatingSquaresApp app = new MultipleRotatingSquaresApp();
		app.start();
	}

	private long lastTimeMs = 0;
	
	private List<RotatingSquare> shapes = new ArrayList<>(); 
	
	public void start() 
	{
		GLCanvas canvas = new GLCanvas();
		canvas.addGLEventListener(this);
		
		// Create world
		float[] eyesColor = new float[] { 1f, 1f, 0f }; 
		shapes.add(new RotatingSquare(-.4f, .50f, .5f, eyesColor, +180));
		shapes.add(new RotatingSquare(+.4f, .50f, .5f, eyesColor, -180));

		float[] mouthColor = new float[] { 1f, 0f, 0f }; 
		shapes.add(new RotatingSquare(-.3f, -.5f, .2f, mouthColor, -90));
		shapes.add(new RotatingSquare(  0f, -.5f, .2f, mouthColor,  90));
		shapes.add(new RotatingSquare(+.3f, -.5f, .2f, mouthColor, -90));
		
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
		float deltaS = deltaMs / 1000f;
		lastTimeMs = currTimeMs;
		
		// update
		for (RotatingSquare shape : shapes) {
			shape.update(deltaMs, deltaS);
		}
		
		// draw
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL_COLOR_BUFFER_BIT);
		
		gl.glPushMatrix();
		gl.glTranslated(.5, .5, 0);
		for (RotatingSquare shape : shapes) {
			shape.display(gl);
		}
		gl.glPopMatrix();
		
		gl.glFlush();
	}


	@Override
	public void dispose(GLAutoDrawable drawable) { }

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
	      GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
	      GLU glu = new GLU();
	    		  
	      if (height == 0) height = 1;   // prevent divide by zero
	      float aspect = (float)width / height;
	 
	      // Set the view port (display area) to cover the entire window
	      gl.glViewport(0, 0, width, height);
	 
	      // Setup perspective projection, with aspect ratio matches viewport
	      gl.glMatrixMode(GL_PROJECTION);  // choose projection matrix
	      gl.glLoadIdentity();             // reset projection matrix
	      glu.gluPerspective(45.0, aspect, -0.1, 100.0); // fovy, aspect, zNear, zFar
	 
	      // Enable the model-view transform
	      gl.glMatrixMode(GL_MODELVIEW);
	      gl.glLoadIdentity(); // reset
	}
	
}















