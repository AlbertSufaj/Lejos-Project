import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.robotics.navigation.MovePilot;

public class ConnectionQR {
	
	private static String IPaddress = "192.168.44.1";
	private static int port = 1234;
	public static Socket connection = new Socket();
	public static DataInputStream dis;
	public static DataOutputStream dos;
	private static int MAX_READ = 30;
	private static BufferedInputStream in = null;
	private static OutputStream out = null;
	public static boolean connected = false;
	
	ConnectionQR() {}
	
	public void action() {
		
		byte[] buffer = new byte[MAX_READ];
		String reply = "";
		
		LCD.clear();
		LCD.drawString("Waiting  ", 0, 2);
		while (true) {
			SocketAddress sa = new InetSocketAddress(IPaddress, port);
			try {
				connection.connect(sa, 1500); // Timeout possible
			} catch (Exception ex) {
				connection = null;
			}
			
			if (connection != null) {
				try {
					in = new BufferedInputStream(connection.getInputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					out = connection.getOutputStream();
				} catch (IOException e) {
					e.printStackTrace();
				}
				LCD.drawString("Connected", 0, 3);
				connected = true;
				break;
			}	
			break;
		}
		if (!connected) {
			LCD.drawString("Not connected", 0, 4);
			LCD.drawString("Trying Again", 0, 5);
		} else {
			LCD.drawString("Connected", 0, 6);
		}
	}
	
	public boolean getConnected() {
		return connected;
	}
	
	public Socket getConnection() {
		return connection;
	}
	
	public BufferedInputStream getIn() {
		return in;
	}
		
	public void suppress() {}
	
	public boolean takeControl() {
		return (!connection.isConnected() | in == null);
	} 
}