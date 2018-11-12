import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class SnakeRunner extends Canvas
	{
		//Main Snake Object
		static Snake snake = new Snake();
		//Primitives, holding various game info
		static int xSize = 913;
		static int ySize = 813;
		static int score = snake.getSnakeBody().size()-1;
		static boolean gameOver=false;
		static boolean needsFruit = true;
		static boolean startGame;
		//Objects holding more game info
		static String dir = "stopped";
		static ArrayList<Body> fruits = new ArrayList<Body>();
		
		public static void main(String[] args)
			{
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
			 g.drawString(String.valueOf("SCORE: "+String.valueOf(snake.getSnakeBody().size()-1)), 765, 25);
				 
			 g.setColor(Color.CYAN);
			 for(Body b: snake.getSnakeBody())
				 {
					 g.fillRect(b.getxPos(), b.getyPos(), 24, 24);
				 }
			 g.setColor(Color.GREEN);
			 g.fillRect(snake.getHead().getxPos(), snake.getHead().getyPos(), 24, 24);
			 g.setColor(Color.RED);
			 if(fruits.size() > 0)
				 {
					 g.fillRect(fruits.get(0).getxPos(), fruits.get(0).getyPos(), 24, 24);
				 }
			 else
				 {
					 needsFruit = true;
				 }
				moveSnake();
			 
			 if(needsFruit)
	            	{
	            		createNewFruit();
	            	}
		        for(Body b: snake.getSnakeBody())
		        	{
		        		if((b.getxPos() == fruits.get(0).getxPos()) && (b.getyPos() == fruits.get(0).getyPos()))
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
			 int startX = snake.getHead().getxPos();
			 int startY = snake.getHead().getyPos();
			 
			 for(int i=1; i< snake.getSnakeBody().size(); i++)
				  {
					if(!(snake.getSnakeBody().size()<3))
						{
					if((snake.getHead().getxPos()==snake.getSnakeBody().get(i).getxPos())&&(snake.getHead().getyPos()==snake.getSnakeBody().get(i).getyPos()))
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
							   if(snake.getHead().getyPos() > 0)
									{
										snake.getHead().setyPos(snake.getHead().getyPos()-25);
									}
								else
									{
										gameOver = true;
									}
							   break;
						   case "down":
							   if(snake.getHead().getyPos() < (ySize - 25))
									{
										snake.getHead().setyPos(snake.getHead().getyPos()+25);
									}
								else
									{
										gameOver = true;
									}
							   break;
						   case "right":
							   if(snake.getHead().getxPos() < (xSize - 25))
									{
										snake.getHead().setxPos(snake.getHead().getxPos()+25);
									}
								else
									{
										gameOver = true;
									}
							   break;
						   case "left":
							   if(snake.getHead().getxPos() > 0)
									{
										snake.getHead().setxPos(snake.getHead().getxPos()-25);
									}
								else
									{
										gameOver = true;
									}
							   break;
					   }
				changeBodyPositions(snake, startX, startY, 1);
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
		 public void changeBodyPositions(Snake s, int x1, int y1, int pos)
		 {
			 if(s.getSnakeBody().size() == pos)
				 {
					 return;
				 }
			 else
				 {
					 int x2 = s.getSnakeBody().get(pos).getxPos();
					 int y2 = s.getSnakeBody().get(pos).getyPos();
					 s.getSnakeBody().get(pos).setxPos(x1);
					 s.getSnakeBody().get(pos).setyPos(y1);
					 changeBodyPositions(s, x2, y2, pos + 1);
				 }
		 }
		 public void createNewFruit()
		 {
			 int fruitX = ((int) (Math.random()*37)) * 25;
			 int fruitY = ((int) (Math.random()*33)) * 25;
			 fruits.add(new Body(fruitX,fruitY));
			 needsFruit = false;
		 }
		 public void eatFruit()
		 {
			 fruits.remove(fruits.get(0));
			 score += 1;
			 int xPos = snake.getSnakeBody().get(snake.getSnakeBody().size()-1).getxPos();
			 int yPos = snake.getSnakeBody().get(snake.getSnakeBody().size()-1).getyPos();
			 int size = ((score / 3) * 2) + 1;
			 for(int i = 0; i < size; i++)
				 {
					 switch(dir)
					 {
						 case "up":
							 snake.addToSnakeBody(-25, -25);
							 break;
						 case "down":
							 snake.addToSnakeBody(-25, -25);
							 break;
						 case "right":
							 snake.addToSnakeBody(-25, -25);
							 break;
						 case "left":
							 snake.addToSnakeBody(-25, -25);
							 break;
						 default:
							 System.out.println("bet!");
							 break;
					 }
				 }			 
		 }
	}

