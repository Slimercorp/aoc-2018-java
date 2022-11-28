package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Part1 {

	public static void main(String[] args) throws IOException {	
		String path = ".\\src\\Day3\\data.txt";
		
		final String[] arr = Files.readString(Path.of(path)).lines().toArray(String[]::new);
		
		Map<String, Integer> map = new HashMap<>();
		for (String s : arr) {
		    final String regex = "^#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)$";
		    String str = s.replaceAll(regex, "$1");
		    final var id = Integer.parseInt(str);
		    str = s.replaceAll(regex, "$2");
		    final var x = Integer.parseInt(str);
		    str = s.replaceAll(regex, "$3");
		    final var y = Integer.parseInt(str);
		    str = s.replaceAll(regex, "$4");
		    final var width = Integer.parseInt(str);
		    str = s.replaceAll(regex, "$5");
		    final var height = Integer.parseInt(str);
		  		    
		    for (int i = y; i < (y + height); i++) {
		    	for (int j = x; j < (x + width); j++) {
		    		String coord = i + ";" + j;
		    		if (map.containsKey(coord)) {
			    		map.put(coord, 0);
		    		} else {
			    		map.put(coord, id);
		    		}
		    	}
		    }
		    
		    System.out.println(id);
		}
		
		Set<String> set = map.keySet();
		int counter = 0;
		for (String s : set) {
			if (map.get(s) == 0) {
				counter++;
			}
		}
		
		System.out.println(counter);
	}
}
