import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class SnakeRunner extends Canvas
	{
		static Snake snake = new Snake();
		static int xSize = 876;
		static int ySize = 780;
		static String dir = "right";
		static boolean gameOver=false;

		static ArrayList<Body> fruits = new ArrayList<Body>();
		static boolean needsFruit = true;
		static int score = 0;
		static boolean startGame;

		
		public static void main(String[] args)
			{
//				System.out.println("starting main");
				JFrame frame = new JFrame("Snake");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        SnakeRunner ex = new SnakeRunner();
		        frame.getContentPane().add(ex);
		        frame.pack();
		        frame.setResizable(false);
		        frame.setVisible(true);
		        ex.requestFocus();
//		        while(true)
//		        	{
//		        		ex.moveSnake();
//		        		ex.repaint();
//		        	}
//		        System.out.println("ending main");
			}
		 public SnakeRunner()
			 {
//		        System.out.println("starting runner");
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
		                		dir = null;
		                		break;
		                	case KeyEvent.VK_ENTER:
		                		startGame=true;
		                		repaint();
		                		break;
		                }
		            }
		            
		        });
		        
		     
//		       System.out.println("ending runner"); 
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
							 Thread.sleep(100); 
							 textX+=25; 
						 }
					 String start = "PRESS ENTER TO START";
					 
					 textX=178;
					 
					 for(int i=0; i<start.length(); i++)
						 {
							 g.setColor(Color.white);
							 g.setFont(z);
							 g.drawString(start.substring(i,  i+1), textX, 400);	 
							 Thread.sleep(100); 
							 textX+=25; 
						 }
				 }
			 else
				 {
//			 System.out.println("painting");
			 if(!gameOver)
			 {
			 g.setColor(Color.CYAN);
			 for(Body b: snake.getSnakeBody())
				 {
//					 g.fillRect(snake.getHead().getxPos(), snake.getHead().getyPos(), 24, 24);
					 g.fillRect(b.getxPos(), b.getyPos(), 24, 24);
				 }
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
//		        		System.out.println(b.getxPos() + " " + b.getyPos());
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
					System.out.println("bet"); 
				 }

			
		 }
		 public void moveSnake()
		 {
			 int startX = snake.getHead().getxPos();
			 int startY = snake.getHead().getyPos();
			 try
				 {
			 if(!gameOver)
				   {
					   switch(dir)
					   {
						   case "up":
							   if(snake.getHead().getyPos() > 0)
									{
										snake.getHead().setyPos(snake.getHead().getyPos()-24);
									}
								else
									{
										gameOver = true;
									}
							   break;
						   case "down":
							   if(snake.getHead().getyPos() < (ySize - 24))
									{
										snake.getHead().setyPos(snake.getHead().getyPos()+24);
									}
								else
									{
										gameOver = true;
									}
							   break;
						   case "right":
							   if(snake.getHead().getxPos() < (xSize - 24))
									{
										snake.getHead().setxPos(snake.getHead().getxPos()+24);
									}
								else
									{
										gameOver = true;
									}
							   break;
						   case "left":
							   if(snake.getHead().getxPos() > 0)
									{
										snake.getHead().setxPos(snake.getHead().getxPos()-24);
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
			 int fruitX = ((int) (Math.random()*37)) * 24;
			 int fruitY = ((int) (Math.random()*33)) * 24;
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
							 snake.addToSnakeBody(xPos, yPos + 24);
							 break;
						 case "down":
							 snake.addToSnakeBody(xPos, yPos - 24);
							 break;
						 case "right":
							 snake.addToSnakeBody(xPos - 24, yPos);
							 break;
						 case "left":
							 snake.addToSnakeBody(xPos + 24, yPos);
							 break;
						 default:
							 System.out.println("bet!");
							 break;
					 }
				 }
			 System.out.println(snake.getSnakeBody().size());
			 
		 }
	}

