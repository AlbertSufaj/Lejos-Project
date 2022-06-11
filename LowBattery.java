import lejos.hardware.Battery;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
	
public class LowBattery extends Behaviors {
	MovePilot pilot;
	int i;
	LowBattery(MovePilot p){
		pilot = p;
		}
	
		public void action() {
			
			//adding message sent to phone
			
			
			
			
			
			//_______________________________
			
			pilot.stop();
			LCD.clear();
			LCD.drawString(" :(", 3, 1);
			LCD.drawString("My battery is low and it's getting dark.", 3, 2);
			//code below is a while loop which makes the robot act erratic that its battery is running out!
			while(i<10) {
			pilot.forward();
			Delay.msDelay(10);
			pilot.backward();
			Sound.beep();
			i++;
			}
		}
	
		public void suppress() {
		}
		
		public boolean takeControl() {
			return Battery.getVoltage() < 1;
		}
}