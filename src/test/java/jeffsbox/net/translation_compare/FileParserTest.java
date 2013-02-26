package jeffsbox.net.translation_compare;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class FileParserTest {

	
	static Map<String,String> correctKeyValues = new HashMap<>();
	static {
		correctKeyValues.put("A_UNIQUE", "Unique to A.po");
		correctKeyValues.put("AB_SAME", "Same in both files");
		correctKeyValues.put("AB_DIFF", "Different value in A.po");
	}
	
		@Test
		public void parsesPoFiles() throws IOException {
			Map<String, String> keyValues = FileParser.getKeyValues("src/test/resources/A.po");
			assertEquals("Key values are not what was expected", correctKeyValues, keyValues);
		}

		@Test
		public void parsesPropertiesFiles() throws IOException {
			Map<String, String> keyValues = FileParser.getKeyValues("src/test/resources/A.properties");
			assertEquals("Key values are not what was expected", correctKeyValues, keyValues);
		}
		
}
