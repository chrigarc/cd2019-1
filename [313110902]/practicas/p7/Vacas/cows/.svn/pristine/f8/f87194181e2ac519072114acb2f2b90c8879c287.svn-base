package main;

public class LocalInfo {
	
	public int grassEaten; //when this is 0, the cow dies
	
	//you are at [1][1], out of bounds shell be null's
	public NearestCell nc[][] = new NearestCell[3][3];
	
	public LocalInfo clone(){
		LocalInfo li = new LocalInfo();
		li.grassEaten = grassEaten;
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				li.nc[i][j] = nc[i][j] != null ? nc[i][j].clone() : null;
		return li;
	}
}
