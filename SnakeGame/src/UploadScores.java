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
	}
