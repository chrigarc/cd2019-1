package cows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.Action;
import main.ActionType;
import main.Config;
import main.GlobalRandom;
import main.ICow;
import main.LocalInfo;

public class CowUser implements ICow{

	class Cell{int ai,aj; Cell(int ai, int aj){this.ai = ai; this.aj = aj;}}
	
	public Action make_turn(LocalInfo li) {
		
		//Determine las celdas vacias
		List<Cell> celdasVacias = new ArrayList<Cell>();
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++){
				if(li.nc[i][j] != null && li.nc[i][j].cowID == null){
					celdasVacias.add(new Cell(i,j));
				}
				
			}
		
		
		if(celdasVacias.size() == 0) return new Action(ActionType.EAT,0,0);
		Cell celdavacia = celdasVacias.get(GlobalRandom.getInstance().rand.nextInt(celdasVacias.size()));
		
		if(li.grassEaten > 2*Config.grassToBreed) return new Action(ActionType.BREED,celdavacia.ai,celdavacia.aj);
		if(li.nc[1][1].grass > 0) return new Action(ActionType.EAT,0,0);
		
		return new Action(ActionType.MOVE,celdavacia.ai,celdavacia.aj);
	}

	public Integer getSTUDENTid() {
		return 311287910;
	}

	public String getName() {
		return "Animal";
	}
	
	public ICow clone(){
		return new CowUser();
	}

	public boolean skipPopulation() {
		return false;
	}
}