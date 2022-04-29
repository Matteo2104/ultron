package it.prova.brain.factory;

import java.util.ArrayList;
import java.util.List;

import it.prova.brain.Neuron;

public class NeuronFactory {
	
	public static List<Neuron> getNeuroni(long N) {
		// istanzio N neuroni
		List<Neuron> neuroni = new ArrayList<>();
		
		for (long i = 0; i < N; i++) {
			neuroni.add(new Neuron());
		}
		return neuroni;
	}
	
}
