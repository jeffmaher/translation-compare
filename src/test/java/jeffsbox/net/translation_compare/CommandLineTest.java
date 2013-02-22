package jeffsbox.net.translation_compare;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

public class CommandLineTest {
	
	public static void main(String[] args) {
		Path aPath = Paths.get("src/test/resources/A.po");
		Path bPath = Paths.get("src/test/resources/B.po");
		
		PoFileTranslationComparer poComp = new PoFileTranslationComparer(aPath, bPath);
		
		System.out.println("*** Common ***");
		printCollection(poComp.getCommonKeys());
		System.out.println();
		
		System.out.println("*** Unique to A ***");
		printCollection(poComp.getUniqueKeysToA());
		System.out.println();
		
		System.out.println("*** Unique to B ***");
		printCollection(poComp.getUniqueKeysToB());
		System.out.println();
		

		System.out.println("*** Value Diffs ***");
		printCollection(poComp.getKeyValueDiffs());
		System.out.println();
		
	}
	
	private static void printCollection(Collection c) {
		for(Object o : c){
			if(o instanceof String) {
				System.out.println(o);
			}
			else if(o instanceof KeyValueDiff){
				KeyValueDiff diff = (KeyValueDiff) o;
				System.out.println(diff.getKey());
				System.out.println("\tA: " + diff.getValueA());
				System.out.println("\tB: " + diff.getValueB());
			}
		}
	}
	
}
