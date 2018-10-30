import java.util.ArrayList;

public class Snake
	{
//		This is the POJO for the whole snake, which will hold an ArrayList of Body objects.
		private ArrayList<Body> snakeBody = new ArrayList<Body>();
		
		public Snake()
		{
			addToSnakeBody(new Body(0, 0));
		}

		public Body getHead()
			{
				return snakeBody.get(0);
			}
		
		public ArrayList<Body> getSnakeBody()
			{
				return snakeBody;
			}

		public void setSnakeBody(ArrayList<Body> snakeBody)
			{
				this.snakeBody = snakeBody;
			}
		
		public void addToSnakeBody(Body b)
			{
				snakeBody.add(b);
			}
		
		public void changeBodyPositions()
			{
				for(int i = 0; i < snakeBody.size(); i++)
					{
						snakeBody.get(i+1).setxPos(snakeBody.get(i).getxPos());
						snakeBody.get(i+1).setyPos(snakeBody.get(i).getyPos());
					}
			}
	}
