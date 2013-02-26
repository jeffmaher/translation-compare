package jeffsbox.net.translation_compare;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PoFileParser {
	
	private static Pattern COMMENT_LINE_PATTERN = Pattern.compile("^#");
	
	private static String KEY_MATCH_GROUP = "key"; 
	private static Pattern KEY_LINE_PATTERN = Pattern.compile("msgid \"(?<" + KEY_MATCH_GROUP + ">.*)\"");
	
	private static String VALUE_MATCH_GROUP = "value";
	private static Pattern VALUE_LINE_PATTERN = Pattern.compile("msgstr \"(?<" + VALUE_MATCH_GROUP + ">.*)\"");

	public static Map<String, String> getKeyValues(String path) throws IOException {
		return getKeyValues(Paths.get(path));
	}
	
	public static Map<String, String> getKeyValues(Path path) throws IOException {
		Map<String, String> keyValues = new HashMap<>();
		List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		
		// Assumes PO file is well formed (ID, then String)
		String lastKey = "";
		for(String line : lines) {
			Matcher commentMatcher = COMMENT_LINE_PATTERN.matcher(line);
			if(commentMatcher.matches()) {
				continue;
			}
			
			Matcher keyMatcher = KEY_LINE_PATTERN.matcher(line);
			if(keyMatcher.matches()) {
				lastKey = keyMatcher.group(KEY_MATCH_GROUP);
				continue;
			}
			
			Matcher valueMatcher = VALUE_LINE_PATTERN.matcher(line);
			if(valueMatcher.matches()) {
				String value = valueMatcher.group(VALUE_MATCH_GROUP);
				keyValues.put(lastKey, value);
			}
		}
		
		return keyValues;
	}

}
