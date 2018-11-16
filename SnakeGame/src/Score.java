import java.io.*;

public class Score implements Serializable
	{
		private static final long serialVersionUID =1L;
		
		private String name;
		private int score;
		
		public Score(int s, String n)
		{
		 score=s;
		 name=n;
		}

		public String getName()
			{
				return name;
			}

		public void setName(String name)
			{
				this.name = name;
			}

		public int getScore()
			{
				return score;
			}

		public void setScore(int score)
			{
				this.score = score;
			}
	}
