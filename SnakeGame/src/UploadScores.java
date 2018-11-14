import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class UploadScores
	{
		public static void writeScores()
		{
			String filename = "HighScores.txt";
			ArrayList<Score> tempHighScores = new ArrayList<Score>();
			
			try
				{
					FileOutputStream file = new FileOutputStream(filename);
					ObjectOutputStream out = new ObjectOutputStream(file);
					
					tempHighScores.add(new Score(10,"JOS"));
					tempHighScores.add(new Score(9, "AND"));
					tempHighScores.add(new Score(8, "MIK"));
					tempHighScores.add(new Score(7, "WOW"));
					tempHighScores.add(new Score(6, "JAM"));
					tempHighScores.add(new Score(5, "MAX"));
					tempHighScores.add(new Score(4, "CAL"));
					tempHighScores.add(new Score(3, "ANI"));
					tempHighScores.add(new Score(2, "DUD"));
					tempHighScores.add(new Score(1, "TOM"));
					

					out.writeObject(tempHighScores);
					
					out.close();
					file.close();
				}
			
			catch(Exception e)
				{
					System.out.println("b e t");
				}
			SnakeRunner.highScores=tempHighScores;
		}
		public static void readScores()
		{
			String filename ="HighScores.txt";
			ArrayList<Score> tempHighScores = new ArrayList<Score>();
			
			try
				{
			FileInputStream file= new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);
			
			SnakeRunner.highScores=(ArrayList<Score>)in.readObject();
			
			in.close();
			file.close();
				}
			catch(Exception e)
				{
					System.out.println("b e     t");
				}
			
//			Collections.sort(SnakeRunner.highScores, new ScoreSorter());
//			Collections.reverse(SnakeRunner.highScores);
		}
	}
