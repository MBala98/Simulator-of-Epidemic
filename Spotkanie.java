package pakiet1;

public class Spotkanie {

	private Agent agent1;
	private Agent agent2;
	
	Spotkanie(Agent agent1, Agent agent2) {
		this.agent1 = agent1;
		this.agent2 = agent2;
	}
	
	public void realizujSpotkanie(Losowość los, double prawd_zarażenia) {
		/* Działamy tylko jeśli dwóch agentów nie jest odpornych, oraz oboje sa żywi.
		 * jeśli któryś z nich jest odporny, to spotkanie nic nie zmieni.
		 */
		if (!agent1.czyOdporny() && !agent2.czyOdporny() && !agent1.czyMartwy() && !agent2.czyMartwy()) { 
			if (agent1.czyChory() && agent2.czyZdrowy()) {
				if (los.zarażenie(prawd_zarażenia))
					agent2.zachoruj();
			}
			else if (agent1.czyZdrowy() && agent2.czyChory()) {
				if (los.zarażenie(prawd_zarażenia))
					agent1.zachoruj();
			}
		}
	}
	
	// Metoda porównująca agentów według ID aby sprawdzić czy agent bierze udział w tym spotkaniu.
	public boolean czyAgentJestWTymSpotkaniu(Agent agent) {
		return agent.dajID() == agent1.dajID() || agent.dajID() == agent2.dajID();
	}
	
	
	
}
