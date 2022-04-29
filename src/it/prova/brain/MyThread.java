package it.prova.brain;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyThread extends Thread {
	private Thread t;
	private String threadName;
	// devo (giustamente) iniettare il cervello
	private Brain brain;
	private Neuron receptor;
	
	public MyThread(String name) {
		threadName = name;
		//System.out.println("Creating " + threadName);
	}
	
	public void makeInjection(Brain brain, Neuron receptor) {
		this.brain = brain;
		this.receptor = receptor;
	}	
	
	public void start() {
		//System.out.println("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
	
	public void run() {
		if (Integer.valueOf(threadName) == 0) { 
			System.out.println("\n\n\n\nCiao, sono sveglio. Dimmi");
			int sizeOfSignal;
			while (true) {
				
				
				
				if (!Signal.SIGNAL.equals("")) {
					
					sizeOfSignal = Signal.SIGNAL.length();
					
					for (int i = 0; i < Signal.SIGNAL.length(); i++) {
						// innesto il meccanismo di percezione
						receptor.trasmit(String.valueOf(Signal.SIGNAL.charAt(i)));

						// a questo punto genero il ricordo
						Response.createRemembrance(brain.getSinapsi());
						
						// resetto la life delle sinapsi
						brain.resetLife();

					}
					
					
					
					Response.saveRemembrance();
					
					
					
					
					
					int memoryOccupied = Response.memory.size();
					int i=0;
					while (i<sizeOfSignal) {
						System.out.println(Response.memory.get(memoryOccupied - 1)[i] + " ");
						i++;
					}
					System.out.println("\n\n");
					

					//System.out.println("ho finito");
				}
				
				try {
					Thread.sleep(1500);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		} else {
			
			while (true) {
				//System.out.println("Enter Input : ");
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

					// Reading data using readLine
					Signal.SIGNAL = reader.readLine();
					
					System.out.println(Signal.SIGNAL);
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
			
		}
	}
	
	
}
