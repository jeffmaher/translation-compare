package jeffsbox.net.translation_compare;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class TranslationComparer {
	
	protected Map<String,String> keyValuesA = new HashMap<>();
	protected Map<String,String> keyValuesB = new HashMap<>();
	
	protected Set<String> uniqueKeysA;
	protected Set<String> uniqueKeysB;
	protected Set<String> commonKeys;
	
	protected Set<KeyValueDiff> keyValueDiffs;
	
	public Set<String> getUniqueKeysToA() {
		return getUniqueKeys(keyValuesA.keySet(), keyValuesB.keySet(), uniqueKeysA);
	}
	
	public Set<String> getUniqueKeysToB() {
		return getUniqueKeys(keyValuesB.keySet(), keyValuesA.keySet(), uniqueKeysB);
	}
	
	public Set<String> getCommonKeys() {
		if(commonKeys != null) {
			return commonKeys;
		}
		
		commonKeys = new HashSet<>();
		commonKeys.addAll(keyValuesA.keySet());
		commonKeys.addAll(keyValuesB.keySet());
		
		commonKeys.removeAll(getUniqueKeysToA());
		commonKeys.removeAll(getUniqueKeysToB());
		
		return commonKeys;
	}
	
	private static Set<String> getUniqueKeys(Set<String> one, Set<String> two, Set<String> result) {
		if(result != null){
			return result;
		}
		
		result = new HashSet<>(one);
		result.removeAll(two);
		
		return result;		
	}
	
	public Set<KeyValueDiff> getKeyValueDiffs() {
		if(keyValueDiffs != null) {
			return keyValueDiffs;
		}
		
		keyValueDiffs = new HashSet<>();
		
		// TODO optimize with a closure if converting to Java 8 or Groovy
		for(String key : getCommonKeys()) {
			String valueA = keyValuesA.get(key);
			String valueB = keyValuesB.get(key);
			
			if(!valueA.equals(valueB)) {
				KeyValueDiff diff = new KeyValueDiff(key, valueA, valueB);
				keyValueDiffs.add(diff);
			}
		}
		
		return keyValueDiffs;
	}
	
	
}
