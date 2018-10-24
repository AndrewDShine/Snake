
public class Body 
	{
//		This is the Body POJO, aka each piece of the snake.
		private int xPos; //X and Y positions of the piece.
		private int yPos;
		
		public Body(int x, int y)
		{
			xPos = x;
			yPos = y;
		}

		public int getxPos()
			{
				return xPos;
			}

		public void setxPos(int xPos)
			{
				this.xPos = xPos;
			}

		public int getyPos()
			{
				return yPos;
			}

		public void setyPos(int yPos)
			{
				this.yPos = yPos;
			}
		
		
	}
