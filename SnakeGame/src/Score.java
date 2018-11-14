import java.io.*;

@SuppressWarnings("serial")
public class Score implements Serializable
	{
		private String name;
		private int score;
		
		public Score(int s, String n)
		{
		 s=score;
		 n=name;
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
