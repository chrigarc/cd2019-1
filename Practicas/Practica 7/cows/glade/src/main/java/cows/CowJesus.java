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

public class CowJesus implements ICow{

    public Color getColor() {
        return new Color(139,131,120);
    }

	class Cell{int di,dj; Cell(int di, int dj){this.di = di; this.dj = dj;}}
	
        
	public Action make_turn(LocalInfo li) {
		
		//Determine free cells
		List<Cell> freeCells = new ArrayList<Cell>();
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++){
				if(  !(i ==1 && j == 1) && li.nc[i][j] != null && li.nc[i][j].cowID == null && li.nc[i][j].grass >= 1)
                                {
                                        
					freeCells.add(new Cell(i,j));
				}
				
			}
           
           
            Random generator = new Random();
            int randomInteger = generator.nextInt(); //0 . . .n-1
            
            int longitud = freeCells.size();
            
           
                //can't move, can't breed, so try to eat until the end...
            if(freeCells.size() == 0 )
            {
                 return new Action(ActionType.EAT,0,0);
            }
            Cell f= freeCells.get(generator.nextInt(freeCells.size()));  
            if((li.grassEaten) > (1.2*Config.grassToBreed))
            {
                if(li.nc[f.di][f.dj].grass >= 1)
                    return new Action(ActionType.BREED,f.di-3,f.dj-3);
            } 
                 
               
               
               
                
                f = freeCells.get(generator.nextInt(freeCells.size()));
                
                if( (li.grassEaten) >=  1 && (li.grassEaten) + (li.nc[f.di][f.dj].grass) > (2*Config.grassToBreed) )
                    return new Action(ActionType.MOVE,f.di-3,f.dj-3);
               
                if( (li.grassEaten)>= 3 )
                {   
                    if(li.nc[1][1].grass <= 1)
                    {
                    if(li.nc[f.di][f.dj].grass <=1)
                        f= freeCells.get(generator.nextInt(freeCells.size()));
                    }
                
                    if( li.nc[f.di][f.dj].grass >= 1)
                        return new Action(ActionType.MOVE,f.di-3,f.dj-3);
                        
                }
                        
                   
                
                if(li.nc[1][1].grass >= 1)
                    return new Action(ActionType.EAT,0,0);
                
                   
             
                
                return new Action(ActionType.MOVE,f.di-3,f.dj-3);
	} 


	public Integer getSTUDENTid() {
		return 311049145;
	}

	public String getName() {
		return "Pesefull animal";
	}
	
	public ICow clone(){
		return new CowExample();
	}

	public boolean skipPopulation() {
		return false;
	}
}
