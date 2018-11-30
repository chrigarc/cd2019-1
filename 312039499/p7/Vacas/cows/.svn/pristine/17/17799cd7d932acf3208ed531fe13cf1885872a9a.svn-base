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
import main.NearestCell;

public class CowP implements ICow{
    class Cell{int di,dj; Cell(int di, int dj){this.di = di; this.dj = dj;}}
	
	public Action make_turn(LocalInfo li) {
		//Determine free cells
		List<Cell> freeCells = new ArrayList<Cell>();
                for(int i = 0; i < 3; i++)
                    for(int j = 0; j < 3; j++){
                        if(li.nc[i][j] != null && li.nc[i][j].cowID == null){
                            freeCells.add(new Cell(i,j));
                            if(i<3 && j<3){
                                freeCells.add(new Cell(i+2,j+2));
                                freeCells.add(new Cell(i-2,j-2));
                       
                                
                            }     
                    }    
                }
		//can't move, can't breed, so try to eat until the end...
		if(freeCells.size() == 0) return new Action(ActionType.EAT,0,0);              
		Cell freeCell = freeCells.get(GlobalRandom.getInstance().rand.nextInt(freeCells.size()));		
		if(li.grassEaten > 5*Config.grassToBreed) return new Action(ActionType.BREED,freeCell.di,freeCell.dj);
		if(li.nc[1][1].grass > 0) return new Action(ActionType.EAT,0,0);		
		return new Action(ActionType.MOVE,freeCell.di+1,freeCell.dj);
	}

	public Integer getSTUDENTid() {
		return 311177903;
	}

	public String getName() {
		return "Pesefull animal";
	}
	
	public ICow clone(){
		return new CowP();
	}

	public boolean skipPopulation() {
		return false;
	}
        public Color getColor() {
		return new Color(255,230,3);
	}
	
}
