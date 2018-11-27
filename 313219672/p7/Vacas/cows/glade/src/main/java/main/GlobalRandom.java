package main;

import java.util.Random;

public class GlobalRandom {

private static GlobalRandom instance;

	public Random rand;

	public static synchronized GlobalRandom getInstance() {
		if (instance == null) {
			instance = new GlobalRandom();
			instance.rand = new Random();
		}
		return instance;
	}
	
}
