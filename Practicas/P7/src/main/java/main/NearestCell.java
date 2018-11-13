package main;

public class NearestCell {
    public Integer cowID;
    public int grass;
    
    public NearestCell clone(){
    	NearestCell nc = new NearestCell();
    	nc.cowID = cowID;
    	nc.grass = grass;
    	return nc;
    }
}
