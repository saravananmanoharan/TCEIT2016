package sara.network.utils.ntvt;

import java.io.IOException;

/*	NETWORK VIRTUAL TERMINAL PRINTER	*/

public class NVTPrinter extends Thread {
	NVTInputStream inStream;

	public NVTPrinter(NVTInputStream in) {
		super();
		inStream = in;
	}

	public void run() {
		boolean finished = false;
		try {
			do {
				int i = inStream.read();
				if (i == -1)
					finished = true;
				else {
					System.out.print((char) i);
					System.out.flush();
					yield();
				}
			} while (!finished);
			System.out.println("\nConnection broken.");
			System.exit(0);
		} catch (IOException ex) {
			System.out.println("NVTPrinter error");
			// System.exit(1);
		}
	}
}