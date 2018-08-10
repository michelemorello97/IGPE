package graphics;
import java.awt.Toolkit;

import javax.swing.*;

public class MyFrame extends  JFrame{

	MyPanel panel;
	JFrame frame;
	
	MyFrame(){
		super();
		Toolkit tk = Toolkit.getDefaultToolkit();
		this.setSize(tk.getScreenSize().width/2, tk.getScreenSize().height);
		panel= new MyPanel(tk.getScreenSize().width/2, tk.getScreenSize().height);
		this.setContentPane(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		JFrame f=new MyFrame();
		f.setVisible(true);
		
	}

}
