import java.util.Comparator;

public class ScoreSorter implements Comparator<Score>
	{
		public int compare(Score s1, Score s2)
			{
				if(s1.getScore()>s2.getScore())
					{
						return 1;
					}
				else
					{
						return -1;
					}
			}
	}
