package utility;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfigurator {

	public Properties getProperties() {
		Properties frameworkProperties;
		FileReader reader = null;
		try {
			reader = new FileReader("src/main/resource/Config.properties");
			frameworkProperties = new Properties();
			frameworkProperties.load(reader);
			return frameworkProperties;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
}
