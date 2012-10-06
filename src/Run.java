public class Run {

	/**
	 * Entry point
	 * @param args arguments
	 */
	public static void main(String[] args) {

		if (args.length == 2) {
			Whois whois = new Whois();
			whois.ask(args[0], args[1]);
		}
		else
			System.out.println(String.format("Error: %s\nFormat: %s\nExample: %s",
					"Wrong input",
					"java -jar [filename] [server] [domain]",
					"java -jar aipos2.jar whois.nic.ru yandex.ru"
					));
	}
}