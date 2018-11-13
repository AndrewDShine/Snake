import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class SnakeRunner extends Canvas
	{
		//Main Snake Object
		static ArrayList<Body> snake = new ArrayList<Body>();
		//Primitives, holding various game info
		static int xSize = 913;
		static int ySize = 813;
		static int score = snake.size()-1;
		static int fruitsEaten = 0;
		static boolean gameOver=false;
		static boolean needsFruit = true;
		static boolean startGame;
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
			}
		 public SnakeRunner()
			 {
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
		                		dir = "down";
		                		break;
		                	case KeyEvent.VK_UP:
		                		dir = "up";
		                		break;
		                	case KeyEvent.VK_RIGHT:
		                		dir = "right";
		                		break;
		                	case KeyEvent.VK_LEFT:
		                		dir = "left";
		                		break;
		                	case KeyEvent.VK_SPACE:
		                		dir = "stopped";
		                		break;
		                	case KeyEvent.VK_ENTER:
		                		startGame=true;
		                		repaint();
		                		break;
		                }
		            }
		            
		        });
		    }

		 public void paint(Graphics g)
		 {
			 Font f=new Font("bet", Font.PLAIN, 50);
			 Font x=new Font("bet", Font.PLAIN, 100);
			 Font z=new Font("bet", Font.PLAIN, 15);
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
				 	g.setColor(Color.GREEN);
				 	g.fillRect(snake.get(0).getxPos(), snake.get(0).getyPos(), 24, 24);
				 	g.setColor(Color.RED);
					g.fillRect(fruit.getxPos(), fruit.getyPos(), 24, 24);
					moveSnake();
				 
				 
			        for(Body b: snake)
			        	{
			        		if((b.getxPos() == fruit.getxPos()) && (b.getyPos() == fruit.getyPos()))
			        			{
			        				eatFruit();
			        			}
			        	}
			 }
			 else 
				 {
						 g.setColor(Color.white);
						 g.setFont(f);
						 g.drawString("GAME OVER", 288, 390);	 
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
									}
							   break;
					   }
				changeBodyPositions(startX, startY, 1);
				repaint(); 
				Thread.sleep(100);
				
				 
				
				   }
			 else
			 {
			 repaint(); 
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
	}

