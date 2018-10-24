import java.util.ArrayList;

public class Snake extends Body
	{
//		This is the POJO for the whole snake, which will hold an ArrayList of Body objects.
		private ArrayList<Body> snakeBody = new ArrayList<Body>();
		
		public Snake(int x, int y)
		{
			super(x,y);
		}

		public ArrayList<Body> getSnakeBody()
			{
				return snakeBody;
			}

		public void setSnakeBody(ArrayList<Body> snakeBody)
			{
				this.snakeBody = snakeBody;
			}
		
		
	}
