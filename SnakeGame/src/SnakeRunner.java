import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class SnakeRunner extends Canvas
	{
		final static String [] alphaBET= {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		//Alphabet Array
		static ArrayList<Score> highScores = new ArrayList<Score>();
		//Main Snake Object
		static ArrayList<Body> snake = new ArrayList<Body>();
		//Primitives, holding various game info
		static int xSize = 913;
		static int ySize = 813;
		static int score = snake.size()-1;
		static int fruitsEaten = 0;
		static boolean gameOver=false;
		static boolean needsFruit = true;
		static boolean startGame=false;
		static boolean enterHighScore;
		static int counter = 0;
		final static int [] alphaBETCounter = {0, 0, 0};
		//Objects holding more game info
		static String dir = "stopped";
		static Body fruit = new Body(xSize + 25, ySize + 25);
		
		public static void main(String[] args)
			{
				
				snake.add(new Body(0,0));
				JFrame frame = new JFrame("Snake");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        SnakeRunner ex = new SnakeRunner();
		        frame.getContentPane().add(ex);
		        frame.pack();
		        frame.setResizable(false);
		        frame.setVisible(true);
		        ex.requestFocus();
		        
		    
				UploadScores.readScores();
			}
		 public SnakeRunner()
			 {
				UploadScores.writeScores();
				UploadScores.readScores();
				setSize(new Dimension(xSize, ySize));
		        setBackground(Color.BLACK);
		        addKeyListener(new KeyAdapter() 
		        {
		            @Override
		            public void keyPressed(KeyEvent e) 
		            {
		                switch(e.getKeyCode())
		                {
		                	case KeyEvent.VK_DOWN:
		                		if((!dir.equals("up") || (snake.size() == 1)) && startGame&&(!gameOver))
		                			{
		                				dir = "down";
		                			}
		                		else if((gameOver)&&(enterHighScore))
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
		                		if((!dir.equals("down") || (snake.size() == 1)) && startGame && (!gameOver))
		                			{
		                				dir = "up";
		                			}
		                		else if((gameOver)&&(enterHighScore))
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
		                		if((!dir.equals("left") || (snake.size() == 1)) && startGame && (!gameOver))
		                			{
		                				dir = "right";
		                			}
		                		else if((gameOver)&&(enterHighScore))
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
		                		if((!dir.equals("right") || (snake.size() == 1)) && startGame && (!gameOver))
		                			{
		                				dir = "left";
		                			}
		                		else if((gameOver)&&(enterHighScore))
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
		                		startGame=true;
		                		if(gameOver)
		                			{
		                				gameOver = false;
				                		restartGame();
		                			}
		                		repaint();
		                		break;
		                }
		            }
		            
		        });
		        Timer timer = new Timer(100, new ActionListener(){
		        	@Override
		        	public void actionPerformed(ActionEvent e)
		        	{
		        		if(startGame)
		        			{
		        				moveSnake();
		        				repaint();
		        			}
		        	}
		        
		        });
		        timer.start();
		    }

		 public void paint(Graphics g)
		 {
			 Font f=new Font("bet", Font.PLAIN, 50);
			 Font x=new Font("bet", Font.PLAIN, 100);
			 Font z=new Font("bet", Font.PLAIN, 15);
			 Font o=new Font("bet", Font.ROMAN_BASELINE, 15);
			 try
				 {
			 if(!startGame)
				 {
					String snake= "SNAKE";
					 int textX=198;
					 for(int i=0; i<snake.length(); i++)
						 {
							 g.setColor(Color.white);
							 g.setFont(x);
							 g.drawString(snake.substring(i, i+1), textX, 125);	 
							 Thread.sleep(100); 
							 textX+=100;
						 }
					 
					String credits = "A GAME BY ANDREW AND JOSH";
					 textX=128;
					 for(int i=0; i<credits.length(); i++)
						 {
							 g.setColor(Color.white);
							 g.setFont(z);
							 g.drawString(credits.substring(i,  i+1), textX, 175);	 
							 Thread.sleep(50); 
							 textX+=25; 
						 }
					 String start = "PRESS ENTER TO START";
					 
					 textX=178;
					 
					 for(int i=0; i<start.length(); i++)
						 {
							 g.setColor(Color.white);
							 g.setFont(z);
							 g.drawString(start.substring(i,  i+1), textX, 400);	 
							 Thread.sleep(10); 
							 textX+=25; 
						 }
				 }
			 else
				 {
			 if(!gameOver)
			 {
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
			 }
			 else if(enterHighScore)
				 {
					 g.setColor(Color.white);
					 g.setFont(f);
					 g.drawString("ENTER YOUR NAME", 218, 290);
					 
					 g.setColor(Color.white);
					 g.setFont(z);
					 g.drawString(alphaBET[alphaBETCounter[0]], 300, 400);
					 
					 g.setColor(Color.white);
					 g.setFont(z);
					 g.drawString(alphaBET[alphaBETCounter[1]], 315, 400);
					 
					 g.setColor(Color.white);
					 g.setFont(z);
					 g.drawString(alphaBET[alphaBETCounter[2]], 330, 400);
				 }
			 else 
				 {
						 g.setColor(Color.white);
						 g.setFont(f);
						 g.drawString("GAME OVER", 288, 390);	
						 
						 g.setColor(Color.white);
						 g.setFont(z);
						 g.drawString("YOUR SCORE WAS: "+String.valueOf(snake.size()-1), 360, 420);
						 
						 Thread.sleep(000);
						 
						 enterHighScore=true;
						 
						 repaint();
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
					  gameOver=true;	
					 }
						}
				 }
			 
			 try
				 {
			 if(!gameOver)
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
										gameOver = true;
										dir = "stopped";
									}
							   break;
						   case "down":
							   if(snake.get(0).getyPos() < (ySize - 25))
									{
										snake.get(0).setyPos(snake.get(0).getyPos()+25);
									}
								else
									{
										gameOver = true;
										dir = "stopped";
									}
							   break;
						   case "right":
							   if(snake.get(0).getxPos() < (xSize - 25))
									{
										snake.get(0).setxPos(snake.get(0).getxPos()+25);
									}
								else
									{
										gameOver = true;
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
										gameOver = true;
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
			 int fruitX = ((int) (Math.random()*37)) * 25;
			 int fruitY = ((int) (Math.random()*33)) * 25;
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
	}

