import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class UploadScores
	{
		public static void writeScores()
		{
			String filename = "HighScores.ser";
			ArrayList<Score> tempHighScores = new ArrayList<Score>();
			
			try
				{
					FileOutputStream file = new FileOutputStream(filename);
					ObjectOutputStream out = new ObjectOutputStream(file);
					
					tempHighScores = SnakeRunner.highScores;

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
			String filename ="HighScores.ser";
			ArrayList<Score> tempHighScores2 = new ArrayList<Score>();
			
			try
				{
			FileInputStream file= new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);
			
			tempHighScores2=(ArrayList<Score>)in.readObject();
			SnakeRunner.highScores= tempHighScores2;
			

			
			in.close();
			file.close();
				}
			catch(Exception e)
				{
					System.out.println("b e     t");
				}
			
		
			Collections.sort(SnakeRunner.highScores, new ScoreSorter());
			Collections.reverse(SnakeRunner.highScores);
		}
		public static void createDefaultScores()
		{
			SnakeRunner.highScores.add(new Score(1, "WIL"));
			SnakeRunner.highScores.add(new Score(2, "FRE"));
			SnakeRunner.highScores.add(new Score(3, "GAR"));
			SnakeRunner.highScores.add(new Score(4, "JAN"));
			SnakeRunner.highScores.add(new Score(5, "BAR"));
			SnakeRunner.highScores.add(new Score(6, "JON"));
			SnakeRunner.highScores.add(new Score(7, "JEF"));
			SnakeRunner.highScores.add(new Score(8, "CAL"));
			SnakeRunner.highScores.add(new Score(9, "AND"));
			SnakeRunner.highScores.add(new Score(10,"JOS"));
		}
	}
