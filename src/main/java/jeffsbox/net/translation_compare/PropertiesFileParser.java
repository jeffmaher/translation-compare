package jeffsbox.net.translation_compare;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesFileParser {

	public static Map<String, String> getKeyValues(String path) throws IOException {
		return getKeyValues(Paths.get(path));
	}
	
	public static Map<String, String> getKeyValues(Path path) throws IOException {
		Properties propFile = new Properties();
		FileReader fileReader = new FileReader("src/test/resources/A.properties");
		propFile.load(fileReader);
		
		Set<Object> keySet = propFile.keySet(); 
		
		Map<String, String> keyValues = new HashMap<>();
		for(Object keyObj : keySet) {
			String keyStr = keyObj.toString();
			keyValues.put(keyStr, propFile.getProperty(keyStr));
		}
		
		return keyValues;
	}

}
