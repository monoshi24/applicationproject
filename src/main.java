import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class main{
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Toolkit toolkit = Toolkit.getDefaultToolkit();

        // Get the screen size as a Dimension object
        Dimension screenSize = toolkit.getScreenSize();

        // Access the width and height
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("Screen Width: " + screenWidth);
        System.out.println("Screen Height: " + screenHeight);
		frame.setSize(screenWidth, screenHeight);
//		frame.setResizable(false);
		ImageIcon logo = new ImageIcon("images/group9_logo.jpg");
		frame.setIconImage(logo.getImage());
		frame.setTitle("Fuel Administration");
		JLabel label =new JLabel();
		
		frame.setVisible(true);
	}
}