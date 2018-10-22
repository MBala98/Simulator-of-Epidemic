package pakiet1;

import java.util.ArrayList;
import java.util.Random;

/* Klasa realizująca prawdopodobieństwo dla każdego zdarzenia z naszej symulacji.
 * Im większy parametr mnożnik tym większa poprawność losowania dla prawdopodobieństw mających
 * wiele miejsc po przecinku. Domyślna wartość mnożnika to 1000.
 */
public class Losowość {
	private Random los;
	private int mnożnik;
	public Losowość() {
		this.los = new Random();
		this.mnożnik = 1000;
	}
	
	public Losowość(int mnożnik) {
		this.los = new Random();
		this.mnożnik = mnożnik;
	}
	
	public void ustawSeed(long seed) {
		los.setSeed(seed);
	}
	
	/* Każda metoda będzie zwracała z odpowiednim prawdopodobieństwiem true i false w przeciwnym razie,
	 * np dla prawdZarażenia=0.1 metoda Zarażony zwróci true z szansą 10% i false z szansą 90%.
	 */
	
	public boolean towarzyski(double prawd_towarzyski) {
		int przypadkowa = los.nextInt(mnożnik);
		if (przypadkowa < prawd_towarzyski* mnożnik)
			return true;
		else return false;
	}
	
	public boolean spotkanie(boolean czy_chory, double prawd_spotkania) {
		int przypadkowa = los.nextInt(mnożnik);
		if (czy_chory) // Jeśli agent jest chory musimy zmniejszyc prawdopodobienstwo ustalenia spotkania dwukrotnie
			przypadkowa *= 2;
		if (przypadkowa < prawd_spotkania * mnożnik)
			return true;
		else return false;
	}
	
	public boolean zarażenie(double prawd_zarażenia) {
		int przypadkowa = los.nextInt(mnożnik);
		if (przypadkowa < prawd_zarażenia * mnożnik)
			return true;
		else return false;
	}
	
	public boolean śmiertelny(double śmiertelność) {
		int przypadkowa = los.nextInt(mnożnik);
		if (przypadkowa < śmiertelność* mnożnik)
			return true;
		else return false;
	}
	
	public boolean wyzdrowienie(double prawd_wyzdrowienia) {
		int przypadkowa = los.nextInt(mnożnik);
		if (przypadkowa < prawd_wyzdrowienia * mnożnik)
			return true;
		else return false;
	}
	
	public int losujDzieńSpotkania(int aktualny_dzień, int liczba_dni) {
		return (los.nextInt(liczba_dni - aktualny_dzień) + aktualny_dzień);
	}
	
	// Metoda do losowania agenta do spotkania wśród znajomych agenta "agent"
	// Losujemy agenta tak dlugo, az napotkamy agenta, który nie jest martwy.
	public Agent losujAgentaDoSpotkania(Agent agent) {
		Agent wynik = agent.dajZnajomych().get(los.nextInt(agent.dajZnajomych().size()));
		while (wynik.czyMartwy()) 
			 wynik = agent.dajZnajomych().get(los.nextInt(agent.dajZnajomych().size()));
		return wynik;
	
	}
	
	// Metoda losuje agenta do spotkania w "towarszyski" sposób, czyli wśród
	// znajomych agenta "agent" i wśród znajomych jego znajomych.
	public Agent losujAgentaDoSpotkaniaTow(Agent agent) {
		ArrayList<Agent> wszyscy_znajomi = new ArrayList<Agent>();
		for (Agent x : agent.dajZnajomych()) {
			wszyscy_znajomi.addAll(x.dajZnajomych());
		}
		wszyscy_znajomi.addAll(agent.dajZnajomych());
		Agent wynik = wszyscy_znajomi.get(los.nextInt(wszyscy_znajomi.size()));
		while (wynik.czyMartwy())
			wynik = wszyscy_znajomi.get(los.nextInt(wszyscy_znajomi.size()));
		return wynik;
	}
	
	// Losuje liczbe int z zakresu 0-zakres-1 z dodatkowym warunkiem, ze nie
	// moze ona byc rowna x.
	public int losujIntBezX(int zakres, int x) {
		if (zakres < 2) return 0;
		int wynik;
		while ((wynik = los.nextInt(zakres)) == x);
		return wynik;
	}
	
	// Funkcja używana w klasie świat do losowania krawędzi znajomości
	public int losujInt(int zakres) {
		return los.nextInt(zakres);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
