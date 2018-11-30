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

public class Vaca implements ICow{

  Integer idHijo;
  String idActual;

    public Color setColor() {
        return new Color(255, 230, 0);
    }

  class Cell{
    int di,dj;
    Cell(int di, int dj){
      this.di = di;
      this.dj = dj;
    }
  }

  public Vaca(){
    idActual = "0";
    idHijo = 0;
  }

  public Vaca(String actualId, Integer sonId){
    idHijo = 1;
    idActual = actualId+sonId;
  }

  public Action make_turn(LocalInfo li) {
    List<Cell> celdasLibres = new ArrayList<Cell>();
    for(int i = 0; i < 3; i++)
      for(int j = 0; j < 3; j++){
      if(li.nc[i][j] != null && li.nc[i][j].cowID == null){
        celdasLibres.add(new Cell(i-1,j-1));
      }
    }
    if(celdasLibres.size() == 0) return new Action(ActionType.EAT,0,0);
    Cell celdaLibre = celdasLibres.get(GlobalRandom.getInstance().rand.nextInt(celdasLibres.size()));
    if(li.grassEaten > 2*Config.grassToBreed+3)
    return new Action(ActionType.BREED,celdaLibre.di,celdaLibre.dj);
    if(li.nc[1][1].grass > 0) return new Action(ActionType.EAT,0,0);
    return new Action(ActionType.MOVE,celdaLibre.di,celdaLibre.dj);
  }

  public Integer getSTUDENTid() {
    return 311217919;
  }

  public String getName() {
    return " " +idActual;
  }

  public ICow clone(){
    idHijo++;
    return new Vaca(idActual,idHijo);
  }

  public boolean skipPopulation() {
    return false;
  }
}
