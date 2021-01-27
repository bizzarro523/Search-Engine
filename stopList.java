import java.util.HashMap;
import java.util.Scanner;

public class stopList{
	HashMap<Integer, String> stopWords = new HashMap<Integer, String>();
	stopList(Scanner inputFile){
		int key = 0;
		while(inputFile.hasNext()) {
			key++;
			String word = inputFile.nextLine();
			stopWords.put(key, word);
		}
	}
}
