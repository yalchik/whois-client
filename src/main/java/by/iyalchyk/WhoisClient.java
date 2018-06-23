package by.iyalchyk;

import org.apache.commons.text.TextStringBuilder;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This is a whois-client. It connects to a whois-server and requests information about the specified domain.
 * It uses the NICNAME/WHOIS protocol according to RFC 954.
 * @author Ilya Yalchyk
 */
public class WhoisClient {
    
    /** Default port for whois protocol */
    public final int DEFAULT_PORT = 43;

    /** Default whois-server domain */
    public final String DEFAULT_SERVER = "whois.nic.ru";

    /** Socket for whois-server connection */
    private Socket socket = null;
    
    /** Writes data to whois-server connection */
    private PrintWriter writer = null;
    
    /** Reads data from whois-server connection */
    private BufferedReader reader = null;
    
    /**
     * Connects to the given whois-server
     * @param serverDomain whois-server domain
     * @throws IOException If an I/O error occurs during the operation.
     */
    public void connect(String serverDomain) throws IOException {
        try {
            socket = new Socket(serverDomain, DEFAULT_PORT);
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            disconnect();
            throw e;
        }
    }

    /**
     * Connects to the default whois-server
     * @throws IOException If an I/O error occurs during the operation.
     */
    public void connect() throws IOException {
        connect(DEFAULT_SERVER);
    }
    
    /**
     * Sends query to the connected whois-server
     * @param domain domain to request from the whois-server
     * @throws IOException If an I/O error occurs during the operation.
     */
    public String queryText(String domain) throws IOException {
        String buffer;
        TextStringBuilder sb = new TextStringBuilder();
        writer.println(domain);

        while ((buffer = reader.readLine()) != null) {
            sb.appendln(buffer);
        }
        return sb.build();
    }
    
    /**
     * Closes streams and socket
     */
    public void disconnect() {
        silentClose(reader);
        silentClose(writer);
        silentClose(socket);
    }

    /**
     * Facade for single query
     * @param serverDomain URL of whois-server
     * @param requestDomain Requested domain
     */
    public static String askOneTime(String serverDomain, String requestDomain) throws IOException {
        WhoisClient whoisClient = new WhoisClient();
        whoisClient.connect(serverDomain);
        String whoisResponse;
        try {
            whoisResponse = whoisClient.queryText(requestDomain);
        } catch (IOException e) {
            throw e;
        } finally {
            whoisClient.disconnect();
        }
        return whoisResponse;
    }


    private void silentClose(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                // Do nothing
            }
        }
    }

    private void silentClose(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                // Do nothing
            }
        }
    }

}
