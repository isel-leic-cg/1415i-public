import geom.Vector3f;


public class RotatingEyesApp extends cggl.World {

	public static void main(String[] args) 
	{
		new RotatingEyesApp().start("CGGL: Rotating Eyes");
	}

	@Override 
	public void createScene() 
	{
		// Create world
		float[] eyesColor = new float[] { 1f, 1f, 0f }; 
		add(new RotatingSquare(-.4f, .50f, .5f, eyesColor, 0, 'q'));
		add(new RotatingSquare(+.4f, .50f, .5f, eyesColor, -180, 'e'));

		float[] mouthColor = new float[] { 1f, 0f, 0f }; 
		add(new RotatingSquare(-.3f, -.5f, .2f, mouthColor, -90, 'z'));
		add(new RotatingSquare(  0f, -.5f, .2f, mouthColor,  90, 'x'));
		add(new RotatingSquare(+.3f, -.5f, .2f, mouthColor, -90, 'c'));
		
		add(new Axis());
		
		Camera.lookAt(
				new Vector3f(1, 0.5f, 1.41f),	
				new Vector3f(0, 0f, 0),		
				new Vector3f(0, 1, 0)      
		);

	}


}
