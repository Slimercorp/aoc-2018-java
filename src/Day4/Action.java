package Day4;

public enum Action {
	SHIFT("begins shift"), WAKEUP("wakes up"), SLEEP("fall asleep");
	
	private String text;
	
	Action(String text) {
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
}
