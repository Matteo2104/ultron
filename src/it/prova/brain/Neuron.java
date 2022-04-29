package it.prova.brain;

import java.util.ArrayList;
import java.util.List;

import it.prova.brain.util.MyUtils;

public class Neuron {
	private int id;
	//private double capacity;
	private List<Neuron> adiacenti = new ArrayList<>();
	private List<Edge> sinapsi = new ArrayList<>();
	private boolean recettore;
	
	
	
	
	
	
	//public Neuron() {}
	public Neuron(/*double capacity*/) {
		this.id = CounterId.counter;
		this.recettore = CounterId.counter==1?true:false;
		//this.capacity = capacity;
		
		CounterId.increment();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Neuron> getAdiacenti() {
		return adiacenti;
	}
	public void setAdiacenti(List<Neuron> adiacenti) {
		this.adiacenti = adiacenti;
	}
	public boolean isRecettore() {
		return recettore;
	}

	public void setRecettore(boolean recettore) {
		this.recettore = recettore;
	}
	
	public List<Edge> getSinapsi() {
		return sinapsi;
	}

	public void setSinapsi(List<Edge> sinapsi) {
		this.sinapsi = sinapsi;
	}
	
	
	
	/*
	public void setResponse(String signal) {
		// analizzo il segnale come una stringa. Il valore è un numero che va da 0 a 9 e 
		// indica l'intensità del segnale.
		
		// trasformo il segnale in numero
		try {
			double convertedSignal = Double.valueOf(signal);
			
			// una volta che il segnale è numerico lo moltiplico per la capacità del neurone
			convertedSignal *= capacity;
			
			// a questo punto riconverto in stringa il valore arrotondato
			signal = String.valueOf((int) Math.floor(convertedSignal));
			
			// assegno il valore alla Response
			Response.setSignal(signal);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	*/
	
	public String getAdjId() {
		long numeroNeuroniAdiacenti = adiacenti.size();
		String resultString = "";
		
		for (int i=0;i<numeroNeuroniAdiacenti;i++) {
			resultString += adiacenti.get(i).id + ",";
		}
		
		return resultString;
	}
	

	// Metodo di trasmissione del segnale
	public void trasmit(String signal) {
		int signal_j;
		int numeroSinapsi = this.sinapsi.size();
		
		Edge sinapsiTemp = null;
		//for (int j = 0; j < signal.length(); j++) {
			
			for (int i = 0; i < numeroSinapsi; i++) {
				sinapsiTemp = this.sinapsi.get(i);
				
				signal_j = (int) (Integer.parseInt(String.valueOf(signal)) * sinapsiTemp.getCapacity());

				// verifico che il segnale sia trasmissibile, in caso negativo vado avanti e non
				// trasmetto niente
				if (signal_j < 1)
					continue;

				// scavo il solco all'interno della sinapsi
				sinapsiTemp.setLife(sinapsiTemp.getLife() - (signal_j * 100));
				
				

				// rilascio il neuro-trasmettitore
				// Response.setSignal();

				// infine ri-trasmetto
				if (sinapsiTemp.getU().getId() == this.id) {
					sinapsiTemp.getV().trasmit(String.valueOf(signal_j));
				} else {
					sinapsiTemp.getU().trasmit(String.valueOf(signal_j));
				}
			}
			
			
		//}
		
		
		
	}

	@Override
	public String toString() {
		return "Neuron [id=" + id + ", recettore=" + recettore + "]";
	}

	
	
	
	
	
	
}
