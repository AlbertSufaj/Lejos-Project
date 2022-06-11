import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.robotics.navigation.MovePilot;

public class SpinModif {
	
	MovePilot pilot;
	SpinModif(MovePilot p){
		pilot = p;
		}
	
	
	public static String QRCode() {
		List<String> y = new ArrayList<String>();
		y.add("RED");
		y.add("GREEN");
		y.add("YELLOW");
		y.add("BLUE");
		Random rand = new Random();
        String colorN = y.get(rand.nextInt(y.size()));
        return colorN;
	}
	
	public static void main(String[] args) {
	EV3ColorSensor ls = new EV3ColorSensor(SensorPort.S4);
	BaseRegulatedMotor mLeft = new EV3LargeRegulatedMotor(MotorPort.A);
	BaseRegulatedMotor mRight = new EV3LargeRegulatedMotor(MotorPort.B);
	mLeft.setSpeed(100);
	mRight.setSpeed(100);
	
	String colorName = "";
	String qr = QRCode();
	
	while (!(colorName.contentEquals(qr))) {
	
	SensorMode color = ls.getColorIDMode();
	float[] sample = new float[1];
	color.fetchSample(sample, 0);
	int colorId = (int)sample[0];
	

	LCD.clear();
	switch(colorId){
		case Color.NONE: colorName = "NONE"; break;
		case Color.BLUE: colorName = "BLUE"; break;
		case Color.WHITE: colorName = "WHITE"; break;
		case Color.RED: colorName = "RED"; break;
		case Color.GREEN: colorName = "GREEN"; break;
		case Color.BLACK: colorName = "BLACK"; break;

	}
	
	// while colour != the colour of qr code () colorID
	

	LCD.drawString("colour reading" + colorName, 2, 1);
	LCD.drawString("colour we want" + qr, 2, 3);
	//while(1==1)
	
		
		mLeft.forward();
		mRight.backward();
	}
	
	
}
}