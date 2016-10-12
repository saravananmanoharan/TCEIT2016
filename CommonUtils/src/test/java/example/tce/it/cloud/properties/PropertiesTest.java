package example.tce.it.cloud.properties;

import example.tce.it.cloud.properties.TestProperties;
import junit.framework.TestCase;

public class PropertiesTest extends TestCase {

	public TestProperties testProperties;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		testProperties = new TestProperties();
	}

	public void testGetPropertyAsString() {
		assertEquals("Hello World", testProperties.getPropertyAsString("testValue"));
	}

}
