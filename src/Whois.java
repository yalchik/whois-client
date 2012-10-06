import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Provides connection and communication to whois-server
 * @author Yalchik Ilya
 *
 */
public class Whois {
	
	/** Default port for whois protocol*/
	private final int PORT = 43;
	
	/** Socket for whois protocol */
	private Socket whoisSocket = null;
	
	/** Stream for queries */
	private PrintWriter writer = null;
	
	/** Stream for responses */
	private BufferedReader reader = null;
	
	/**
	 * Connects to whois-server
	 * @param url URL of whois-server
	 * @throws IOException
	 */
	public void connect(String url) throws IOException {
		whoisSocket = new Socket(url, PORT);
		writer = new PrintWriter(new OutputStreamWriter(whoisSocket.getOutputStream()));
		reader = new BufferedReader(new InputStreamReader(whoisSocket.getInputStream()));
	}
	
	/**
	 * Sends query to whois-server
	 * @param domain Requested domain
	 * @throws IOException
	 */
	public void query(String domain) throws IOException {
		String buffer = null;

		writer.print(domain);
		writer.print("\r\n");
		writer.flush();

		while ((buffer = reader.readLine()) != null)
			System.out.println(buffer);
	}
	
	/**
	 * Closes streams and socket
	 * @throws IOException
	 */
	public void close() throws IOException {
		writer.close();
		reader.close();
		whoisSocket.close();	
	}
	
	/**
	 * Facade for single query
	 * @param url URL of whois-server
	 * @param domain Requested domain
	 */
	public void ask(String url, String domain) {
		try {
			connect(url);
			query(domain);
			close();
		} catch (IOException e) {
			System.out.println("Connection error!");
		}
	}
}
