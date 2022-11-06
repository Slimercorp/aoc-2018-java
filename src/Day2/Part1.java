package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Part1 {

	public static void main(String[] args) {
		File file = new File(".\\src\\Day2\\data.txt");
		Scanner scan;
		
		try {
			 scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File is not found");
			return;
		}
		
		List<String> data = new ArrayList<>();
		
		while (scan.hasNextLine()) {
			data.add(scan.nextLine());
		}
		
		scan.close();
		
		getAnswer(data);
	}

	
	public static void getAnswer(List<String> data) {
		int count2 = 0;
		int count3 = 0;
		for (int i=0; i<data.size(); i++) {
			String str = data.get(i);
			
			char[] charArr = str.toCharArray();
			
			// count characters
			HashMap<Character, Integer> map = new HashMap<>();
			for (int j=0; j<charArr.length; j++) {
				if (map.get(charArr[j]) == null) {
					map.put(charArr[j], 1);
				} else {
					int value = map.get(charArr[j]);
					map.put(charArr[j], value + 1);
				}
			}
			
			//check three
			for (int j=0; j<charArr.length; j++) {
				int value = map.get(charArr[j]);
				if (value == 3) {
					count3++;
					break;
				}
			}
			
			//check two
			for (int j=0; j<charArr.length; j++) {
				int value = map.get(charArr[j]);
				if (value == 2) {
					count2++;
					break;
				}
			}
			
		}
		
		System.out.println(count2 * count3);
	}
}
