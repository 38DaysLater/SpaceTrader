package spacetrader;
import java.util.HashSet;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class Universe {
        private final int NUM_SOLAR_SYSTEMS = 5;
        SolarSystem[] SSArray = new SolarSystem[NUM_SOLAR_SYSTEMS];
        
	public Universe() throws IOException{
		//set up total number of SS we want
		
		//sets up string of solar system name creation
		String[] solarSystemNames = new String[NUM_SOLAR_SYSTEMS];
		FileReader inputFile = new FileReader("SolarSystemNames.txt");
        BufferedReader bufReader = new BufferedReader(inputFile);
        
		for(int i = 0; i < NUM_SOLAR_SYSTEMS; i ++){
			solarSystemNames[i] = bufReader.readLine();
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
		
        bufReader.close();

        
	}//ends the constructor

        
        @Override
	public String toString(){
            String returnString = "";
            for(int i = 0; i < NUM_SOLAR_SYSTEMS; i ++){
                returnString = returnString + SSArray[i].toString();
            }
             
            return returnString;
	}
	public static void main(String args[]) throws IOException{
		Universe u = new Universe();
                System.out.println();
                System.out.print(u);
                
			
	}
	
	
}

