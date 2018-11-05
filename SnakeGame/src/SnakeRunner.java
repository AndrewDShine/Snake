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
		static boolean up=false;
		static boolean down=false;
		static boolean right=true;
		static boolean left=false;
		static boolean gameOver=false;

		static ArrayList<Body> fruits = new ArrayList<Body>();
		static boolean needsFruit = true;
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
		                		falseAll();
		                		down = true;
		                		break;
		                	case KeyEvent.VK_UP:
		                		falseAll();
		                		up = true;
		                		break;
		                	case KeyEvent.VK_RIGHT:
		                		falseAll();
		                		right = true;
		                		break;
		                	case KeyEvent.VK_LEFT:
		                		falseAll();
		                		left = true;
		                		break;
		                	case KeyEvent.VK_SPACE:
		                		falseAll();
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
		 public void falseAll()
		 {
			 up = false;
			 down = false;
			 left = false;
			 right = false;
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
					 String [] snake = {"S", "N", "A", "K", "E"};
					 int textX=198;
					 for(String s: snake)
						 {
							 g.setColor(Color.white);
							 g.setFont(x);
							 g.drawString(s, textX, 125);	 
							 Thread.sleep(100); 
							 textX+=100;
						 }
					 
					 String [] intro = {"A", " ", "G", "A", "M", "E"," ","B","Y"," ","A","N","D","R","E","W"," ","A","N","D", " ","J","O","S","H"};
					 textX=128;
					 for(String s: intro)
						 {
							 g.setColor(Color.white);
							 g.setFont(z);
							 g.drawString(s, textX, 175);	 
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
			 int preX = snake.getHead().getxPos();
			 int preY = snake.getHead().getyPos();
			 try
				 {
			 if(!gameOver)
				   {
				if(up)
				 {
					if(snake.getHead().getyPos() > 0)
						{
							snake.getHead().setyPos(snake.getHead().getyPos()-24);
						}
					else
						{
							gameOver = true;
						}
				 }
				else if(down)
				 {
					 if(snake.getHead().getyPos() < (ySize - 24))
							{
								snake.getHead().setyPos(snake.getHead().getyPos()+24);
							}
						else
							{
								gameOver = true;
							}	
				 }
				else if(right)
				 {
					 if(snake.getHead().getxPos() < (xSize - 24))
							{
								snake.getHead().setxPos(snake.getHead().getxPos()+24);
							}
						else
							{
								gameOver = true;
							}	
				 }
				else if(left)
				 {
					 if(snake.getHead().getxPos() > 0)
							{
								snake.getHead().setxPos(snake.getHead().getxPos()-24);
							}
						else
							{
								gameOver = true;
							}	
				 }
				changeBodyPositions(snake, 1);
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
		 public void changeBodyPositions(Snake s, int pos)
		 {
			 if(s.getSnakeBody().size() == pos)
				 {
					 return;
				 }
			 else
				 {
					 int x1 = s.getSnakeBody().get(pos - 1).getxPos();
					 int y1 = s.getSnakeBody().get(pos - 1).getyPos();
					 
					 changeBodyPositions(s, pos + 1);
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
			 int xPos = snake.getSnakeBody().get(snake.getSnakeBody().size()-1).getxPos();
			 int yPos = snake.getSnakeBody().get(snake.getSnakeBody().size()-1).getyPos();
			 if(up)
				 {
					 snake.addToSnakeBody(xPos, yPos + 24);
				 }
			 else if(right)
				 {
					 snake.addToSnakeBody(xPos - 24, yPos);
				 }
			 else if(down)
				 {
					 snake.addToSnakeBody(xPos, yPos - 24);
				 }
			 else if(left)
				 {
					 snake.addToSnakeBody(xPos + 24, yPos);
				 }
			 else
				 {
					 System.out.println("bet!");
				 }
		 }
	}

