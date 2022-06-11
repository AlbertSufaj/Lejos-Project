//need to import all packages



public class ColorScanner {
	
		public static String colorName = "";
		MovePilot pilot;
		
		public ColorScanner(MovePilot p, EV3ColorSensor color){
			this.pilot = p;
			this.colorsensor = color;
		}
			
		public void run(){
			EV3ColorSensor colorsensor;
			SensorMode color = colorsensor.getColorIDMode();
			float[] sample = new float[1];
			color.fetchSample(sample, 0);
			int colorId = (int)sample[0];
			
			String colorName = "";
			LCD.clear();
			
			switch(colorId){
			case Color.GREEN: colorName = "green"; break;
			case Color.BLUE: colorName = "2blue"; break;
			case Color.RED: colorName = "4red4"; break;
			//case Color.END: colorName = "6end6"; break;
			case Color.BLACK: colorName = "black"; break;
			case Color.WHITE: colorName = "white"; break;
			//case Color.YELLOW: colorName = "YELLOW"; break;
			//case Color.BROWN: colorName = "BROWN"; break;
			}
		}
		
		
		
		
		// method that gets a color and returns it
		
		public static String GetColorSample() {
			return colorName;
		}
		
}
