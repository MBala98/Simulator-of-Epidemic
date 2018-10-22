package pakiet1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DaneDoWypisania {

	private Świat świat;
	private String spis_agentów;
	private PrintWriter writer;
	
	public DaneDoWypisania(Świat świat, String plik_z_raportem) throws FileNotFoundException {
		this.świat = świat;
		try {
			this.writer = new PrintWriter(plik_z_raportem);
		}
		catch (FileNotFoundException e) {
			System.out.println("Niedozwolona wartość " + plik_z_raportem + " dla klucza PlikZRaportem");
			System.exit(10);
		}
	}
	
	private String wypiszDaneSymulacji() {
		String wynik = "# twoje wyniki powinny zawierać te komentarze\n";
		wynik += "seed=" + świat.dajSeed() + "\n";
		wynik += "liczbaAgentów=" + świat.dajLiczbeAgentów() + "\n";
		wynik += "prawdTowarzyski=" + świat.dajPrawdTowarzyski() + "\n";
		wynik += "prawdSpotkania=" + świat.dajPrawdSpotkania() + "\n";
		wynik += "prawdZarażenia=" + świat.dajPrawdZarażenia() + "\n";
		wynik += "prawdWyzdrowienia=" + świat.dajPrawdWyzdrowienia() + "\n";
		wynik += "śmiertelność=" + świat.dajŚmiertelność() + "\n";
		wynik += "liczbaDni=" + świat.dajLiczbeDni() + "\n";
		wynik += "śrZnajomych=" + świat.dajŚrZnajomych() + "\n";
		wynik += "\n";
		return wynik;
	}
	
	private String wypiszAgentów(ArrayList<Agent> lista_agentów) {
		String wynik = "# agenci jako: id typ lub id* typ dla chorego\n";
		String typ;
		for (int i = 1; i <= lista_agentów.size(); i++) {
			typ = lista_agentów.get(i-1).dajTyp();
			if (lista_agentów.get(i-1).czyChory())
				wynik += i + "* " + typ + "\n";
			else 
				wynik += i + " " + typ + "\n";
		}
		wynik += "\n";
		return wynik;
	}
	
	public void ustalSpisAgentów(ArrayList<Agent> lista_agentów) {
		this.spis_agentów = wypiszAgentów(lista_agentów);
	}
	
	private String wypiszSieć() {
		String wynik = "# graf\n";
		ArrayList<Agent> lista_agentów = świat.dajListeAgentów();
		for (Agent x : lista_agentów) {
			wynik += x.dajID();
			for (Agent znajomy : x.dajZnajomych()) {
				wynik += " " + znajomy.dajID();
			}
			wynik += "\n";
		}
		wynik += "\n";
		return wynik;
	}
	
	private String wypiszLiczność() {
		String wynik = "# liczność w kolejnych dniach\n";
		for (Dzień x : świat.dajListeDni()) {
			wynik += "zdrowi" + x.dajLiczbeZdrowych() + " chorzy" + x.dajLiczbeChorych() + " uodp" + x.dajLiczbeOdpornych() + "\n";
		}
		return wynik;
	}
	
	public String wypiszCałość() {
		return wypiszDaneSymulacji() + spis_agentów + wypiszSieć() + wypiszLiczność();
	}
	
	public void zapisz() {
		writer.println(wypiszCałość());
		writer.close();
	}
	
	public static void main(String args[]) throws FileNotFoundException, IOException, BłądDanych {
		Losowość los = new Losowość();
		Własności dane = new Własności("default.properties", "simulation-conf.xml");
		System.out.println(dane.dajPrawdSpotkania());
		Świat świat = new Świat(dane, los);
		DaneDoWypisania test = new DaneDoWypisania(świat, dane.dajPlikZRaportem());
		test.ustalSpisAgentów(świat.dajListeAgentów());
		świat.symuluj();
		test.zapisz();
	}
	
	
	
}
