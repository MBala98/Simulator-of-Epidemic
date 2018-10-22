package pakiet1;

import java.util.ArrayList;

public class AgentTowarzyski extends Agent {

	public AgentTowarzyski(int ID) {
		super(ID);
		this.typ = "towarzyski";
	}
	
	@Override
	public void losujSpotkania(ArrayList<Dzień> lista_dni, Losowość los, int aktualny_dzień, double prawd_spotkania) {
		if (aktualny_dzień < lista_dni.size()) {
			while (los.spotkanie(false, prawd_spotkania)) {
				if (this.czyChory()) 
					lista_dni.get(los.losujDzieńSpotkania(aktualny_dzień, lista_dni.size())).dodajSpotkanie(new Spotkanie(this, los.losujAgentaDoSpotkania(this)));
				else
					lista_dni.get(los.losujDzieńSpotkania(aktualny_dzień, lista_dni.size())).dodajSpotkanie(new Spotkanie(this, los.losujAgentaDoSpotkaniaTow(this)));
			}
		}
	}
}
