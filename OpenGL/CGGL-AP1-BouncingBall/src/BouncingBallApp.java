import geom.Vector3f;

public class BouncingBallApp extends cggl.World {

	public static void main(String[] args) 
	{
		new BouncingBallApp().start("CGGL: Bouncing Ball");
	}

	@Override 
	public void createScene() 
	{
		add(new Axis());

		Vector3f position = new Vector3f(-0.5f, 0.8f, 0);
		Vector3f direction = new Vector3f(0.1f, 0, 0).clone();
		BouncingBall ball = new BouncingBall(position, direction);
		add(ball);
	}
		
}