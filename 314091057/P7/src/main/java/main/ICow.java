package main;

public interface ICow {
	public Action make_turn(LocalInfo li);
	public ICow clone();
	public Integer getSTUDENTid();
	public String getName();
	public boolean skipPopulation();
}
