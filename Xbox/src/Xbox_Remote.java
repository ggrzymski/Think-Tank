import java.io.*;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Xbox_Remote 
{
	protected static int xbox_port = 5050;
	
	protected String xbox_ping = "dd00000a000000000000000400000002";
	
	protected String xbox_power  = "dd02001300000010";
	
	protected static String home_ip="";
	
	protected static String live_id ="";
	
	public static void main(String[] args) 
	{
		// Get IP-address and Live ID
		
		getIP();		
		getLiveID();
		
		// Connect to xbox with Socket
		
		connectXbox(home_ip, xbox_port);
	}
	
	private static void getIP()
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Choose one of the following options:");
		System.out.println("1. Manually enter your home ip address:");
		System.out.println("2: Automatically find your device's ip address");
		
		int option = 0;
		
		try
		{
			 String choice = br.readLine();
			 
			 option = Integer.parseInt(choice);
	
			while(option!=1 && option !=2)
			{
				System.out.println("Only options 1 and 2 are valid, try again.");
				option = br.read();
			}
		}
			
		catch(IOException i)
		{
				
		}
		
		if(option==1)
		{
			System.out.println("Enter your Xbox One's external ip address:");
			
			try
			{
				home_ip = br.readLine();
				
				br.close();
			}
			
			catch(IOException i)
			{
				
			}
		}
		else if(option==2)
		{
			try
			{
				URL get_ip = new URL("http://checkip.amazonaws.com");
				
				BufferedReader in = new BufferedReader(new InputStreamReader(get_ip.openStream()));

				home_ip = in.readLine();
				
				br.close();
				in.close();
			}
			catch(Exception e)
			{
				
			}
		}
	}
	
	private static void getLiveID()
	{
		System.out.println("Enter your Xbox One Live ID from the console dashboard:");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try
		{
			live_id = br.readLine();
			
			br.close();
		}
		catch(IOException e)
		{
			
		}
	}
	
	private static void connectXbox(String ip, int port)
	{
		try
		{
			InetAddress address = InetAddress.getByName(ip);
			
			Socket sc = new Socket(address,port);
		
			sc.close();
		}
		catch(IOException e)
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
