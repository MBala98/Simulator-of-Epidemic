package pakiet1;


import java.util.ArrayList;

public class Świat {
	private final long seed;
	private final int liczba_dni;
	private final int liczba_agentów;
	private final double prawd_spotkania;
	private final double prawd_towarzyski;
	private final double prawd_zarażenia;
	private final double prawd_wyzdrowienia;
	private final double śmiertelność;
	private final int śr_znajomych;
	private ArrayList<Agent> lista_agentów;
	private ArrayList<Dzień> lista_dni;
	private Losowość los;
	
	private ArrayList<Agent> utwórzAgentów() { // Tworzenie agentów i jednoczesne losowanie towarzyskości
		ArrayList<Agent> lista_agentów = new ArrayList<Agent>(liczba_agentów);
		for (int i = 1; i <= liczba_agentów; i++) {
			if (los.towarzyski(prawd_towarzyski))
				lista_agentów.add(new AgentTowarzyski(i));
			else
				lista_agentów.add(new Agent(i));
		}
		return lista_agentów;
	}
	
	private void losujZnajomości() {
		assert lista_agentów.size() == liczba_agentów;
		int ilość_krawędzi = śr_znajomych*liczba_agentów/2;
		int numer_agenta1, numer_agenta2;
		int i = 1;
		while (i <= ilość_krawędzi) {
			numer_agenta1 = los.losujInt(liczba_agentów);
			numer_agenta2 = los.losujIntBezX(liczba_agentów, numer_agenta1);
			Agent agent1 = lista_agentów.get(numer_agenta1);
			Agent agent2 = lista_agentów.get(numer_agenta2);
			if (!agent1.czyMaTegoZnajomego(agent2)) { // Jeśli nie ma tego znajomego to dodajemy wzajemnie agentów jako
			agent1.dodajZnajomego(agent2);			  // znajomych i zwiekszamy i. Jeśli ci agenci juz sa swoimi 
			agent2.dodajZnajomego(agent1);			  // znajomymi, to zaczynamy petle od nowa bez zwiekszania i.
			i++;
			}
		}
	}
	
	private ArrayList<Dzień> utwórzDni() {
		ArrayList<Dzień> lista_dni = new ArrayList<Dzień>(liczba_dni);
		for (int i = 1; i <= liczba_dni; i++)
			lista_dni.add(new Dzień(i));
		return lista_dni;
	}
	
	public Świat(Własności dane, Losowość los) { 
		this.los = los;
		this.seed = dane.dajSeed();
		this.los.ustawSeed(seed);
		this.liczba_dni = dane.dajLiczbeDni();
		this.liczba_agentów = dane.dajLiczbeAgentów();
		this.prawd_spotkania = dane.dajPrawdSpotkania();
		this.prawd_towarzyski = dane.dajPrawdTowarzyski();
		this.prawd_wyzdrowienia = dane.dajPrawdWyzdrowienia();
		this.prawd_zarażenia = dane.dajPrawdZarażenia();
		this.śmiertelność = dane.dajŚmiertelność();
		this.śr_znajomych = dane.dajŚrZnajomych();
		this.lista_agentów = utwórzAgentów();
		losujZnajomości();
		this.lista_dni = utwórzDni();
		zarażPrzypadkowegoAgenta();
	}
	
	// Metoda do zarażenia przypadkowego agenta na poczatku symulacji
	public void zarażPrzypadkowegoAgenta() {
		lista_agentów.get(los.losujInt(liczba_agentów)).zachoruj();
	}
	
	public ArrayList<Agent> dajListeAgentów() {
		return this.lista_agentów;
	}
	
	public ArrayList<Dzień> dajListeDni() {
		return this.lista_dni;
	}
	
	
	public long dajSeed() {
		return this.seed;
	}
	
	public int dajLiczbeAgentów() {
		return this.liczba_agentów;
	}
	
	public double dajPrawdTowarzyski() {
		return this.prawd_towarzyski;
	}
	
	public double dajPrawdSpotkania() {
		return this.prawd_spotkania;
	}
	
	public double dajPrawdZarażenia() {
		return this.prawd_zarażenia;
	}
	
	public double dajPrawdWyzdrowienia() {
		return this.prawd_wyzdrowienia;
	}
	
	public double dajŚmiertelność() {
		return this.śmiertelność;
	}
	
	public int dajLiczbeDni() {
		return this.liczba_dni;
	}
	
	public int dajŚrZnajomych() {
		return this.śr_znajomych;
	}
	
	
	// Metoda na zaczęcie dnia od wylosowania śmierci bądz wyzdrowienia dla każdego chorego agenta.
	public void zacznijDzień() {
		for (Agent x : lista_agentów) {
			x.wylosujCzyUmrze(los, śmiertelność, prawd_wyzdrowienia);
		}
	}
	
	public void ustalSpotkania(int aktualny_dzień) {
		for (Agent x : lista_agentów) {
			x.losujSpotkania(lista_dni, los, aktualny_dzień, prawd_spotkania);
		}
			
	}
	
	public void spotykaj(int aktualny_dzień) {
		for (Spotkanie x : lista_dni.get(aktualny_dzień).dajListeSpotkań()) {
			x.realizujSpotkanie(los, prawd_zarażenia);
		}
	}
	
	public void symuluj() {
		for (int i = 1; i <= liczba_dni; i++) {
			zacznijDzień();
			ustalSpotkania(i);
			spotykaj(i-1);
			lista_dni.get(i-1).przeliczLudzi(lista_agentów);
		}
	}
	
	
	
	
	public void show(Agent agent) {
		int i = 0;
		for (Agent x : agent.dajZnajomych()) {
			System.out.println(x.dajID());
			i++;
		}
		System.out.println("Suma " + i);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
