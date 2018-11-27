package cows;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


import main.Action;
import main.ActionType;
import main.Config;
import main.GlobalRandom;
import main.ICow;
import main.LocalInfo;


public class CowMarco implements ICow{

    public Color getColor() {
        return new Color(123,115,127);
    }


	class Cell{int di,dj; Cell(int di, int dj){this.di = di; this.dj = dj;}}
	
	public Action make_turn(LocalInfo li) {
		
		//Determine free cells
		List<Cell> freeCells = new ArrayList<Cell>();
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++){
				if(li.nc[i][j] != null && li.nc[i][j].cowID == null && li.nc[i][j].grass > 0){
					freeCells.add(new Cell(i,j));
				}	
			}
		
		//can't move, can't breed, so try to eat until the end...
		if(freeCells.isEmpty()) return new Action(ActionType.EAT,0,0);
                Cell freeCell = freeCells.get(GlobalRandom.getInstance().rand.nextInt(freeCells.size()));
                         
		if(li.grassEaten >= 2*Config.grassToBreed) return new Action(ActionType.BREED,freeCell.di-3,freeCell.dj-3);
		if(li.nc[1][1].grass > 0) return new Action(ActionType.EAT,0,0);
		
		return new Action(ActionType.MOVE,freeCell.di-1,freeCell.dj-1);
	}

	public Integer getSTUDENTid() {
		return 313110902;
	}

	public String getName() {
		return "Quinos";
	}
	
        @Override
	public ICow clone(){
		return new CowMarco();
	}

	public boolean skipPopulation() {
		return false;
	}
}
