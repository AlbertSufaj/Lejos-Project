import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Behaviors {

	final static float WHEEL_DIAMETER = 56;
	final static float AXLE_LENGTH = 190;
	final static float ANGULAR_SPEED = 100;
	final static float LINEAR_SPEED = 70;
	
	public static void main(String[] args) {
		BaseRegulatedMotor mLeft = new EV3LargeRegulatedMotor(MotorPort.A);
		Wheel wLeft = WheeledChassis.modelWheel(mLeft, WHEEL_DIAMETER).offset(-AXLE_LENGTH / 2);
		BaseRegulatedMotor mRight = new EV3LargeRegulatedMotor(MotorPort.B);
		Wheel wRight = WheeledChassis.modelWheel(mRight, WHEEL_DIAMETER).offset(AXLE_LENGTH / 2);
		
		Chassis chassis = new WheeledChassis(new Wheel[] { wRight, wLeft}, WheeledChassis.TYPE_DIFFERENTIAL);
		
		MovePilot pilot = new MovePilot(chassis);
		
		Trundle trundle = new Trundle(pilot);
		BackUp  backup = new BackUp(pilot);
		LowBattery lowBattery = new LowBattery(pilot);
		Light light = new Light(pilot);
		dark d = new dark(pilot);
		
		Arbitrator ab = new Arbitrator (new Behavior[]{trundle, backup, light, d, lowBattery});
		ab.go();
	}
}
