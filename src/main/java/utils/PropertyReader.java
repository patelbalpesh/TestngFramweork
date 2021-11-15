package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PropertyReader {

	protected final Logger logger = LogManager.getLogger(this.getClass());
	private Properties prop = new Properties();

	public PropertyReader() {    	    	 
		try (InputStream in = getClass().getResourceAsStream("/"+ Constants.PROPERTIES_NAME)){
			try {
				logger.info("Attempting .properties file load.");
				prop.load(in);
			} catch (FileNotFoundException e) {
				logger.error(Constants.PROPERTIES_NAME + " Property file not found", e);
			} 

		} catch (IOException e) {
			logger.error("Error reading file " + Constants.PROPERTIES_NAME, e);
		}
	}

	public String getString(String propertyName) {
		return prop.getProperty(propertyName);
	}

	public Integer getInt(String propertyName) {
		int temp = -1;

		try {
			temp = Integer.parseInt(prop.getProperty(propertyName));
		} catch (NumberFormatException e) {
			logger.error("The property named: " + propertyName + " cannot be parsed to an Int.");
		}
		return temp;
	}
}