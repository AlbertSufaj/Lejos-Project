import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class QRScanner extends Thread {
	
	
	public static String reading = "";

	
	public static Socket connection = new Socket();
	public static DataInputStream dis;
	public static DataOutputStream dos;
	private static int MAX_READ = 30;
	
	private static BufferedInputStream in = null;
	private static OutputStream out = null;
	
	public static String getReading() {	
		return reading;
	}
	public static String current = "";

	public void run() {
		byte[] buffer = new byte[MAX_READ];
	
		connectToPhone connect = new connectToPhone();
		Socket connection = connect.getConnection();
		BufferedInputStream in = connect.getInputStream();
		
		while (!Button.ESCAPE.isDown()) {
			if (connection != null) {
				try {
					if (in.available() > 0) {
						LCD.drawString("Chars read: ", 0, 4);
						LCD.drawInt(in.available(), 12, 2);
						int read = in.read(buffer, 0, MAX_READ);
						LCD.drawChar('[', 3, 3);
						for (int index= 0 ; index < read ; index++) {						
							LCD.drawChar((char)buffer[index], index + 4, 3);
							reading +=(char)buffer[index];
						}
						reading = reading.substring(4, reading.length());
						current = reading;
						reading="";
						
						LCD.drawChar(']', read + 4, 3);
						//out.write("Reply:".getBytes(), 0, 6);
						//out.write(buffer, 0, read);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			//LCD.drawString(reading, 0, 7);
		}
		
	}
}