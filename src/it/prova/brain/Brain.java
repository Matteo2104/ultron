package it.prova.brain;

import java.util.List;

public class Brain {
	private List<Neuron> neuroni;
	private List<Edge> sinapsi;
	
	
	
	public List<Neuron> getNeuroni() {
		return neuroni;
	}
	public List<Edge> getSinapsi() {
		return sinapsi;
	}
	public void setNeuroni(List<Neuron> neuroni) {
		this.neuroni = neuroni;
	}
	public void setSinapsi(List<Edge> sinapsi) {
		this.sinapsi = sinapsi;
	}
	
	
	
	
	
	
	
	public Neuron getReceptor() {
		long numeroNeuroni = this.neuroni.size();
		
		System.out.println("Elezione del recettore...");
		Neuron neuroneTemp = null;
		for (int i=0;i<numeroNeuroni;i++) {
			neuroneTemp = neuroni.get(i);
			
			if (neuroneTemp.isRecettore()) {
				return neuroneTemp;
			}
		}
		
		return null;
	}
	
	
	/*
	 * RITORNA UNA LISTA DI RECETTORI IN CASO CHE IL RECETTORE NON SIA UNICO
	public List<Neuron> getReceptorsList() {
		int numeroNeuroni = this.neuroni.size();

		Neuron neuroneTemp = null;
		for (int i = 0; i < numeroNeuroni; i++) {
			neuroneTemp = neuroni.get(i);

			if (neuroneTemp.isRecettore()) {
				return neuroneTemp;
			}
		}

		return null;
	}
	
	public List<Neuron> getReceptorsList() {
		return this.neuroni.stream().filter(neuroneItem -> neuroneItem.isRecettore()).collect(Collectors.toList());
	}
	*/
	
	
	public void resetLife() {
		int numeroSinapsi = this.sinapsi.size();
		
		Edge sinapsiTemp = null;
		for (int i=0;i<numeroSinapsi;i++) {
			sinapsiTemp = this.sinapsi.get(i);
			
			if (sinapsiTemp.getLife() < 2000000000) {
				sinapsiTemp.setLife(2000000000);
			}
		}
	}
	
	
	
}
