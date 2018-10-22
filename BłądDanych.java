package pakiet1;

import java.util.Properties;
@SuppressWarnings("serial")
public class BłądDanych extends Exception {
	public final static String parametr1 = "seed";
	public final static String parametr2 = "liczbaAgentów";
	public final static String parametr3 = "prawdTowarzyski";
	public final static String parametr4 = "prawdSpotkania";
	public final static String parametr5 = "prawdZarażenia";
	public final static String parametr6 = "prawdWyzdrowienia";
	public final static String parametr7 = "śmiertelność";
	public final static String parametr8 = "liczbaDni";
	public final static String parametr9 = "śrZnajomych";
	public final static String parametr10 = "plikZRaportem";
	public final static String komunikat1 = "Brak wartości dla klucza ";
	public final static String komunikat2 = "Niedozwolona wartość ";
	public final static String komunikat3 = " dla klucza ";
	
	public BłądDanych(String komunikat) {
		super(komunikat);
	}
	
	public static void sprawdźDane(Properties dane) throws BłądDanych {
		String wartość_napis;
		double wartość;
		if ((wartość_napis = dane.getProperty(parametr1)) == null) 
			throw new BłądDanych(komunikat1 + parametr1);
		else if ((wartość = Double.parseDouble(wartość_napis)) %1 != 0)
			throw new BłądDanych(komunikat2 + wartość + komunikat3 + parametr1);
		
		else if ((wartość_napis = dane.getProperty(parametr2)) == null)
			throw new BłądDanych(komunikat1 + parametr2);
		else if ((wartość = Double.parseDouble(wartość_napis)) < 1.0 || wartość > 1000000.0)
			throw new BłądDanych(komunikat2 + wartość + komunikat3 + parametr2);
			
		else if ((wartość_napis = dane.getProperty(parametr3)) == null)
			throw new BłądDanych(komunikat1 + parametr3);
		else if ((wartość = Double.parseDouble(wartość_napis)) < 0 || wartość >= 1.0)
			throw new BłądDanych(komunikat2 + wartość + komunikat3 + parametr3);
		
		else if ((wartość_napis = dane.getProperty(parametr4)) == null)
			throw new BłądDanych(komunikat1 + parametr4);
		else if ((wartość = Double.parseDouble(wartość_napis)) < 0 || wartość >= 1.0)
			throw new BłądDanych(komunikat2 + wartość + komunikat3 + parametr4);

		else if ((wartość_napis = dane.getProperty(parametr5)) == null)
			throw new BłądDanych(komunikat1 + parametr5);
		else if ((wartość = Double.parseDouble(wartość_napis)) < 0 || wartość >= 1.0)
			throw new BłądDanych(komunikat2 + wartość + komunikat3 + parametr5);

		else if ((wartość_napis = dane.getProperty(parametr6)) == null)
			throw new BłądDanych(komunikat1 + parametr6);
		else if ((wartość = Double.parseDouble(wartość_napis)) < 0 || wartość >= 1.0)
			throw new BłądDanych(komunikat2 + wartość + komunikat3 + parametr6);
		
		else if ((wartość_napis = dane.getProperty(parametr7)) == null)
			throw new BłądDanych(komunikat1 + parametr7);
		else if ((wartość = Double.parseDouble(wartość_napis)) < 0 || wartość >= 1.0)
			throw new BłądDanych(komunikat2 + wartość + komunikat3 + parametr7);
		
		else if ((wartość_napis = dane.getProperty(parametr8)) == null)
			throw new BłądDanych(komunikat1 + parametr8);
		else if ((wartość = Double.parseDouble(wartość_napis)) < 1 || wartość > 1000.0)
			throw new BłądDanych(komunikat2 + wartość + komunikat3 + parametr8);
		
		else if ((wartość_napis = dane.getProperty(parametr9)) == null)
			throw new BłądDanych(komunikat1 + parametr9);
		else if ((wartość = Double.parseDouble(wartość_napis)) < 0 || wartość > Double.parseDouble(dane.getProperty(parametr2)))
			throw new BłądDanych(komunikat2 + wartość + komunikat3 + parametr9);
		
		else if ((wartość_napis = dane.getProperty(parametr10)) == null)
			throw new BłądDanych(komunikat1 + parametr10);
	}
}
