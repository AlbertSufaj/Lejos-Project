
public class FollowColorPath implements Behavior	{
	
	MovePilot pilot;
	private Thread QRScanner;
	private Thread ColourScanner;
	private String colorWant;
	private String colorReading;
	
	FollowColorPath(MovePilot p, Thread QRScanner, Thread ColourScanner){
		this.pilot = p;
		this.QRScanner = QRScanner;
		this.ColourScanner = ColourScanner;
		}
	
	public void action() {
		
		QRScanner.start();
		ColourScanner.start();
				
		String colorReading = getColorSample();
		String colorWant = getReading();
		//we need to return value of Colour Scanner to make STARK follow path
		
		while (!Button.ENTER.isDown()) {
			
			LCD.drawString("reading " + colorReading, 2, 1);
			LCD.drawString("want " + colorWant, 2, 3);
		
			
	}
	
		
		
		
	public boolean takeControl() {
		connectToPhone connect = new connectToPhone;
		return (connect.getConnected());
	}
	
	
	
	
}
