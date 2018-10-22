package pakiet1;

import java.util.ArrayList;

public class Agent {

	public enum Stan {
		ZDROWY, CHORY, ODPORNY, MARTWY;
	}
	
	protected String typ;
	private int ID;
	private Stan stan;
	private ArrayList<Agent> znajomi;
	
	public Agent(int ID) {
		this.typ = "zwykły";
		this.ID = ID;
		this.stan = Stan.ZDROWY;
		this.znajomi = new ArrayList<Agent>();
	}
	
	public ArrayList<Agent> dajZnajomych() {
		return this.znajomi;
	}
	
	public String dajTyp() {
		return this.typ;
	}
	
	
	@Override
	public boolean equals (Object o) {
		if (o == null)
			return false;
		else if (this.getClass() != o.getClass())
			return false;
		else {
			Agent tmp = (Agent)o;
			return this.ID == tmp.ID;
		}
	}
	
	@Override
	public int hashCode() {
		return this.ID;
	}
	
	public boolean czyMaTegoZnajomego(Agent agent) {
		for (Agent x : znajomi) {
			if (x.equals(agent))
				return true;
		}
		return false;
	}
	
	public void dodajZnajomego(Agent agent) {
		znajomi.add(agent);
	}
	
	public int dajID() {
		return this.ID;
	}
	
	public boolean czyOdporny() {
		return stan == Stan.ODPORNY;
	}
	
	public boolean czyZdrowy() {
		return stan == Stan.ZDROWY;
	}
	
	public boolean czyChory() {
		return stan == Stan.CHORY;
	}
	
	public boolean czyMartwy() {
		return stan == Stan.MARTWY;
	}
	
	public void zachoruj() {
		this.stan = Stan.CHORY;
	}
	
	public void wyzdrowiej() {
		this.stan = Stan.ZDROWY;
	}
	
	public void uodpornij() {
		this.stan = Stan.ODPORNY;
	}
	
	// Zabijamy agenta, czyli usuwamy go z listy znajomych jego znajomych, oraz usuwamy wszystkie spotkania
	// w których miał wziąć udział.
	public void zabijAgenta() {
		this.stan = Stan.MARTWY;
	}
	
	// Losuje czy agent umrze, bądz wyzdrowieje i sie uodporni.
	// Z konieczności podejmuje decyzje, że najpierw losuje śmierć.
	public void wylosujCzyUmrze(Losowość los, double śmiertelność, double prawd_wyzdrowienia) {
		if (stan == Stan.CHORY) {
			if (los.śmiertelny(śmiertelność)) 
				this.zabijAgenta();
			else if (los.wyzdrowienie(prawd_wyzdrowienia))
				this.uodpornij();
		}
	}
	
	public void losujSpotkania(ArrayList<Dzień> lista_dni, Losowość los, int aktualny_dzień, double prawd_spotkania) {
		if (aktualny_dzień < lista_dni.size()) { // Nie losujemy w przypadku ostatniego dnia, gdyż agenci moga umawiac sie tylko na dni do przodu.
			while (los.spotkanie(this.czyChory(), prawd_spotkania)) 
				lista_dni.get(los.losujDzieńSpotkania(aktualny_dzień, lista_dni.size())).dodajSpotkanie(new Spotkanie(this, los.losujAgentaDoSpotkania(this)));
		}
	}

	
}
