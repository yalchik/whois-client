package by.iyalchyk;

import org.apache.commons.validator.routines.DomainValidator;

import java.io.IOException;

public class Run {

    private static final DomainValidator domainValidator = DomainValidator.getInstance(true);

    /**
     * Entry point
     * @param args arguments
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println(String.format("Error: %s\nFormat: %s\nExample: %s",
                    "Wrong input",
                    "java -cp [classpath] by.iyalchyk.Run [whois-server] [domain_request]",
                    "java -cp target/WhoisClient-1.0-SNAPSHOT.jar by.iyalchyk.Run whois.nic.ru yandex.ru"
            ));
            System.exit(1);
        }

        String serverDomain = args[0];
        if (!domainValidator.isValid(serverDomain)) {
            System.out.println("Error: whois-server domain is incorrect. Please make sure you do not specify a protocol (e.g. http://server.com). Specify just the domain (e.g. server.com");
            System.exit(2);
        }

        String requestDomain = args[1];
        if (!domainValidator.isValid(requestDomain)) {
            System.out.println("Error: request domain is incorrect. Please make sure you do not specify a protocol (e.g. http://server.com). Specify just the domain (e.g. server.com");
            System.exit(3);
        }

        try {
            String whoisResponse = WhoisClient.askOneTime(serverDomain, requestDomain);
            System.out.println(whoisResponse);
        } catch (IOException e) {
            System.out.println("Error: network connection issues");
            System.exit(4);
        }
    }

}
