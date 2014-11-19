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
	
	@Override
	protected void updateScene(long currentMs, long deltaMs, float deltaS) {
		super.updateScene(currentMs, deltaMs, deltaS);
		
		// Camera 2 - Look from x
		if(Input.isKeyPressed('1', false)) 
		{
			Camera.lookAt(
					new Vector3f(5, 0, 0),	
					new Vector3f(0, 0f, 0),		
					new Vector3f(0, 1, 0)
			);
		}

		// Camera 2 - Look from middle of xx and zz
		if(Input.isKeyPressed('2', false)) 
		{
			Camera.lookAt(
					new Vector3f(1, 0.5f, 1.41f),	
					new Vector3f(0, 0f, 0),		
					new Vector3f(0, 1, 0)      
			);
		}		

		// Camera 2 - Look from top
		if(Input.isKeyPressed('3', false)) 
		{
			Camera.lookAt(
					new Vector3f(0, 5,  0),	
					new Vector3f(0, 0,  1),		
					new Vector3f(0, 0, -1)  	// up vector ("parte de cima da camera")    
			);
		}		
		
	}


}
