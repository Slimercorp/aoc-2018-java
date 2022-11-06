package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Part2 {

	public static void main(String[] args) {
		File file = new File(".\\src\\Day1\\data.txt");
		Scanner scan;
		
		try {
			 scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File is not found");
			return;
		}
		
		List<Integer> data = new ArrayList<>();
		
		while (scan.hasNextInt()) {
			data.add(scan.nextInt());
		}
		
		System.out.println(getAnswer(data));
		
		scan.close();
	}

	
	public static int getAnswer(List<Integer> data) {
		int freq = 0;
		HashSet<Integer> set = new HashSet<>();
		
		while (true) {
			for (int i=0; i<data.size(); i++) {
				freq += data.get(i);
				
				if (set.contains(freq)) {
					return freq;
				}
				
				set.add(freq);
			}
		}
	}
}
