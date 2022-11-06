package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Part2 {

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
		for (int i=0; i<data.size()-1; i++) {
			String str1 = data.get(i);
			
			for (int j=i+1; j<data.size(); j++) {
				String str2 = data.get(j);
				
				int counter = 0;
				for (int k=0; k<str1.length(); k++) {
					if (str1.charAt(k) != str2.charAt(k)) {
						counter++;
					}
					
					if (counter == 2) {
						break;
					}
				}
				
				if (counter == 1) {
					System.out.println(str1);
					System.out.println(str2);
				}
			}
		}
	}
}
