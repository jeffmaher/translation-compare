package jeffsbox.net.translation_compare;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class PoFileParserTest {

	Map<String,String> keyValues;
	
	static Map<String,String> correctKeyValues = new HashMap<>();
	static {
		correctKeyValues.put("A_UNIQUE", "Unique to A.po");
		correctKeyValues.put("AB_SAME", "Same in both files");
		correctKeyValues.put("AB_DIFF", "Different value in A.po");
	}
	
	@Before
	public void setup() {
		try {
			keyValues = PoFileParser.getKeyValues("src/test/resources/A.po");
		} catch (IOException e) {
			fail("Could not open file");
		}
	}

	@Test
	public void getsAllKeys() {
		assertEquals( "Incorrect number of keys captured", correctKeyValues.size(), keyValues.size());
		assertEquals( "Keys were not captured correctly", correctKeyValues.keySet(), keyValues.keySet());
	}

	@Test
	public void keyValuesAreCorrect() {
		Set<String> allKeys = new HashSet<>();
		allKeys.addAll(correctKeyValues.keySet());
		allKeys.addAll(keyValues.keySet());
		
		for(String key : allKeys) {
			assertEquals("Mismatch on key value: " + key, correctKeyValues.get(key), keyValues.get(key));
		}
	}
}
