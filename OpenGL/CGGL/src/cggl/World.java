package cggl;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import com.jogamp.opengl.util.FPSAnimator;

import static javax.media.opengl.GL.*;

public abstract class World implements GLEventListener {

	private static final int FPS = 60;

	// Singleton
	public static World Instance = null;
	public World() {
		if(Instance != null) throw new UnsupportedOperationException("World is already created");
		Instance = this;
	}
	
	public Input Input = new Input();

	
	// Start
	public void start(String title) {
		GLCanvas canvas = new GLCanvas();
		canvas.addGLEventListener(this);

		JFrame frame = new JFrame(title);
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(canvas);
		frame.setVisible(true);
		
		Input.configure(canvas);

		FPSAnimator animator = new FPSAnimator(canvas, FPS);
		animator.start();

		createScene();
	}
	
	// createScene
	protected abstract void createScene();
	
	private List<SceneObject> sceneObjects = new ArrayList<>(); 
	public void add(SceneObject obj) 
	{
		sceneObjects.add(obj);
	}
	
	// init
	private void init(GL2 gl) {
		gl.glClearColor(0, 0, 0, 0);
		for (SceneObject sceneObject : sceneObjects) {
			sceneObject.initGL(gl);
		}
	}
	
	// update
	private long lastTimeMs = 0;

	private void update() 
	{
		if(lastTimeMs == 0) lastTimeMs = System.currentTimeMillis();
		long currTimeMs = System.currentTimeMillis();
		long deltaMs = currTimeMs - lastTimeMs;
		float deltaS = deltaMs / 1000f;
		lastTimeMs = currTimeMs;

		for (SceneObject sceneObject : sceneObjects) {
			sceneObject.update(deltaMs, deltaS);
		}
	}	

	// draw
	private void draw(GL2 gl) 
	{
		gl.glClear(GL_COLOR_BUFFER_BIT);
		gl.glLoadIdentity();
		
		// TODO: camera
		
		for (SceneObject sceneObject : sceneObjects) {
			sceneObject.draw(gl);
		}
		gl.glFlush();
	}
	
	
	// GLEventListener implementation
	@Override
	public void init(GLAutoDrawable drawable) 
	{
 		GL2 gl = drawable.getGL().getGL2();
		init(gl);
	}
	
	// Update

	@Override
	public void display(GLAutoDrawable drawable) 
	{
		update();
		
		GL2 gl = drawable.getGL().getGL2();
		draw(gl);
	}


	@Override
	public void dispose(GLAutoDrawable drawable) { }

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {}

	
}
