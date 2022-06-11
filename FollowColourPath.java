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

public class FollowColourPath extends Behaviors {

	MovePilot pilot;
	FollowColourPath(MovePilot p){
		pilot = p;
		}
	
	
	public static void main(String[] args) {
//
//		EV3ColorSensor ls = new EV3ColorSensor(SensorPort.S4);
//		BaseRegulatedMotor mLeft = new EV3LargeRegulatedMotor(MotorPort.A);
//		BaseRegulatedMotor mRight = new EV3LargeRegulatedMotor(MotorPort.B);
		
		while (!Button.ENTER.isDown()) {
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

		}
		
		mLeft.setSpeed(144);
		mRight.setSpeed(144);
		mLeft.forward();
		mRight.forward();


			color.fetchSample(sample, 0);
			if (colorName== "BLUE") {
				mLeft.forward();
				mRight.stop();

			} else if (colorName== "WHITE"){
				mRight.forward();
				mLeft.stop();

			}else if(colorName == "BLACK") {
				mLeft.stop();
				mRight.stop();
			}

		
		
		}
		ls.close();
		mLeft.close();
		mRight.close();

	}

}