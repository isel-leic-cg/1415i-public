package cggl;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

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
	public TextWriter TextWriter = new TextWriter();
	public Camera Camera = new Camera();
	
	private GLU glu = new GLU();
	private GLUT glut = new GLUT();
	
	// Start
	public void start(String title) {
		GLCanvas canvas = new GLCanvas();
		canvas.addGLEventListener(this);

		JFrame frame = new JFrame(title);
		frame.setSize(800, 400);
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
	protected void initScene(GL2 gl) {	}
	protected void drawScene(GL2 gl) {	}
	protected void drawScene(GL2 gl, GLU glu, GLUT glut) {	}
	protected void updateScene(long currentMs, long deltaMs, float deltaS) {	}

	
	private List<SceneObject> sceneObjects = new ArrayList<>(); 
	public void add(SceneObject obj) 
	{
		sceneObjects.add(obj);
	}
	
	// init
	private void init(GL2 gl) {
		gl.glClearColor(0, 0, 0, 0);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		
		initScene(gl);
		
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

		updateScene(currTimeMs, deltaMs, deltaS);
		
		for (SceneObject sceneObject : sceneObjects) {
			sceneObject.update(currTimeMs, deltaMs, deltaS);
		}
	}	

	// draw
	private void draw(GL2 gl) 
	{
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
		Camera.setup(gl);
		
		drawScene(gl, glu, glut);
		
		for (SceneObject sceneObject : sceneObjects) {
			sceneObject.draw(gl, glu, glut);
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
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) 
	{
		GL2 gl = drawable.getGL().getGL2();
		GLU glu = new GLU();
		
		// set viewport according new w and h
		gl.glViewport(0, 0, w, h);
		
		// Update projection values (projector)
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		double aspect = w / (double)h;
		//gl.glOrtho(-aspect, aspect, -1, 1, -1, 1);
		
		//gl.glFrustum(-aspect, aspect, -1, 1, -1, 100);
		
		glu.gluPerspective(60, aspect, .1, 100);

		// Change back to model-view matrix
		gl.glMatrixMode(GL2.GL_MODELVIEW);
	}

	
}











