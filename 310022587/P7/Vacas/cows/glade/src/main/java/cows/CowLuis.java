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

public class CowLuis implements ICow{

    public Color getColor() {
        return new Color(131,139,131);
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
  if(freeCells.size() == 0) return new Action(ActionType.EAT,0,0);
  Cell freeCell = freeCells.get(GlobalRandom.getInstance().rand.nextInt(freeCells.size()));
  //cambio
  if(li.grassEaten > 18) return new Action(ActionType.BREED,freeCell.di,freeCell.dj);
  if(li.nc[1][1].grass > 0 ) return new Action(ActionType.EAT,0,0);
  if(li.grassEaten < 10 && li.grassEaten > 5){
      for(int x=0;x<3;x++){
          for(int y = 0;y<3;y++){
              if(li.nc[x][y]!=null && li.nc[x][y].cowID==null){
                  if(li.nc[x][y].grass > 0){
                  return new Action(ActionType.MOVE,x-1,y-1);
                  }
                  //else return new Action(ActionType.EAT,0,0);
                  }
              }
          }
      }
  
  
  
  
  

  return new Action(ActionType.MOVE,freeCell.di,freeCell.dj);
 }

 public Integer getSTUDENTid() {
  return 313352304;
 }

 public String getName() {
  return "Viejo apestoso";
 }
 
 public ICow clone(){
  return new CowFercho();
 }

 public boolean skipPopulation() {
  return false;
 }
}
