package sara.mine.webserver;

public class MyServer {

	public static void main(String[] args) {
		WebServer server = new WebServer();
		try {
			server.run();
		} catch (Exception E) {
			System.out.println(E);
		}
	}

}
