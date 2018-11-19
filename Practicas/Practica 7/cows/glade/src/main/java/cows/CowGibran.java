package cows;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.Action;
import main.ActionType;
import main.Config;
import main.GlobalRandom;
import main.ICow;
import main.LocalInfo;

public class CowGibran implements ICow{

    public Color getColor() {
        return new Color(255,216,216);
    }

	class Cell{int di,dj; Cell(int di, int dj){this.di = di; this.dj = dj;}}
	
	public Action make_turn(LocalInfo li) {
		
		//Determine free cells
		List<Cell> freeCells = new ArrayList<Cell>();
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++){
				if(li.nc[i][j] != null && li.nc[i][j].cowID == null){
					freeCells.add(new Cell(i,j));
				}
				
			}
		
		//can't move, can't breed, so try to eat until the end...
                if(li.grassEaten > (1.2)*Config.grassToBreed && !freeCells.isEmpty()){
                    Cell freeCell =  freeCells.get(GlobalRandom.getInstance().rand.nextInt(freeCells.size()));
                    
                    if(li.nc[2][2] != null && li.nc[2][2].grass > 0 && li.nc[2][2].cowID == null)
                        return new Action(ActionType.BREED,1,1);
                    if(li.nc[0][0] != null && li.nc[0][0].grass > 0 && li.nc[0][0].cowID == null)
                        return new Action(ActionType.BREED,-1,-1);
                    if(li.nc[0][2] != null && li.nc[0][2].grass > 0 && li.nc[0][2].cowID == null)
                        return new Action(ActionType.BREED,-1,1);
                    if(li.nc[1][2] != null && li.nc[1][2].grass > 0 && li.nc[1][2].cowID == null) 
                        return new Action(ActionType.BREED,0,1);
                    if(li.nc[2][1] != null && li.nc[2][1].grass > 0 && li.nc[2][1].cowID == null) 
                        return new Action(ActionType.BREED,1,0);
                    if(li.nc[0][1] != null && li.nc[0][1].grass > 0 && li.nc[0][1].cowID == null) 
                        return new Action(ActionType.BREED,-1,0);                    
                    return new Action(ActionType.BREED,freeCell.di,freeCell.dj);
                }
		if(li.nc[1][1].grass > 0) 
                    return new Action(ActionType.EAT,0,0);
                
                if(li.nc[0][2] != null && li.nc[0][2].grass > 0 && li.nc[0][2].cowID == null)
                    return new Action(ActionType.MOVE,-1,1);
                
                if(li.nc[2][2] != null && li.nc[2][2].grass > 0 && li.nc[2][2].cowID == null)
                    return new Action(ActionType.MOVE,1,1);
                if(li.nc[0][0] != null && li.nc[0][0].grass > 0 && li.nc[0][0].cowID == null) 
                    return new Action(ActionType.MOVE,-1,-1);
                if(li.nc[0][1] != null && li.nc[0][1].grass > 0 && li.nc[0][1].cowID == null) 
                    return new Action(ActionType.MOVE,-1,0);
                if(li.nc[2][1] != null && li.nc[2][1].grass > 0 && li.nc[2][1].cowID == null) 
                    return new Action(ActionType.MOVE,1,0);
                if(li.nc[2][0] != null && li.nc[2][0].grass > 0 && li.nc[2][0].cowID == null) 
                    return new Action(ActionType.MOVE,1,-1);
                return new Action(ActionType.MOVE,0,-1);
	}

	public Integer getSTUDENTid() {
		return 309043511;
	}

	public String getName() {
		return "Yorche";
	}
	
	public ICow clone(){
		return new CowGibran();
	}

	public boolean skipPopulation() {
		return false;
	}
}
