import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;

public class connectToPhone implements Behavior {
	
	public static String reading = "";
	private static String IPaddress = "192.168.44.1";
	private static int port = 1234;
	public static Socket connection = new Socket();
	public static DataInputStream dis;
	public static DataOutputStream dos;
	private static int MAX_READ = 30;
	private static BufferedInputStream inp = null;
	private static OutputStream out = null;
	private static boolean connectedOr = false;
	private Thread QR;
	
	connectToPhone(Thread QR) {
		this.QR = QR;
	}
	connectToPhone() { }
	
	public void action() {
		LCD.clear();
		LCD.drawString("Scan it baby ;)", 0, 6);
		
		while(true) {
			SocketAddress sa = new InetSocketAddress(IPaddress, port);
			try {
				connection.connect(sa, 1500); // Timeout possible
			} catch (Exception ex) {
				connection = null;
			}
			if (connection != null) {
				try {
				
					inp = new BufferedInputStream( connection.getInputStream());
					//out = connection.getOutputStream();
						
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				LCD.drawString("Connected", 0, 0);
				connectedOr = true;
				break;
			}
			break;
		}
		if(connectedOr == false) {
			LCD.drawString("Whoops, Disconnected!", 0, 7);
		}
		else {
			LCD.drawString("Connected, yay!", 0, 7);
		}
	}
	public Socket getConnection() {
		return connection;
	}
	
	public BufferedInputStream getInputStream() {
		return inp;
	}
		
	public void suppress() {}
	
	public boolean takeControl() {
		return true;
	}
	
	
}