package pakiet1;

import java.util.ArrayList;

public class Dzień {

	private final int numer_dnia;
	private ArrayList<Spotkanie> lista_spotkań;
	private int liczba_zdrowych_na_koniec_dnia;
	private int liczba_chorych_na_koniec_dnia;
	private int liczba_odpornych_na_koniec_dnia;
	
	public Dzień(int numer_dnia) {
		this.numer_dnia = numer_dnia;
		this.lista_spotkań = new ArrayList<Spotkanie>();
	}
	
	public int dajNumerDnia() {
		return this.numer_dnia;
	}
	
	public ArrayList<Spotkanie> dajListeSpotkań() {
		return this.lista_spotkań;
	}
	
	public void dodajSpotkanie(Spotkanie spotkanie) {
		lista_spotkań.add(spotkanie);
	}
	
	public int dajLiczbeChorych() {
		return this.liczba_chorych_na_koniec_dnia;
	}
	
	public int dajLiczbeZdrowych() {
		return this.liczba_zdrowych_na_koniec_dnia;
	}
	
	public int dajLiczbeOdpornych() {
		return this.liczba_odpornych_na_koniec_dnia;
	}
	
	// Metoda do sumowania ilości ludzi chorych, zdrowych i odpornych na koniec dnia
	public void przeliczLudzi(ArrayList <Agent> lista_agentów) {
		for (Agent x : lista_agentów) {
			if (x.czyChory())
				this.liczba_chorych_na_koniec_dnia++;
			else if (x.czyOdporny())
				this.liczba_odpornych_na_koniec_dnia++;
			else if (x.czyZdrowy())
				this.liczba_zdrowych_na_koniec_dnia++;
		}
	}
}
