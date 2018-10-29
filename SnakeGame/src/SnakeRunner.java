import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
public class SnakeRunner extends Canvas
	{
		static int x=0;
		static int y=0;
		static boolean up=false;
		static boolean down=true;
		static boolean right=false;
		static boolean left=false;
		static boolean gameOver=false;
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
		        setSize(new Dimension(876, 780));
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
		                }
		            }
		        });
		        
		      
		        
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
			 g.setColor(Color.CYAN);
			 g.fillRect(x, y, 24, 24);
			 moveSnake();
			
		 }
		 public void moveSnake()
		 {
			 try
				 {
			 
				if(up)
				 {
					y-=24; 
				 }
				else if(down)
				 {
					y+=24;	
				 }
				else if(right)
				 {
					x+=24;	
				 }
				else if(left)
				 {
					x-=24;	
				 }
				repaint(); 
				Thread.sleep(100);

				 }
			 catch(Exception e)
				 {
					System.out.println("bet"); 
				 }
				
				
		 }
		 
	}
