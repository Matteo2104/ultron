package it.prova.brain;

import java.util.ArrayList;
import java.util.List;

public class Response {
	public static String resultSignal;
	public static List<String[]> memory = new ArrayList<>();
	public static List<String> ricordo_i = new ArrayList<>();
	
	
	public static void setSignal(String signal) {
		resultSignal += signal;
	}
	
	public static void createRemembrance(List<Edge> sinapsi) {
		int N = sinapsi.size();
		String remembranceRepresentation = "";

		for (int i = 0; i < N; i++) {
			
			remembranceRepresentation += (int) ((2000000000 - sinapsi.get(i).getLife())/100);
		}
		
		ricordo_i.add(remembranceRepresentation);
		
		
		/*
		int sizeMemory = memory.size();
		for (int i=0;i<sizeMemory;i++) {
			if (MyUtils.compareArray(memory.get(i), remembrance)) {
				// se trovo un array uguale o simile emetto un segnale di risposta
				return;
			}
		}
		
		
		memory.add(remembrance);
		*/
	}
	
	public static void saveRemembrance() {
		String[] arrayTemp = new String[ricordo_i.size()]; 
		for (int i=0;i<ricordo_i.size();i++) {
			arrayTemp[i] = ricordo_i.get(i);
		}
		memory.add(arrayTemp);
		
		ricordo_i.clear();
	}
	
	
}
