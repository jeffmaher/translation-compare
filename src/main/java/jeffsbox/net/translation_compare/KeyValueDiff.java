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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((valueA == null) ? 0 : valueA.hashCode());
		result = prime * result + ((valueB == null) ? 0 : valueB.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeyValueDiff other = (KeyValueDiff) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (valueA == null) {
			if (other.valueA != null)
				return false;
		} else if (!valueA.equals(other.valueA))
			return false;
		if (valueB == null) {
			if (other.valueB != null)
				return false;
		} else if (!valueB.equals(other.valueB))
			return false;
		return true;
	}
}
