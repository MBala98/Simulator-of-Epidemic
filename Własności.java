package pakiet1;



import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class Własności {
	private Properties własności;
	
	public Własności(String default_properties, String config) throws IOException, BłądDanych {
		this.własności = czytajWłasności(default_properties, config);
	}
	
	private Properties czytajWłasności(String default_properties, String config) throws IOException, BłądDanych {
		Properties app = new Properties();
		InputStream propertiesInputStream = null;
		try {
			propertiesInputStream = Thread.currentThread()
						.getContextClassLoader().getResourceAsStream(default_properties);
			app.load(new InputStreamReader(propertiesInputStream, Charset.forName("UTF-8")));
		}
		catch (NullPointerException e) {
			System.out.println("Brak pliku " + default_properties);
			System.exit(1);
		}
		catch (MalformedInputException e) {
			System.out.println(default_properties + " nie jest tekstowy");
			System.exit(2);
		}
		catch(IOException e) {
			System.out.println("Błąd wczytywania pliku " + default_properties);
			System.exit(3);
		}
		finally {
			propertiesInputStream.close();
		}
		app = new Properties(app);
		try {
			propertiesInputStream = Thread.currentThread()
						.getContextClassLoader().getResourceAsStream(config);
			app.loadFromXML(propertiesInputStream);
			BłądDanych.sprawdźDane(app);
		}
		catch (NullPointerException e) {
			System.out.println("Brak pliku " + config);
			System.exit(4);
		}
		catch (InvalidPropertiesFormatException e) {
			System.out.println(config + " nie jest XML");
			System.exit(5);
		}
		catch (IOException e) {
			System.out.println("Błąd wczytywania pliku " + config);
			System.exit(6);
		}
		catch (BłądDanych e) {
			System.out.println(e.getMessage());
			System.exit(7);
		}
		finally {
			propertiesInputStream.close();
		}
		return app;
	}
	
	public long dajSeed() {
		return Long.parseLong(własności.getProperty("seed"));
	}
	
	public int dajLiczbeAgentów() {
		return Integer.parseInt(własności.getProperty("liczbaAgentów"));
	}
	
	public double dajPrawdTowarzyski() {
		return Double.parseDouble(własności.getProperty("prawdTowarzyski"));
	}
	
	public double dajPrawdSpotkania() {
		return Double.parseDouble(własności.getProperty("prawdSpotkania"));
	}
	
	public double dajPrawdZarażenia() {
		return Double.parseDouble(własności.getProperty("prawdZarażenia"));
	}
	
	public double dajPrawdWyzdrowienia() {
		return Double.parseDouble(własności.getProperty("prawdWyzdrowienia"));
	}
	
	public double dajŚmiertelność() {
		return Double.parseDouble(własności.getProperty("śmiertelność"));
	}
	
	public int dajLiczbeDni() {
		return Integer.parseInt(własności.getProperty("liczbaDni"));
	}
	
	public int dajŚrZnajomych() {
		return Integer.parseInt(własności.getProperty("śrZnajomych"));
	}
	
	public String dajPlikZRaportem() {
		return własności.getProperty("plikZRaportem");
	}
	
	
}
