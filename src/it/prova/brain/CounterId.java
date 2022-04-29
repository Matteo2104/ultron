package it.prova.brain;

public class CounterId {
	public static int counter;
	
	public static void init() {
		counter = 1;
	}
	
	public static void increment() {
		counter++;
	}
}
