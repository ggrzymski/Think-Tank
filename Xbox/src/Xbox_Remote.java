import java.io.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Xbox_Remote 
{
	protected int xbox_port = 5050;
	
	protected String xbox_ping = "dd00000a000000000000000400000002";
	
	protected String xbox_power  = "dd02001300000010";
	
	protected static String home_ip="";
	
	public static void main(String[] args) 
	{
		// Start off with command-line prompts
		
		getInput();
		
		
	}
	
	private static void getInput()
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter your Home IP Address:");
		
		try
		{
			home_ip = br.readLine();
			
			
		}
		catch(IOException i)
		{
			
		}
	}

	private static void buildGui()
	{
		JFrame frame = new JFrame("FrameDemo");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		
		JTextField ip_address = new JTextField("Home IP Address:");
		JTextArea testing = new JTextArea("Testing");
		testing.setFont(testing.getFont().deriveFont(12f));
		
		panel.add(testing);
		panel.add(ip_address);
		
		frame.add(panel);
		frame.pack();

		
		frame.setVisible(true);
	}

}
