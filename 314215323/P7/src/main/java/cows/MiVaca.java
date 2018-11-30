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


public class MiVaca implements ICow{

    public Color getColor() {
        return new Color(130,126,150);
    }
    class Cell{int i,j; Cell(int i, int j){this.i = i; this.j = j;}}
	
    public Action make_turn(LocalInfo local) {
		
	//Determina celdas libres
	List<Cell> freeCells = new ArrayList<Cell>();
	for(int i = 0; i < 3; i++)
	    for(int j = 0; j < 3; j++){
		if(local.nc[i][j] != null && local.nc[i][j].cowID == null && local.nc[i][j].grass > 0){
		    freeCells.add(new Cell(i,j));
		}	
	    }		
	//metodos para comer hasta el final si no se mueve o no se reproduce
	if(freeCells.isEmpty()) return new Action(ActionType.EAT,0,0);
	Cell freeCell = freeCells.get(GlobalRandom.getInstance().rand.nextInt(freeCells.size()));                         
	if(local.grassEaten >= 2*Config.grassToBreed) return new Action(ActionType.BREED,freeCell.i-3,freeCell.j-3);
	if(local.nc[1][1].grass > 0) return new Action(ActionType.EAT,0,0);		
	return new Action(ActionType.MOVE,freeCell.i-1,freeCell.j-1);
    }
	
    @Override
    public ICow clone(){
	return new MiVaca();
    }
    public boolean skipPopulation() {
	return false;
    }
    public Integer getSTUDENTid() {
	return 314215323;
    }
    public String getName() {
	return "TEAM";
    }
}
