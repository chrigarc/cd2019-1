package main;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.reflections.Reflections;

public class VirtualGlade {
	// number of cells in one dimension (the glade is a rectangle) 
	private final int size = Config.gladeSize;
	
	//grass array - current quantity of grass in each cell 
	public int grass[][];
	private ICow cows[][];
	private int maxGrass;

	//constructor: grass generator, cows distributor
	public VirtualGlade() throws Exception{
		
		//initialize field of grass with all zeros, and cows
		grass = new int[size][size];
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++) grass[i][j] = 0;
		
		//generate exponential distribution of grass:
		Random rand = new Random();
		Point2D c1 = new Point2D.Double(rand.nextDouble(), rand.nextDouble());
		
		int totalGrass = Config.grassMult*size*size;
		Point2D cur = new Point2D.Double();
		maxGrass = 0;
		
		while(totalGrass > 0){
			for(int i = 0; i < size; i++){
				if(totalGrass == 0) break;
				for(int j = 0; j < size; j++){
					if(totalGrass == 0) break;
					cur.setLocation((double)i/size, (double)j/size);
					if(rand.nextDouble() < (Math.exp(-Math.pow(cur.distance(c1),2)))){
						grass[i][j]++;
						totalGrass--;
						maxGrass = grass[i][j] > maxGrass ? grass[i][j] : maxGrass;
					}
				}
			}
		}
		
		//enumerate all active cows implementations
		Reflections reflections = new Reflections("cows");
		Set<Class<? extends ICow>> imps = reflections.getSubTypesOf(ICow.class);
		Map<Integer,ICow> instances = new HashMap<Integer,ICow>();
		ICow cow;
		
		for(Class<? extends ICow> imp : imps){
			cow = imp.newInstance();
			if(!cow.skipPopulation()) instances.put(cow.getSTUDENTid(),cow);
		}
		
		if(instances.isEmpty()) throw new Exception("No active COW implementations");
                System.out.println("Number of populations: " + instances.size());
		
		//determine the initial population size counters
		int singlePopSize = (int) Math.max(1, Math.floor(Config.populationFactor*size*size/instances.size()));
		if(Config.grassMult*size*size/Config.grassToBreed + singlePopSize*instances.size() > size*size) throw new Exception("Glade too small");
		System.out.println("Initial population size = " + singlePopSize);
		
		Map<Integer, Integer> counter = new HashMap<Integer, Integer>();
		for(Integer id : instances.keySet()) counter.put(id, singlePopSize);
		
		//initialize cows
		cows = new ICow[size][size];
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				cows[i][j] = null;
		
		Integer curPopID;
		while(!counter.isEmpty()){
			for(int i = 0; i < size; i++){
				if(counter.isEmpty()) break;
				for(int j = 0; j < size; j++){
					if(counter.isEmpty()) break;
					if(cows[i][j] != null) continue;
					if(rand.nextDouble() < Config.populationFactor){
						curPopID = (Integer) counter.keySet().toArray()[rand.nextInt(counter.size())];
						cows[i][j] = instances.get(curPopID).clone();
						if(counter.get(curPopID) <= 1) counter.remove(curPopID);
						else counter.put(curPopID, counter.get(curPopID) - 1);
					}
				}
			}
		}
	}
	
	public ICow[][] getCows() {
		return cows;
	}

	public int getSize() {
		return size;
	}
	
	public int[][] getGrass() {
		return grass;
	}
	
	public int getMaxGrass() {
		return maxGrass;
	}
}
