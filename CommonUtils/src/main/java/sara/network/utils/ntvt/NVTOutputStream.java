package sara.network.utils.ntvt;

import java.io.OutputStream;
import java.io.PrintStream;

/*	NETWORK VIRTUAL TERMINAL	OUTPUT STREAM	*/

public class NVTOutputStream extends PrintStream {
	int IAC = 255;
	byte CR = 13;
	byte LF = 10;

	public NVTOutputStream(OutputStream outStream) {
		super(outStream);
	}

	public void write(int i) {
		if (i == IAC)
			super.write(i);
		super.write(i);
	}

	public void println(String s) {
		super.print(s);
		super.write(CR);
		super.write(LF);
		super.flush();
	}
}
