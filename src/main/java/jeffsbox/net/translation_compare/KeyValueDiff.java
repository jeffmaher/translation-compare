package jeffsbox.net.translation_compare;

public class KeyValueDiff {

	private String key;
	private String valueA;
	private String valueB;
	
	public KeyValueDiff(String key, String valueA, String valueB) {
		this.key = key;
		this.valueA = valueA;
		this.valueB = valueB;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValueA() {
		return valueA;
	}
	public void setValueA(String valueA) {
		this.valueA = valueA;
	}
	public String getValueB() {
		return valueB;
	}
	public void setValueB(String valueB) {
		this.valueB = valueB;
	}
}
