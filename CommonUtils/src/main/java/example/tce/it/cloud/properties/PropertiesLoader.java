/**
 * 
 */
package example.tce.it.cloud.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Saravanan
 *
 */
public abstract class PropertiesLoader {
	private Properties prop = new Properties();
	private InputStream input = null;

	protected PropertiesLoader(String fileName) {
		try {
			input = new FileInputStream(fileName);
			// load a properties file
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Integer getPropertyAsInteger(String property) {
		return Integer.parseInt(prop.getProperty(property));
	}

	public String getPropertyAsString(String property) {
		return prop.getProperty(property);
	}

}
