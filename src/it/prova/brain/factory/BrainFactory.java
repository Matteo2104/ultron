package it.prova.brain.factory;

import java.util.List;
import it.prova.brain.Brain;
import it.prova.brain.CounterId;
import it.prova.brain.Edge;
import it.prova.brain.Neuron;


public class BrainFactory {
	
	public static Brain getBrain(int numeroNeuroni, int numeroSinapsi, double capacitaMedia, boolean printResult) {
		Brain brain = new Brain();
		CounterId.init();
		
		System.out.println("Creazione dei neuroni...");
		// per costruire un cervello ho bisogno innanzitutto di istanziare un certo numero di neuroni
		List<Neuron> neuroni = NeuronFactory.getNeuroni(numeroNeuroni);
		brain.setNeuroni(neuroni);
		
		System.out.println("Creazione delle sinapsi...");
		// dopodichè ho bisogno di creare le sinapsi, ovvero di collegare i neuroni
		List<Edge> sinapsi = SinapsiFactory.getSinapsi(neuroni, numeroSinapsi, capacitaMedia);
		brain.setSinapsi(sinapsi);
		
		System.out.println("Collegamento dei neuroni tramite le sinapsi...");
		// e ogni neurone dovrà essere collegato ai neuroni adiacenti
		Neuron neuroneTemp = null;
		Edge sinapsiTemp = null;
		for (int i=0;i<numeroNeuroni;i++) {
			neuroneTemp = neuroni.get(i);
			
			for (int j=0;j<numeroSinapsi;j++) {
				sinapsiTemp = sinapsi.get(j);
				
				
				if (sinapsiTemp.getU().getId() == neuroneTemp.getId()) {
					neuroneTemp.getAdiacenti().add(sinapsiTemp.getV());
					neuroneTemp.getSinapsi().add(sinapsiTemp);
				}
				if (sinapsiTemp.getV().getId() == neuroneTemp.getId()) {
					neuroneTemp.getAdiacenti().add(sinapsiTemp.getU());
					neuroneTemp.getSinapsi().add(sinapsiTemp);
				}
			}
		}
		
		System.out.println("Creazione del cervello completata");
		
		// la funzione di stampa non ha bisogno di prestazioni per ora
		if (printResult) {
			// stampo le sinapsi
			for (Edge edge : sinapsi) {
				System.out.println(edge);
			}
			
			// stampo i neuroni
			for (Neuron neurone : brain.getNeuroni()) {
				System.out.println("NEURONE " + neurone.getId());
				for (Neuron adiacente : neurone.getAdiacenti()) {
					System.out.println(adiacente);
				}
				System.out.println("\n");
				for (Edge sinapsiItem : neurone.getSinapsi()) {
					System.out.println(sinapsiItem);
				}
				System.out.println("----------------");
			}
		}
		
		
		
		/*
		// istanzio 100 recettori tattile
		brain.setListaRecettori(ReceptorFactory.getSkinReceptors(recettoriTattili));

		// istanzio 10000 neuroni
		brain.setListaNeuroni(NeuronFactory.getNeuroni(neuroni));

		// collego tutti i recettori a tutti i neuroni
		brain.getListaRecettori().forEach(receptor -> {
			receptor.setListaNeuroni(brain.getListaNeuroni());
		});
		*/
		return brain;
	}
	
}
