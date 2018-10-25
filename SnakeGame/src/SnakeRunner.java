import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
public class SnakeRunner extends Canvas
	{
		static int x=408;
		static int y=336;
		static boolean up=false;
		static boolean down=false;
		static boolean right=true;
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
		        setSize(new Dimension(864, 768));
		        setBackground(Color.BLACK);
		        addKeyListener(new KeyAdapter() 
		        {
//		            @Override
//		            public void keyPressed(KeyEvent e) 
//		            {
//		                setDirection(e);
//		            }
		        });
		      
		        
		    }
		 public void paint(Graphics g)
		 {
			 g.setColor(Color.CYAN);
			 g.fillRect(x, y, 24, 24);
			 
			
		 }
		 public void moveSnake()
		 {
			 try
				 {
			 
				if(up)
				 {
					y+=24; 
				 }
				else if(down)
				 {
					y-=24;	
				 }
				else if(right)
				 {
					x+=24;	
				 }
				else
				 {
					x-=24;	
				 }
				repaint(); 
				Thread.sleep(1000);

				 }
			 catch(Exception e)
				 {
					System.out.println("bet"); 
				 }
				
				
		 }
		 
	}
