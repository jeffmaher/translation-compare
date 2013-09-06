package jeffsbox.net.translation_compare;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

public class FileParser {
	
	private static Pattern PO_FILE_EXTENSTION_PATTERN = Pattern.compile(".*\\.pot?$", Pattern.CASE_INSENSITIVE);
	private static Pattern PROPERTIES_FILE_EXTENSION_PATTERN = Pattern.compile(".*\\.properties$", Pattern.CASE_INSENSITIVE);

	public static Map<String, String> getKeyValues(String path) throws IOException {
		return getKeyValues(Paths.get(path));
	}
	
	public static Map<String, String> getKeyValues(Path path) throws IOException {
		Map<String, String> keyValues = Collections.emptyMap();
		
		if(PO_FILE_EXTENSTION_PATTERN.matcher(path.toString()).matches()) {
			keyValues = PoFileParser.getKeyValues(path);
		}
		else if(PROPERTIES_FILE_EXTENSION_PATTERN.matcher(path.toString()).matches()) {
			keyValues = PropertiesFileParser.getKeyValues(path);
		}
		
		return keyValues;
	}

}
