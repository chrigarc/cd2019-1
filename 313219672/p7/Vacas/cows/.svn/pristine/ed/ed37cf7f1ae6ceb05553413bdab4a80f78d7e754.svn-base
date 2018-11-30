package main;

public class Action {
	private ActionType at;

	private int dx;
	private int dy;
	
	public Action(ActionType at, int dx, int dy){
		this.at = at;
		this.dx = dx;
		this.dy = dy;
		
		//diagonal movements are permitted
		if(dx > 1 || dx < -1) this.dx = 0;
		if(dy > 1 || dy < -1) this.dy = 0;
		
		if(at.equals(ActionType.SKIP) || at.equals(ActionType.EAT)){this.dx = 0; this.dy = 0;}
		
		//can't breed under itself
		if(at.equals(ActionType.BREED) && this.dx == 0 && this.dy == 0){this.at = ActionType.SKIP;}
	}
	
	public ActionType getActionType() {
		return at;
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}
}
