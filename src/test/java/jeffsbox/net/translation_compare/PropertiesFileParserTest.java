package jeffsbox.net.translation_compare;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class PropertiesFileParserTest {

	static Map<String,String> correctPoKeyValues = new HashMap<>();
	static {
		correctPoKeyValues.put("A_UNIQUE", "Unique to A.po");
		correctPoKeyValues.put("AB_SAME", "Same in both files");
		correctPoKeyValues.put("AB_DIFF", "Different value in A.po");
	}
	
	static Map<String,String> correctPropKeyValues = new HashMap<>();
	static {
		correctPropKeyValues.put("A_UNIQUE", "Unique to B.po");
		correctPropKeyValues.put("AB_SAME", "Same in both files");
		correctPropKeyValues.put("AB_DIFF", "Different value in B.po");
	}
	
	@Test
	public void poKeyValuesAreCorrect() throws IOException {
		Map<String, String> keyValues = PoFileParser.getKeyValues("src/test/resources/A.po");
		Set<String> allKeys = new HashSet<>();
		allKeys.addAll(correctPoKeyValues.keySet());
		allKeys.addAll(keyValues.keySet());
		
		for(String key : allKeys) {
			assertEquals("Mismatch on key value: " + key, correctPoKeyValues.get(key), keyValues.get(key));
		}
	}
	
	@Test
	public void propKeyValuesAreCorrect() throws IOException {
		Map<String, String> keyValues = PropertiesFileParser.getKeyValues("src/test/resources/B.properties");
		Set<String> allKeys = new HashSet<>();
		allKeys.addAll(correctPoKeyValues.keySet());
		allKeys.addAll(keyValues.keySet());
		
		for(String key : allKeys) {
			assertEquals("Mismatch on key value: " + key, correctPropKeyValues.get(key), keyValues.get(key));
		}
	}
}
