package jeffsbox.net.translation_compare;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class TranslationComparerTest {

	static Map<String,String> aKeyValues = new HashMap<>();
	static {
		aKeyValues.put("A_UNIQUE", "Unique to A.po");
		aKeyValues.put("AB_SAME", "Same in both files");
		aKeyValues.put("AB_DIFF", "Different value in A.po");
	}
	
	static Map<String,String> bKeyValues = new HashMap<>();
	static {
		bKeyValues.put("B_UNIQUE", "Unique to B.po");
		bKeyValues.put("AB_SAME", "Same in both files");
		bKeyValues.put("AB_DIFF", "Different value in B.po");
	}
	
	TranslationComparer comparer = new TranslationComparer(aKeyValues, bKeyValues);
	
	@Test
	public void correctlyFindsUniqueKeysInA() {
		Set<String> correctKeys = new HashSet<>(Arrays.asList(new String[]{"A_UNIQUE"}));
		Set<String> keysFound = comparer.getUniqueKeysToA();
		assertEquals("Incorrect set of unique keys for A", correctKeys, keysFound);
	}
	
	@Test
	public void correctlyFindsUniqueKeysInB() {
		Set<String> correctKeys = new HashSet<>(Arrays.asList(new String[]{"B_UNIQUE"}));
		Set<String> keysFound = comparer.getUniqueKeysToB();
		assertEquals("Incorrect set of unique keys for B", correctKeys, keysFound);
	}
	
	@Test
	public void correctlyFindsCommonKeys() {
		Set<String> correctKeys = new HashSet<>(Arrays.asList(new String[]{
				"AB_SAME", "AB_DIFF"
				}));
		Set<String> keys = comparer.getCommonKeys();
		assertEquals("Incorrect set of common keys", correctKeys, keys);
	}
	
	@Test
	public void correctlyDiffsCommonKeys() {
		Set<KeyValueDiff> correctDiffs = new HashSet<>( Arrays.asList(new KeyValueDiff[] {
				new KeyValueDiff("AB_DIFF", aKeyValues.get("AB_DIFF"), bKeyValues.get("AB_DIFF"))
		}));
		Set<KeyValueDiff> diffs = comparer.getCommonKeyValueDiffs();
		assertEquals("Different diffs found", correctDiffs, diffs);
	}

}
