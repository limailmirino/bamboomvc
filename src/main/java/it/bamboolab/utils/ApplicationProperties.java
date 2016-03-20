package it.bamboolab.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ApplicationProperties {

	private static String fileLocation = "bamboomvc.properties";
	private static Properties properties;

	public static String getProperty(String name, String defaultValue) {

        checkProperties();

		return properties.getProperty(name, defaultValue);
	}

	public static String getProperty(String name) throws Exception {

        checkProperties();

		if(properties.getProperty(name) != null) {
			return properties.getProperty(name); 
		}

		throw new Exception("Property " + name + " not found");
	}

	private static void checkProperties() {

        InputStream input = ApplicationProperties.class.getClassLoader().getResourceAsStream(fileLocation);

        if(properties == null) {

			properties = new Properties();

            try {
				properties.load(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
