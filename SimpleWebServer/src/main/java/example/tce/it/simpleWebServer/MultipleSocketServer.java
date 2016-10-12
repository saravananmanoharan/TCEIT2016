package example.tce.it.simpleWebServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import example.tce.it.utils.SimpleServerProperties;

public class MultipleSocketServer implements Runnable {

	private Socket connection;
	private String TimeStamp;
	private int ID;

	MultipleSocketServer(Socket s, int i) {
		this.connection = s;
		this.ID = i;
	}

	public static void main(String[] args) throws IOException {
		SimpleServerProperties serverProperties = new SimpleServerProperties();
		int port = serverProperties.getPropertyAsInteger("server.port").intValue();
		int count = 0;
		ServerSocket socket1 = null;
		try {
			socket1 = new ServerSocket(port);
			System.out.println("MultipleSocketServer Initialized @ port " + port);
			while (true) {
				Socket connection = socket1.accept();
				Runnable runnable = new MultipleSocketServer(connection, ++count);
				Thread thread = new Thread(runnable);
				thread.start();
			}
		} catch (Exception e) {
		} finally {
			socket1.close();
		}
	}

	public void run() {
		try {
			BufferedInputStream is = new BufferedInputStream(connection.getInputStream());
			InputStreamReader isr = new InputStreamReader(is);
			int character;
			StringBuffer process = new StringBuffer();
			while ((character = isr.read()) != 13) {
				process.append((char) character);
			}
			System.out.println(process);
			// need to wait 10 seconds to pretend that we're processing
			// something
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
			}
			TimeStamp = new java.util.Date().toString();
			String returnCode = "MultipleSocketServer repsonded at " + TimeStamp + (char) 13;
			BufferedOutputStream os = new BufferedOutputStream(connection.getOutputStream());
			OutputStreamWriter osw = new OutputStreamWriter(os, "US-ASCII");
			osw.write(returnCode);
			osw.flush();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				connection.close();
			} catch (IOException e) {
			}
		}
	}
}