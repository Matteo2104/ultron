package it.prova.brain.factory;

import java.util.ArrayList;
import java.util.List;
import it.prova.brain.Edge;
import it.prova.brain.Neuron;

public class SinapsiFactory {
	
	
	public static List<Edge> getSinapsi(List<Neuron> neuroni, int numeroSinapsi, double capacityAvg) {
		int numeroNeuroni = neuroni.size();
		long maxCapacity = numeroNeuroni*(numeroNeuroni-1)/2;
		if (numeroSinapsi > maxCapacity)
			throw new RuntimeException("numero sinapsi troppo grande - capacità massima = " + maxCapacity);
			
		
		// istanzio N sinapsi
		List<Edge> sinapsi = new ArrayList<>();		
		
		
		int a=0, b=0;
		Neuron temp1=null, temp2=null;
		for (int i=0;i<numeroSinapsi;i++) {
			
			// inizio una procedura di ricerca dei due nodi da collegare
			// metto arcoEsistente a true di default solo per il primo ciclo
			boolean arcoEsistente = true;
			while (arcoEsistente) {
				// i numeri non devono essere uguali, cioè un nodo non può essere collegato a se stesso
				a = (int)(Math.random()*neuroni.size());
				do {
					b = (int)(Math.random()*neuroni.size());
				} while (b==a);
				
				// se i due numeri costruiscono un arco gia esistente fin ora ricomincio da capo
				arcoEsistente=false;
				Edge sinapsiTemp = null;
				for (int j=0;j<sinapsi.size();j++) {
					sinapsiTemp = sinapsi.get(j);
					
					if (  	(sinapsiTemp.getU().getId() == a && sinapsiTemp.getV().getId() == b) || 
							(sinapsiTemp.getU().getId() == b && sinapsiTemp.getV().getId() == a)  ) {
						arcoEsistente=true;
					}
					
					if (arcoEsistente)
						break;
				}
			}
			
			Neuron neuroneTemp = null;
			for (int j=0;j<numeroNeuroni;j++) {
				neuroneTemp = neuroni.get(j);
				
				if (neuroneTemp.getId() == a)
					temp1=neuroneTemp;
				
				if (neuroneTemp.getId() == b)
					temp2=neuroneTemp;
			}	
			
			// aggiungo sinapsi con capacità in [capacityMean-0.1, capacityMean+0.1]
			sinapsi.add(new Edge(temp1, temp2, (Math.random()*0.2)+capacityAvg-0.1));
		}	
		
		
		
		return sinapsi;
	}
}
