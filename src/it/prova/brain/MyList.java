package it.prova.brain;

public class MyList {
	private Neuron[] lista;
	private int size;
	
	public MyList() {
	}
	
	public MyList(int size) {
		lista = new Neuron[size];
		this.size = size;
	}
	
	
	public int size() {
		return this.size;
	}
	
	public Neuron get(int index) {
		return lista[index];
	}
}
