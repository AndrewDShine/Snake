import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
public class SnakeRunner extends Canvas
	{

		public static void main(String[] args)
			{
				JFrame frame = new JFrame("Final Game");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    
		        SnakeRunner ex = new SnakeRunner();
		        frame.getContentPane().add(ex);
		        frame.pack();
		        frame.setResizable(false);
		        frame.setVisible(true);
		        ex.requestFocus();
			}
		 public SnakeRunner() {
		        setSize(new Dimension(960, 640));
		        setBackground(Color.white);
		        addKeyListener(new KeyAdapter() {
//		            @Override
//		            public void keyPressed(KeyEvent e) {
//		                moveSquare(e);
//		            }
		        });
		    }

	}
