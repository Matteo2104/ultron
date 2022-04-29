package it.prova.brain;

public class Edge {
	// nodo di partenza
	private Neuron u;
	// nodo di arrivo
	private Neuron v;
	
	// capacità di trasmissione dati arco
	private Double capacity;
	
	// vita residua dell'arco
	private Integer life;
	
	public Edge() {}
	public Edge(Neuron u, Neuron v, Double capacity) {
		this.u = u;
		this.v = v;
		if (capacity!=null && capacity>0) 
			this.capacity = capacity;
		
		this.life = 2000000000;
	}

	
	public Neuron getU() {
		return u;
	}

	public Neuron getV() {
		return v;
	}

	public Double getCapacity() {
		return capacity;
	}

	public void setU(Neuron u) {
		this.u = u;
	}

	public void setV(Neuron v) {
		this.v = v;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}
	public Integer getLife() {
		return life;
	}
	public void setLife(Integer life) {
		this.life = life;
	}
	
	
	@Override
	public String toString() {
		return "<" + u.getId() + ", " + v.getId() + ", " + this.getCapacity() + ", " + this.getLife() + ">";
	}
	
	
	
	
}
