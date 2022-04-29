package it.prova.brain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import it.prova.brain.language.Parole;
import it.prova.brain.util.MyUtils;

public class Test {
	public static void main(String[] args) {
		/*
		// pongo il resultSignal a stringa vuota
		Response.resultSignal = "";
		
		int numeroNeuroni = 100;
		int numeroSinapsi = 1000;
		
		
		// creo un cervello con 1000 neuroni tattili e 100000 sinapsi di capacità media 0.7
		// tempo di percezione medio : 291.5 ms
		// tempo di creazione : 15-20 secondi
		Brain brain = BrainFactory.getBrain(numeroNeuroni, numeroSinapsi, 0.7, false);
		
		Neuron receptor = brain.getReceptor();
		if (receptor == null) 
			throw new RuntimeException("Non esiste alcun neurone recettore!");
		
		// suddivido le operazioni in due thread distinti: uno ascolta, l'altro esegue la trasmissione del segnale
		MyThread t1 = new MyThread("0");
		MyThread t2 = new MyThread("1");
		
		t1.makeInjection(brain, receptor);
		t2.makeInjection(brain, receptor);
		
		System.out.println("Sistema cerebrale completo\nAttivazione in corso...");
		
		t1.start();
		t2.start();
		*/
		
//		Parole.findAll();
//		
//		Parole.insert("ciao");
//		Parole.insert("io");
//		Parole.insert("sono");
//		Parole.insert("Ultron");
//		
//		System.out.println(Parole.findByParola("sono"));
		

		// fase di apprendimento
		BufferedReader reader;
		String input = null;
		List<String> tokens = new ArrayList<>();
		String percorso;
		int codice = 0;
		String risposta;
		
		while (true) {
			percorso = "";
			System.out.print(">>> ");
			try {
				reader = new BufferedReader(new InputStreamReader(System.in));

				// Reading data using readLine
				input = reader.readLine();
				
				
				
				/*
				if (input.equals("list")) {
					Parole.findAllVerbi();
					continue;
				}
				*/
				
				if (input.equals("update")) {
					//input = MyUtils.readFromFile("C:\\Users\\Key Biz\\Desktop\\words.txt");
					input = MyUtils.removeTrashFromFile("C:\\Users\\Key Biz\\Desktop\\verbi.txt");
					
					tokens = MyUtils.token(input);
					
					for (String parola : tokens) {
						Parole.insert(parola);
						//System.out.println(parola);
					}
				}
				
				// genero un codice
				tokens = MyUtils.token(input);
				List<String> paroleNonTrovate = new ArrayList<>();
				for (String parola : tokens) {
					//System.out.println(parola);
					
					// cerco la parola tra le parole
					codice = Parole.findCodiceByParola(parola);
					if (codice != 0) {
						percorso += String.valueOf(codice) + ".";
						continue;
					}
					
					// se sono qui non l'ho trovata, la cerco tra i verbi
					codice = Parole.findCodiceByVerbo(parola);
					if (codice != 0) {
						percorso += "v" + String.valueOf(codice) + ".";
					} else {
						// la parola non esiste nel DB
						
						// chiedo se è un verbo e eventualmente lo inserisco come coniugazione
						System.out.print("'" + parola + "'" + " è un verbo? >>> ");
						reader = new BufferedReader(new InputStreamReader(System.in));
						risposta = reader.readLine();
						
						if (!MyUtils.isAffermazione(risposta)) {
							System.out.println("vuoi che aggiungo questo vocabolo alle parole? >>> ");
							reader = new BufferedReader(new InputStreamReader(System.in));
							risposta = reader.readLine();
							
							if (MyUtils.isAffermazione(risposta)) {
								Parole.insertParola(parola);
								percorso += Parole.findCodiceByParola(parola) + ".";
							}
						} else {
							System.out.print("puoi dirmi il verbo all'infinito? >>> ");
							reader = new BufferedReader(new InputStreamReader(System.in));
							risposta = reader.readLine();
							
							codice = Parole.findVerbo(risposta);
							if (codice != 0) {
								Parole.insertConiugazione(parola, codice);
								percorso += "v" + String.valueOf(codice) + ".";
							} else {
								percorso += "?.";
							}
						}
						
					}
					
					
				}
				
				
				System.out.println(percorso);
				
				for (String parola : paroleNonTrovate) {
					
					
				}
				paroleNonTrovate.clear();
				
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}

		
	}

}

/*
// volevo solo provare a usare le reflection
try {
	Method getIdMethod = Neuron.class.getMethod("getId");
	
	
	
	Integer result = (Integer) getIdMethod.invoke(new Neuron());
	
	System.out.println(result);
} catch (Exception e) {
	e.printStackTrace();
}
*/



