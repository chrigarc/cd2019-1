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

public class VacaNR implements ICow{
  
  Integer idHijo;
  String idActual;

    public Color getColor() {
        return new Color(139, 17, 201);
    }
  class Cell{int di,dj; Cell(int di, int dj){this.di = di; this.dj = dj;}}
  
  public VacaNR(){
    idActual = "0";
    idHijo = 0;
  }
  
  public VacaNR(String actualId, Integer sonId){
    idHijo = 1;
    idActual = actualId+sonId;
  }
  
  public Action make_turn(LocalInfo li) {
    
    //Determina celdas libres en adyacencias
    List<Cell> celdasLibres = new ArrayList<Cell>();
    for(int i = 0; i < 3; i++)
      for(int j = 0; j < 3; j++){
      if(li.nc[i][j] != null && li.nc[i][j].cowID == null){
        celdasLibres.add(new Cell(i-1,j-1));
      }
    }    
    //Si no hay celdas libres, la mejor opcion es comer en posicion actual
    if(celdasLibres.size() == 0) return new Action(ActionType.EAT,0,0);//if(celdasLibres.size() == 0) return new Action(ActionType.EAT,0,0);
    
    Cell celdaLibre = celdasLibres.get(GlobalRandom.getInstance().rand.nextInt(celdasLibres.size()));
    //Si es posible, clonarse en celda adyacente (Manteniendo +5 de vida como margen de supervivencia)
    if(li.grassEaten > Config.grassToBreed+5) return new Action(ActionType.BREED,celdaLibre.di,celdaLibre.dj);
    
    //Revisar si la celda actual tiene pasto, en ese caso come
    if(li.nc[1][1].grass > 0) return new Action(ActionType.EAT,0,0);
    
    //Si no paso ninguna de las anteriores, nos movemos a celda adyacente
    //SUJETO A MODIFICACION, Podria llevar una lista de una o dos anteriores casillas visitadas para no ir y regresar a la misma
    return new Action(ActionType.MOVE,celdaLibre.di,celdaLibre.dj);
  }
  
  public Integer getSTUDENTid() {
    return 311256512;
  }
  
  public String getName() {
    return "Bernal "+idActual;
  }
  
  public ICow clone(){
    idHijo++;
    return new VacaNR(idActual,idHijo);
  }
  
  public boolean skipPopulation() {
    return false;
  }
}
