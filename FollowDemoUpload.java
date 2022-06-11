import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FollowDemoUpload implements Behavior  {

	//private MovePilot pilot;

	public FollowDemoUpload(MovePilot pilot) {
		MovePilot p = pilot;
	}

	public static String QRCode() {
		List<String> y = new ArrayList<String>();
		y.add("GREEN");
		y.add("BLUE");
		y.add("BLACK");
		y.add("RED");
		Random rand = new Random();
		String colorN = y.get(rand.nextInt(y.size()));
		return colorN;
	}

	public static String GetColorSample(EV3ColorSensor ls) {
	
		SensorMode color = ls.getColorIDMode();
		float[] sample = new float[1];
		color.fetchSample(sample, 0);
		int colorId = (int)sample[0];
		
		String colorName = "";
		LCD.clear();
		
		switch(colorId){
		case Color.NONE: colorName = "NONE"; break;
		case Color.BLUE: colorName = "BLUE"; break;
		case Color.WHITE: colorName = "WHITE"; break;
		case Color.RED: colorName = "RED"; break;
		case Color.GREEN: colorName = "GREEN"; break;
		case Color.BLACK: colorName = "BLACK"; break;
		//case Color.YELLOW: colorName = "YELLOW"; break;
		case Color.BROWN: colorName = "BROWN"; break;
		}
		
		return colorName;
	}

	public static void main(String[] args) {
	EV3ColorSensor ls = new EV3ColorSensor(SensorPort.S4);
	
	EV3UltrasonicSensor dist = new EV3UltrasonicSensor(SensorPort.S1);
	SampleProvider sp = dist.getDistanceMode();
	float[] samples = new float [1];
	 
	BaseRegulatedMotor mLeft = new EV3LargeRegulatedMotor(MotorPort.A);
	BaseRegulatedMotor mRight = new EV3LargeRegulatedMotor(MotorPort.B);
	mLeft . synchronizeWith ( new BaseRegulatedMotor [] { mRight });
	
	String colorWant = "BLUE";
	
	while (!Button.ENTER.isDown()) {
		
		String colorName = GetColorSample(ls);
		sp.fetchSample ( samples , 0 );
		
		LCD.drawString("reading " + colorName, 2, 1);
		LCD.drawString("want " + colorWant, 2, 3);
				
		
		mLeft.setSpeed(144);
		mRight.setSpeed(144);
		mLeft.backward();
		mRight.backward();
		

		if (colorName== colorWant) {
			mLeft.backward();
			mRight.stop();
		} else if (colorName== "WHITE") {
			mRight.backward();
			mLeft.stop();
		}else if(samples[0] < 0.035) {

			mLeft.setSpeed(0);
			mRight.setSpeed(0);
			mLeft.stop();
			mRight.stop();
			mLeft.setSpeed(100);
			mRight.setSpeed(100);
			mLeft.startSynchronization();
			mLeft.rotate(180);
			mRight.rotate(180);
			mLeft.endSynchronization();
			mLeft . waitComplete ();
			mRight . waitComplete ();
			mLeft.stop();
			mRight.stop();
			colorWant = QRCode();
		
		
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
		dist.close();
		ls.close();
		mLeft.close();
		mRight.close();
		}

	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}