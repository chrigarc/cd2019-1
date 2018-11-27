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

public class CowRamsot implements ICow{

    public Color getColor() {
        return Color.white;
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
                if(freeCells.size() == 0)
                    return new Action(ActionType.EAT,0,0);
                Cell a=freeCells.get(0);
                for(Cell c:freeCells){
                    //System.out.print(c.di+" "+c.dj+"|");
                    if(li.nc[c.di][c.dj].grass>li.nc[a.di][a.dj].grass)
                        a=c;
                }
		Cell freeCell = a;//freeCells.get(GlobalRandom.getInstance().rand.nextInt(freeCells.size()));
		//System.out.println(a.di+" "+a.dj+" freecell");
                if(li.grassEaten > Config.grassToBreed+2)
                    return new Action(ActionType.BREED,freeCell.di,freeCell.dj);
		if(li.nc[1][1].grass > 0)
                    return new Action(ActionType.EAT,0,0);
		return new Action(ActionType.MOVE,freeCell.di-1,freeCell.dj-1);
	}

	public Integer getSTUDENTid() {
		return 25976;
	}

	public String getName() {
		return "ArmandoParedes";
	}
	
	public ICow clone(){
		return new CowRamsot();
	}

	public boolean skipPopulation() {
		return false;
	}
}
