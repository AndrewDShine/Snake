import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("serial")
public class SnakeRunner extends JPanel
	{
		final static String [] alphaBET= {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		//Alphabet Array
		static ArrayList<Score> highScores = new ArrayList<Score>();
		//Main Snake Object
		static ArrayList<Body> snake = new ArrayList<Body>();
		//Primitives, holding various game info
		static int xSize = 931;
		static int ySize = 828;
		static int score = snake.size()-1;
		static int fruitsEaten = 0;
		static int stage = 0;
		static boolean needsFruit = true;
		static int counter = 0;
		final static int [] alphaBETCounter = {0, 0, 0};
		//Objects holding more game info
		static String dir = "stopped";
		static Body fruit = new Body(xSize + 25, ySize + 25);
		static int tickCounter = 0;
		static String title= "SNAKE";
		static String shownTitle = "";
		static String credits = "A GAME BY ANDREW AND JOSH";
		static String shownCredits = "";
		static String start = "PRESS ENTER TO START";
		static String shownStart = "";
		
		public static void main(String[] args)
			{
				UploadScores.readScores();
				snake.add(new Body(0,0));
				JFrame frame = new JFrame("Snake");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        SnakeRunner ex = new SnakeRunner();
		        frame.add(ex);
		        frame.setSize(xSize, ySize);
		        frame.setResizable(false);
		        frame.setVisible(true);
		        ex.setFocusable(true);	
			}
		 public SnakeRunner()
			 {
				
				UploadScores.writeScores();
				UploadScores.readScores();
		        setBackground(Color.BLACK);
		        addKeyListener(new KeyAdapter() 
		        {
		            @Override
		            public void keyPressed(KeyEvent e) 
		            {
		                switch(e.getKeyCode())
		                {
		                	case KeyEvent.VK_DOWN:
		                		if((!dir.equals("up") || (snake.size() == 1)) && (stage == 1))
		                			{
		                				dir = "down";
		                			}
		                		else if(stage == 3)
		                			{
		                				if(alphaBETCounter[counter]==0)
		                					{
		                						alphaBETCounter[counter] = 25;
		                					}
		                				else
		                					{
		                						alphaBETCounter[counter]-=1;
		                					}
		                			}
		                		break;
		                	case KeyEvent.VK_UP:
		                		if((!dir.equals("down") || (snake.size() == 1)) && (stage == 1))
		                			{
		                				dir = "up";
		                			}
		                		else if(stage == 3)
		                			{
		                				if(alphaBETCounter[counter]==25)
		                					{
		                						alphaBETCounter[counter] = 0;
		                					}
		                				else
		                					{
		                						alphaBETCounter[counter]+=1;
		                					}
		                			}
		                		break;
		                	case KeyEvent.VK_RIGHT:
		                		if((!dir.equals("left") || (snake.size() == 1)) && (stage == 1))
		                			{
		                				dir = "right";
		                			}
		                		else if(stage == 3)
		                			{
		                				if(counter == 2)
		                					{
		                						counter = 0;
		                					}
		                				else
		                					{
		                						counter+=1;
		                					}
		                			}
		                		break;
		                	case KeyEvent.VK_LEFT:
		                		if((!dir.equals("right") || (snake.size() == 1)) && (stage == 1))
		                			{
		                				dir = "left";
		                			}
		                		else if(stage == 3)
		                			{
		                				if(counter == 0)
		                					{
		                						counter = 2;
		                					}
		                				else
		                					{
		                						counter-=1;
		                					}
		                			}
		                		break;
		                	case KeyEvent.VK_SPACE:
		                		dir = "stopped";
		                		break;
		                	case KeyEvent.VK_ENTER:
		                		if(stage != 1 && stage != 5)
		                			{
		                				stage += 1;
		                				if(stage==4)
		                					{
		                						highScores.add(new Score((snake.size()-1), (alphaBET[alphaBETCounter[0]]+alphaBET[alphaBETCounter[1]]+alphaBET[alphaBETCounter[2]])));
		                						Collections.sort(SnakeRunner.highScores, new ScoreSorter());
		                						Collections.reverse(SnakeRunner.highScores);
		                						UploadScores.writeScores();
		                					}
		                			}
		                		if(stage == 5)
		                			{
				                		restartGame();
				                		stage = 1;
		                			}
		                		repaint();
		                		break;
		                	case KeyEvent.VK_P:
		                		createNewFruit();
		                		break;
		                }
		            }
		            
		        });
		        Timer timer = new Timer(100, new ActionListener(){
		        	@Override
		        	public void actionPerformed(ActionEvent e)
		        	{
		        		if(shownTitle.length() < title.length())
		        			{
		        				shownTitle += title.substring(tickCounter, tickCounter + 1);
		        			}
		        		else if(shownCredits.length() < credits.length())
		        			{
		        				int tick = tickCounter - 5;
		        				shownCredits += credits.substring(tick, tick+1);
		        			}
		        		else if(shownStart.length() < start.length())
		        			{
		        				int tick = tickCounter - 30;
		        				shownStart += start.substring(tick, tick+1);
		        			}
		        		tickCounter++;
		        		if(stage == 1)
		        			{
		        				moveSnake();
		        				repaint();
		        			}
		        		else
		        			{
		        				repaint();
		        			}
		        	}
		        
		        });
		        timer.start();
		    }

		 public void paintComponent(Graphics g)
		 {
			 super.paintComponent(g);
			 Font f=new Font("Arial", Font.PLAIN, 50);
			 Font x=new Font("Arial", Font.PLAIN, 100);
			 Font z=new Font("Arial", Font.PLAIN, 15);
			 Font o=new Font("Arial", Font.PLAIN, 25);
			 Font scores = new Font("Arial", Font.PLAIN, 30);
			 Font restart = new Font("Arial", Font.PLAIN, 40);
			 try
				 {
					 switch(stage)
					 {
						 case 0:
							 int textX=278;
							 g.setColor(Color.white);
							 g.setFont(x);
							 g.drawString(shownTitle, textX, 125);	 
							 textX+=100;
							 
							 textX=158;
							 
							 g.setColor(Color.white);
							 g.setFont(z);
							 for(int i = 0; i < shownCredits.length(); i++)
								 {
									 g.drawString(shownCredits.substring(i, i+1), textX + (25 * i), 175);
								 }
								 
							 
							 textX=208;
							 g.setColor(Color.white);
							 g.setFont(z);
							 for(int i = 0; i < shownStart.length(); i++)
								 {
									 g.drawString(shownStart.substring(i, i+1), textX + (25 * i), 400);
								 }
							 break;
						 case 1:
							 Font q = new Font("bet", Font.PLAIN, 25); 
							 g.setColor(Color.white);
							 g.setFont(q);
							 g.drawString(String.valueOf("SCORE: "+String.valueOf(snake.size()-1)), 10, 25);
							 
							 if(highScores.size()>0)
								 {
							 g.setColor(Color.white);
							 g.setFont(q);
							 g.drawString("HIGH SCORE: "+String.valueOf(highScores.get(0).getScore()), 690, 25);
								 }
								 
							 g.setColor(Color.CYAN);
							 for(Body b: snake)
								 {
									 g.fillRect(b.getxPos(), b.getyPos(), 24, 24);
								 }
							 g.setColor(Color.GREEN);
							 g.fillRect(snake.get(0).getxPos(), snake.get(0).getyPos(), 24, 24);
							 g.setColor(Color.RED);
							 g.fillRect(fruit.getxPos(), fruit.getyPos(), 24, 24);
							 
							 if(needsFruit)
								 {
									 createNewFruit();
								 }
									 g.setColor(Color.CYAN);
								 	for(Body b: snake)
									 	{
										 	g.fillRect(b.getxPos(), b.getyPos(), 24, 24);
									 	}
								 	
								 	g.setColor(Color.RED);
									g.fillRect(fruit.getxPos(), fruit.getyPos(), 24, 24);
									g.setColor(Color.GREEN);
								 	g.fillRect(snake.get(0).getxPos(), snake.get(0).getyPos(), 24, 24);
								 
								 
							        for(Body b: snake)
							        	{
							        		if((b.getxPos() == fruit.getxPos()) && (b.getyPos() == fruit.getyPos()))
							        			{
							        				eatFruit();
							        			}
							        	}
							 break;
						 case 2:
							 g.setColor(Color.white);
							 g.setFont(f);
							 g.drawString("GAME OVER", 288, 390);	
							 
							 g.setColor(Color.white);
							 g.setFont(z);
							 g.drawString("YOUR SCORE WAS: "+String.valueOf(snake.size()-1), 360, 420);
							 
							 break;
						 case 3:
							 g.setColor(Color.white);
							 g.setFont(f);
							 g.drawString("ENTER YOUR NAME", 218, 290);
							 
							 g.setColor(Color.white);
							 g.setFont(o);
							 g.drawString(alphaBET[alphaBETCounter[0]], 420, 400);
							 
							 g.setColor(Color.white);
							 g.setFont(o);
							 g.drawString(alphaBET[alphaBETCounter[1]], 445, 400);
							 
							 g.setColor(Color.white);
							 g.setFont(o);
							 g.drawString(alphaBET[alphaBETCounter[2]], 470, 400);
							 
							 
							 
							 switch(counter)
							 {
								 case 0:
									 drawTriangle(g, 420, 400, true);
									 drawTriangle(g, 420, 400, false);
									 break;
								 case 1:
									 drawTriangle(g, 445, 400, true);
									 drawTriangle(g, 445, 400, false);
									 break;
								 case 2:
									 drawTriangle(g, 470, 400, true);
									 drawTriangle(g, 470, 400, false);
									 break;
							 }
							 break;
						 case 4:
//							 UploadScores.readScores();
							 int tempY = 120;
							 
							 g.setColor(Color.white);
							 g.setFont(f);
							 g.drawString("HIGH SCORES", 270, 50);
							 
							 g.drawRect(50, 60, 800, 1);
							 
							 g.setColor(Color.white);
							 g.setFont(restart);
							 g.drawString("PRESS ENTER TO RESTART", 180, 750);
							 
							 for(int i=0 ; i<10; i++)
								 {
									 g.setColor(Color.white);
									 g.setFont(scores);
									 g.drawString(highScores.get(i).getName(), 366, tempY);
									 
									 g.setColor(Color.white);
									 g.setFont(scores);
									 g.drawString(String.valueOf(highScores.get(i).getScore()), 496, tempY);
									 
									 tempY+=50;
								 }
					 }
				 }
			 catch(Exception e)
				 {
//					System.out.println("bet?"); 
				 }

			
		 }
		 public void moveSnake()
		 {
			 int startX = snake.get(0).getxPos();
			 int startY = snake.get(0).getyPos();
			 
			 for(int i=1; i< snake.size(); i++)
				  {
					if(!(snake.size()<3))
						{
					if((snake.get(0).getxPos()==snake.get(i).getxPos())&&(snake.get(0).getyPos()==snake.get(i).getyPos()))
					 {
					  stage = 2;	
					 }
						}
				 }
			 
			 try
				 {
			 if(stage == 1)
				   {
					   switch(dir)
					   {
						   case "up":
							   if(snake.get(0).getyPos() > 0)
									{
										snake.get(0).setyPos(snake.get(0).getyPos()-25);
									}
								else
									{
										stage = 2;
										dir = "stopped";
									}
							   break;
						   case "down":
							   if(snake.get(0).getyPos() < (ySize - 60))
									{
										snake.get(0).setyPos(snake.get(0).getyPos()+25);
									}
								else
									{
										stage = 2;
										dir = "stopped";
									}
							   break;
						   case "right":
							   if(snake.get(0).getxPos() < (xSize - 50))
									{
										snake.get(0).setxPos(snake.get(0).getxPos()+25);
									}
								else
									{
										stage = 2;
										dir = "stopped";
									}
							   break;
						   case "left":
							   if(snake.get(0).getxPos() > 0)
									{
										snake.get(0).setxPos(snake.get(0).getxPos()-25);
									}
								else
									{
										stage = 2;
										dir = "stopped";
									}
							   break;
					   }
				changeBodyPositions(startX, startY, 1);
//				repaint(); 
				
				 
				
				   }
			 else
			 {
//			 repaint(); 
			 }
				 }
			 catch(Exception e)
				 {
					System.out.println(e.getMessage());
					 System.out.println("bet"); 
				 }
				
				
		 }
		 public void changeBodyPositions(int x1, int y1, int pos)
		 {
			 if(snake.size() == pos)
				 {
					 return;
				 }
			 else
				 {
					 int x2 = snake.get(pos).getxPos();
					 int y2 = snake.get(pos).getyPos();
					 snake.get(pos).setxPos(x1);
					 snake.get(pos).setyPos(y1);
					 changeBodyPositions(x2, y2, pos + 1);
				 }
		 }
		 public void createNewFruit()
		 {
			 int fruitX = ((int) (Math.random()*36)) * 25;
			 int fruitY = ((int) (Math.random()*32)) * 25;
			 fruit.setxPos(fruitX);
			 fruit.setyPos(fruitY);
			 needsFruit = false;
		 }
		 public void eatFruit()
		 {
			 fruitsEaten += 1;
			 int size = ((fruitsEaten / 3) * 2) + 1;
			 for(int i = 0; i < size; i++)
				 {
					 snake.add(new Body(-25, -25));
				 }
			 needsFruit = true;
		 }
		 public void restartGame()
		 {
			 snake.clear();
			 snake.add(new Body(0,0));
			 dir = "stopped";
			 fruitsEaten = 0;
			 createNewFruit();
		 }
		 public void drawTriangle(Graphics g, int x, int y, boolean up)
		 {
			 if(up)
				 {
					 g.drawRect(x, y - 25, 15, 1);
					 g.drawRect(x + 2, y - 27, 11, 1); 
					 g.drawRect(x + 4, y - 29, 7, 1);
					 g.drawRect(x + 6, y - 31, 3, 1);
					 g.drawRect(x + 7, y - 32, 1, 1);
				 }
			 else
				 {
					 g.drawRect(x, y + 7, 15, 1);
					 g.drawRect(x + 2, y + 9, 11, 1); 
					 g.drawRect(x + 4, y + 11, 7, 1);
					 g.drawRect(x + 6, y + 13, 3, 1);
					 g.drawRect(x + 7, y + 14, 1, 1); 
				 }
		 }
	}

