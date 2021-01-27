import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Scanner;
import java.util.Map;
import java.util.Map.Entry;

public class InvertedIndex {
	static HashMap<String, WordDetails> newInver = new HashMap<String, WordDetails>();
	static File[] listOfFiles;
	BufferedWriter outFile;

	InvertedIndex(File corpusDir, stopList theStopList) throws IOException{
		listOfFiles = corpusDir.listFiles();
		for(int a = 0; a < 200; a++){
			StringBuilder html = new StringBuilder();
			FileReader fr = new FileReader(listOfFiles[a]);
			try {
				BufferedReader br = new BufferedReader(fr);

				String val = "";
				while((val=br.readLine())  != null){
					if(val.startsWith("href") || val.startsWith("li{") || val.startsWith("#"))
						continue;
					else
						html.append(val); 

				}
				br.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}

			String result = html.toString().replaceAll("<.*?>", " ");
			String[] words = new String[0];
			words = result.split(" ");

			// each individual word has been put in array at this point

			for(int i = 0; i < words.length;i++) {
				String key = words[i];
				boolean stop = false;

				//implement stop words
				for (Map.Entry mapElement : theStopList.stopWords.entrySet()){ 
					String word = (String)mapElement.getValue();
					if(key.equalsIgnoreCase(word)){
						stop = true;
						break;
					}
				}

				if(stop){ //if the word is in the stop list move on to next word
					continue;
				}
				else if(stop == false){ // if not a stop word add to InvertedIndex
					if(newInver.isEmpty() || !newInver.containsKey(key)) {  // if this is first time seeing word
						WordDetails newWord = new WordDetails(key);
						newWord.frequency++;
						newWord.docNames.add(listOfFiles[a].getName());
						ArrayList<Integer> index = new ArrayList<Integer>();
						index.add(i);
						newWord.wordHash.put(listOfFiles[a].getName(), index);
						newInver.put(key, newWord);
					}
					else {	// if word is already in inverted index hashmap (update)
						newInver.get(key).frequency++;
						if(!newInver.get(key).docNames.contains(listOfFiles[a].getName())){
							newInver.get(key).docNames.add(listOfFiles[a].getName());
						}
						if(!newInver.get(key).wordHash.containsKey(listOfFiles[a].getName())) {
							ArrayList<Integer> index = new ArrayList<Integer>();
							index.add(i);
							newInver.get(key).wordHash.put(listOfFiles[a].getName(), index);
						}
						else{
							newInver.get(key).wordHash.get(listOfFiles[a].getName()).add(i);
						}
						newInver.get(key).wordHash.replace(listOfFiles[a].getName(), newInver.get(key).wordHash.get(listOfFiles[a].getName()));
						newInver.replace(key, newInver.get(key));	
					}
				}
			}
		}
	}
}