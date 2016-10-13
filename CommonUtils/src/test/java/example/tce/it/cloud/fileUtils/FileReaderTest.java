package example.tce.it.cloud.fileUtils;

import junit.framework.TestCase;

public class FileReaderTest extends TestCase {
	MyFileReader fileReader ;
	protected void setUp() throws Exception {
		super.setUp();
		fileReader = new MyFileReader("resources/test.html");
	}

	public void testReadFileContent() {
		assertEquals("test", fileReader.readFileContent());
	}

}
