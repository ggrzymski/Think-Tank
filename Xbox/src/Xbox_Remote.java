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
	
	protected static String xbox_power  = "dd02001300000010";
	
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
		
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
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
			// convert xbox's hex code into bytes for proper transmission
			
			byte [] xbox_power_bytes = hexTobytes(xbox_power);
			byte [] live_id_bytes = hexTobytes(live_id);
			byte [] zero_pad = hexTobytes("00");
			InetAddress address = InetAddress.getByName(ip);
			
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
			outputStream.write(xbox_power_bytes);
			outputStream.write(live_id_bytes);
			outputStream.write(zero_pad);
			
			String test = xbox_power+live_id+"00";
			byte [] result = test.getBytes();
			
			for(int i=0; i<5; i++)
			{
				DatagramPacket packet = new DatagramPacket(result, result.length, address, port);
				DatagramSocket socket = new DatagramSocket();
				socket.send(packet);
				socket.close();
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
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
	
	private static byte[] hexTobytes(String s) 
	{
	    int len = s.length();
	    
	    byte[] data = new byte[len / 2];
	    
	    for (int i = 0; i < len; i += 2) 
	    {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)+ Character.digit(s.charAt(i+1), 16));
	    }
	    
	    return data;
	}

}
