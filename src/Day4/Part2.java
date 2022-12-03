package Day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

	public static void main(String[] args) throws IOException {	
		String path = ".\\src\\Day4\\data.txt";
		
		final String[] arr = Files.readString(Path.of(path)).lines().toArray(String[]::new);
		
		TreeSet<LogElement> log = new TreeSet<>();
		for (String s : arr) {
		    final String regex = "\\[(\\d{4})\\-(\\d{2})\\-(\\d{2}) (\\d{2}):(\\d{2})\\]";
		    Matcher time = Pattern.compile(regex).matcher(s);
		    time.find();
		    
		    final var year = Integer.parseInt(time.group(1));
		    final var month = Integer.parseInt(time.group(2));
		    final var day = Integer.parseInt(time.group(3));
		    final var hour = Integer.parseInt(time.group(4));
		    final var minute = Integer.parseInt(time.group(5));
		    
		    final String regex2 = "Guard #(\\d+) begins shift$";
		    Matcher id = Pattern.compile(regex2).matcher(s);
		    int idGuard = -1;
		    if (id.find()) {
		    	idGuard = Integer.parseInt(id.group(1));
		    }
		    
		    if (Pattern.compile("wakes up").matcher(s).find()) {
		    	log.add(new LogElement(year, month, day, hour, minute, Action.WAKEUP));
		    } else if (Pattern.compile("falls asleep").matcher(s).find()) {
		    	log.add(new LogElement(year, month, day, hour, minute, Action.SLEEP));
		    } else {
		    	log.add(new LogElement(year, month, day, hour, minute, Action.SHIFT, idGuard));
		    }
		}
		
		log.stream().forEach(System.out::println);
		
		Map<Integer, int[]> map = new HashMap<>();
		int startSleepTime = -1;
		int guardId = -1;
		for (LogElement le : log) {
			if (le.getAction() == Action.SHIFT) {
				guardId = le.getId();
			}
			
			if (le.getAction() == Action.SLEEP) {
				startSleepTime = le.getMinute();
				continue;
			}
			
			if ((startSleepTime == -1) || (guardId == -1)) {
				continue;
			}
			
			if (le.getAction() == Action.WAKEUP) {
				if (!map.containsKey(guardId)) {
					map.put(guardId, new int[60]);
				}
				
				int[] timeTrack = map.get(guardId);
				for (int minute = startSleepTime; minute<le.getMinute(); minute++) {
					timeTrack[minute] = timeTrack[minute] + 1;
				}
				
				map.put(guardId, timeTrack);
			}
		}
		
		int maxSleepTime = Integer.MIN_VALUE;
		int maxId = -1;
		int indexMaxValue = -1;
		for (Integer guard : map.keySet()) {
			int[] timeTrack = map.get(guard);
			int maxValue = Arrays.stream(timeTrack).max().getAsInt();
			if (maxValue > maxSleepTime) {
				maxSleepTime = maxValue;
				maxId = guard;
				for (int i=0; i<timeTrack.length; i++) {
					if (timeTrack[i] == maxSleepTime) {
						indexMaxValue = i;
					}
				}
			}
		}
		
		System.out.println(maxId * indexMaxValue);
	}
	

	
}
