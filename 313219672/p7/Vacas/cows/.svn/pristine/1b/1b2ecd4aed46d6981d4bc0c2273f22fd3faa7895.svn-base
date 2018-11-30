/**
 * This cow uses a simple greedy algorithm to eat whatever it wants and then breed
 * @author AlOrozco53
 * @version 0.1
 **/
package cows;

import java.awt.Color;
import java.util.ArrayList;

import main.Action;
import main.ActionType;
import main.Config;
import main.ICow;
import main.LocalInfo;

public class GreedyEatCow implements ICow{
    
    private class Cell{
	protected int di,dj;
	
	public Cell(int di, int dj) {
	    this.di = di;
	    this.dj = dj;
	}
    }
    
    /* Determine free cells */
    private ArrayList<Cell> freeCells(LocalInfo li) {
	ArrayList<Cell> freeCells = new ArrayList<Cell>();
	for(int i = 0; i < 3; i++)
	    for(int j = 0; j < 3; j++)
		if(li.nc[i][j] != null && li.nc[i][j].cowID == null)
		    freeCells.add(new Cell(i-1,j-1));
	return freeCells;
    }

    private Cell getFreeCell(ArrayList<Cell> freeCells, LocalInfo li) {
	Cell fc = null;
	if(freeCells.size() > 0) {
	    fc = freeCells.get(0);
	    for(Cell c : freeCells) {
		if(li.nc[c.di+1][c.dj+1].grass > li.nc[fc.di+1][fc.dj+1].grass)
		    fc = c;
	    }
	}
	return fc;
    }
    
    public Action make_turn(LocalInfo li) {
	ArrayList<Cell> freeCells = freeCells(li);
	Cell fc;
	if(li.nc[1][1].grass > 0)
	    return new Action(ActionType.EAT, 0, 0);
	else if(freeCells.size() > 0) {
	    fc = getFreeCell(freeCells, li);
	    if(fc != null) {
		if(li.grassEaten > Config.grassToBreed)
		    return new Action(ActionType.BREED, fc.di, fc.dj);	    
		else
		    return new Action(ActionType.MOVE, fc.di, fc.dj);
	    }
	}
	return new Action(ActionType.SKIP, 0, 0);
    }
    
    public Integer getSTUDENTid() {
	return 413080260;
    }
    
    public String getName() {
	return "GreedyEatCow";
    }
    
    public ICow clone() {
	return new GreedyEatCow();
    }
    
    public boolean skipPopulation() {
	return false;
    }

	public Color getColor() {
		return new Color(255,0,255);
	}
}
