package example.tce.it.cloud.fileUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyFileReader {
	String fileName = new String();

	public MyFileReader(String fileName) {
		super();
		this.fileName = fileName;
	}

	public String readFileContent() {
		BufferedReader br = null;
		StringBuffer stringBuffer = new StringBuffer();
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(fileName));

			while ((sCurrentLine = br.readLine()) != null) {
				stringBuffer.append(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return stringBuffer.toString();
	}
}
