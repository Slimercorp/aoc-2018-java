package Day4;

import java.util.Objects;

public class LogElement implements Comparable<LogElement> {
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int id;
	private Action action;

	public LogElement(int year, int month, int day, int hour, int minute, Action action, int id) {
		this(year, month, day, hour, minute, action);
		this.id = id;
	}
	
	public LogElement(int year, int month, int day, int hour, int minute, Action action) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.action = action;
		this.id = 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(action, day, hour, id, minute, month, year);
	}

	@Override
	public String toString() {
		String str =  "[" + year + "-" + month + "-" + day + " " + hour + ":" + minute + "] ";
		
		if (action.equals(Action.SHIFT)) {
			str = str + "Guard #" + id + " " + action;
		} else {
			str = str + action;
		}
		
		return str;
	}

	public int getMinute() {
		return minute;
	}

	public int getId() {
		return id;
	}

	public Action getAction() {
		return action;
	}
	
	public static int getDaysInMonth(int month) {
		switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			case 2:
				return 28;
			default:
				return 30;
		}
	}
	
	public int getDaysBeforeCurrentMonth() {
		int sum = 0;
		for (int i=0; i<this.month; i++) {
			sum += getDaysInMonth(i);
		}
		
		return sum;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogElement other = (LogElement) obj;
		return action == other.action && day == other.day && hour == other.hour && id == other.id
				&& minute == other.minute && month == other.month && year == other.year;
	}

	@Override
	public int compareTo(LogElement o) {	
		long myMinutes = this.getDaysBeforeCurrentMonth() * 24 * 60 + this.day * 24 * 60 + this.hour * 60 + this.minute; 
		long himMinutes = o.getDaysBeforeCurrentMonth() * 24 * 60 + o.day * 24 * 60 + o.hour * 60 + o.minute; 
		return (int) (myMinutes - himMinutes);
	}
	
	
}
