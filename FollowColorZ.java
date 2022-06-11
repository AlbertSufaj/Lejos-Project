import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.robotics.navigation.MovePilot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FollowColorZ extends Behaviors{
	MovePilot pilot;
	FollowColorZ(MovePilot p){
		pilot = p;
		}
	static String value ="";
	static MainClass m = new MainClass();
	public static String QRCode() {
		value = m.getReading(value);
		return value;
	}

	public static String GetColorSample(EV3ColorSensor ls) {
	
	
		SensorMode color = ls.getColorIDMode();
		float[] sample = new float[1];
		color.fetchSample(sample, 0);
		int colorId = (int)sample[0];
		
		String colorName = "";
		
		LCD.clear();
		
		switch(colorId){
		case Color.NONE: colorName = "none"; break;
		case Color.BLUE: colorName = "blue"; break;
		case Color.WHITE: colorName = "white"; break;
		case Color.RED: colorName = "red"; break;
		case Color.GREEN: colorName = "green"; break;
		case Color.BLACK: colorName = "black"; break;
		case Color.YELLOW: colorName = "yellow"; break;
		}
		
		return colorName;
	}

	public static void main(String[] args) {
	EV3ColorSensor ls = new EV3ColorSensor(SensorPort.S4);
	
	BaseRegulatedMotor mLeft = new EV3LargeRegulatedMotor(MotorPort.A);
	BaseRegulatedMotor mRight = new EV3LargeRegulatedMotor(MotorPort.B);
	mLeft . synchronizeWith ( new BaseRegulatedMotor [] { mRight });
	
	String colorWant = "blue";
	
	while (!Button.ENTER.isDown()) {
	
		String colorName = GetColorSample(ls);
		
		LCD.drawString("reading " + colorName, 2, 1);
		LCD.drawString("want " + colorWant, 2, 3);
		
		mLeft.setSpeed(144);
		mRight.setSpeed(144);
		mLeft.backward();
		mRight.backward();
	
	if (colorName== colorWant) {
		mLeft.backward();
		mRight.stop();
	} else if (colorName== "white") {
		mRight.backward();
		mLeft.stop();
	}else if(colorName == "black") {
		mLeft.setSpeed(0);
		mRight.setSpeed(0);
		mLeft.stop();
		mRight.stop();
		mLeft.setSpeed(100);
		mRight.setSpeed(100);
		mLeft.startSynchronization();
		mLeft.rotate(-90);
		mRight.rotate(-90);
		mLeft.endSynchronization();
		mLeft . waitComplete ();
		mRight . waitComplete ();
		mLeft.stop();
		mRight.stop();
		colorWant = QRCode();
	
	// need to put code in separate behaviour to spin.
		while (!(colorName.contentEquals(colorWant))) {
		
			colorName = GetColorSample(ls);
			
			LCD.drawString("reading " + colorName, 2, 1);
			LCD.drawString("want " + colorWant, 2, 3);
			
			mLeft.setSpeed(100);
			mRight.setSpeed(100);
			mLeft.backward();
			mRight.forward();
		
		
		}
		LCD.drawString("reading " + colorName, 2, 1);
		LCD.drawString("want " + colorWant, 2, 3);
		
		// colorName = colorWant;
		//break;
		}
		else {
			mLeft.setSpeed(0);
			mRight.setSpeed(0);
			mLeft.stop();
			mRight.stop();
			mLeft.setSpeed(100);
			mRight.setSpeed(100);
			mLeft.startSynchronization();
			mLeft.rotate(-90);
			mRight.rotate(-90);
			mLeft.endSynchronization();
			mLeft . waitComplete ();
			mRight . waitComplete ();
			mLeft.stop();
			mRight.stop();
		}
	
	}
	ls.close();
	mLeft.close();
	mRight.close();
	}

}