package spacetrader;
import java.util.HashSet;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author AfiqAzaibi
 */

public class Universe {
        private final int NUM_SOLAR_SYSTEMS = 3;
        SolarSystem[] SSArray = new SolarSystem[NUM_SOLAR_SYSTEMS];
        
        
        
        /**
        * Constructor of Solar System 
        * reads in text file and randomly generates solar system coordinates
        * @param none
        * @return none
        * 
        */
	public Universe() throws IOException{
		//sets up string of solar system name creation
		String[] solarSystemNames = new String[NUM_SOLAR_SYSTEMS];
                try {
                    FileReader inputFile = new FileReader("SolarSystemNames.txt");
                    BufferedReader bufReader = new BufferedReader(inputFile);
            
                    for(int i = 0; i < NUM_SOLAR_SYSTEMS; i ++){
			solarSystemNames[i] = bufReader.readLine();
                    }
                    
                    bufReader.close();
                }
                
                catch(IOException e){
                    System.out.println("goofed");
                }
        

		//randomly generates a solar system name and location such that no two occupy the same space		
		HashSet<int[]> coordinates = new HashSet<int[]>();
		Random rand = new Random();		
		int x,y;
		int[] key = new int[2];
		
		for(int i = 0; i < NUM_SOLAR_SYSTEMS; i ++) {

			x = rand.nextInt(100);
			y = rand.nextInt(100);
			key[0] = x; key[1] = y; 
			
			//makes sure set doesn't have same coordinate			
			while(coordinates.contains(key)) {
				//change memory address
				key = null;
				key = new int[2];
				x = rand.nextInt(100);
				y = rand.nextInt(100);
				key[0] = x; key[1] = y;
			}
			coordinates.add(key);
		}//ends random generation of solar systems			
		

		//creating a solar system
		int i = 0;
		for(int[] xy: coordinates) {
			SolarSystem ss = new SolarSystem(solarSystemNames[i], xy[0], xy[1]);
                        SSArray[i] = ss;
			i++;
		}
	}//ends the constructor

        
        @Override
        /**
        * Displays the contents of the solar system 
        * @param none
        * @return a string of the contents
        */
	public String toString(){
            String returnString = "";
            for(int i = 0; i < NUM_SOLAR_SYSTEMS; i ++){
                returnString = returnString + SSArray[i].toString();
            }
             
            return returnString;
	}

	
	
}


