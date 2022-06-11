import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Trundle extends Behaviors {
	private MovePilot pilot;
	Trundle (MovePilot p) {
		this.pilot = p;
	}

	public void action(){
		pilot.forward();
	}

	public void suppress (){ }

	public boolean takeControl () {
		return true;
	}
}