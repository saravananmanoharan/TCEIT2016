package sara.mine.webserver;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import example.tce.it.utils.SimpleServerProperties;
import sara.network.utils.ntvt.*;
/*	WEB SERVER */

class WebServer {
	public WebServer() {
		super();
	}

	public void run() {
		try {
			ServerSocket server = new ServerSocket(
					new SimpleServerProperties().getPropertyAsInteger("server.port").intValue());
			int localPort = server.getLocalPort();
			System.out.println("Server Started \n@ " + server);
			System.out.println("Listening in port " + localPort + ".");

			do {
				Socket client = server.accept();
				String destName = client.getInetAddress().getHostName();
				int destPort = client.getPort();
				System.out.println("ACCEPTED " + " CLIENT IP : " + (client.getInetAddress()) + " CONNECTION FROM : "
						+ destName + " ON PORT : " + destPort + ".");
				/*byte[] data = new byte[client.getInputStream().available()];
				client.getInputStream().read(data);
				for(byte value:data){
					System.out.print((char)value);
				}*/
				
				NVTOutputStream outStream = new NVTOutputStream(client.getOutputStream());
				NVTInputStream inStream = new NVTInputStream(client.getInputStream(), outStream);

				boolean finished = false;

				String inLine = inStream.readLine();
				System.out.println("Received: " + inLine);

				if (getRequest(inLine)) {
					valuateParameters(inLine);
					String fileName = getFileName(inLine);
					File file = new File("resources/" + fileName);

					if (file.exists()) {
						System.out.println(fileName + " requested file found.");

						outStream.println("HTTP/1.0 200 OK");
						outStream.println("MIME-Version: 1.0");
						outStream.println("Content-Type: text/html");

						int len = (int) file.length();
						outStream.println("Content-Length: " + len);
						outStream.println("");

						sendFile(outStream, file);
						outStream.flush();
					} else {
						outStream.println("HTTP/1.0 404 Not Found");
						String notFound = "<TITLE>Not Found</TITLE><H1>Error 404 - File Not Found</H1>";
						outStream.println("Content-Type: text/html");
						outStream.println("Content-Length: " + notFound.length() + 2);
						outStream.println("");
						outStream.println(notFound);
					}
				}
				client.close();
			} while (true);
		} catch (IOException ex) {
			System.out.println("IOException occurred.");
		}
	}

	private void valuateParameters(String inLine) {
		// GET /?Name=&Email=&Submit=Submit HTTP/1.1
		int start = inLine.indexOf("?");
		int end = inLine.indexOf(" HTTP/1.1");
		if (start != -1) {
			String parameters = inLine.substring(start + 1, end);
			for (String values : parameters.split("&")) {
				System.out.println(values);
			}
		}
	}

	boolean getRequest(String s) {
		if (s.length() > 0) {
			if (s.substring(0, 3).equalsIgnoreCase("GET"))
				return true;
			if (s.substring(0, 4).equalsIgnoreCase("POST"))
				return true;
		}

		return false;
	}

	String getFileName(String s) {
		String f = s.substring(s.indexOf(' ') + 1);
		f = f.substring(0, f.indexOf(' '));
		try {
			if (f.charAt(0) == '/')
				f = f.substring(1);
		} catch (StringIndexOutOfBoundsException ex) {

		}
		if (f.equals(""))
			f = "index.htm";
		return f;
	}

	void sendFile(NVTOutputStream out, File file) {
		try {
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			int len = (int) file.length();
			byte buffer[] = new byte[len];
			in.readFully(buffer);
			out.write(buffer, 0, len);
			in.close();
		} catch (Exception ex) {
			System.out.println("Error retrieving file.");
			// System.exit(1);
		}
	}
}