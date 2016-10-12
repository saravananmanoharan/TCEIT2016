package example.tce.it.utils;

import example.tce.it.cloud.properties.PropertiesLoader;

public class SimpleServerProperties extends PropertiesLoader {
	public SimpleServerProperties() {
		super("resources/SimpleServerProperties.properties");
	}
}
