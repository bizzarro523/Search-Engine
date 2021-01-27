import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.*; 


public class SearchEngine {
	public static void main(String[] args) throws IOException{
		BufferedWriter invIndFile = null;
		File PathOfDir = null;
		String stopListPath = null;
		FileReader QueryFile = null;
		BufferedWriter ResultsFile = null;
		boolean porterSwitch = false;
		BufferedWriter porterInvIndFile = null;

		//Part 4
		if(args.length > 0)
			for(int i = 0; i < args.length; i++) {
				if(args[i].equals("-CorpusDir")) {
					System.out.println("Corpus Directory: " + args[i+1]);
					PathOfDir = new File(args[i+1]);
				}

				else if(args[i].equals("-StopList")) {
					System.out.println("Stop List Directory: " + args[i+1]);
					stopListPath = args[i+1];
				}

				else if(args[i].equals("-InvertedIndex")) {
					System.out.println("Inverted Index Directory: " + args[i+1]);
					invIndFile = new BufferedWriter(new FileWriter(args[i+1])); 
				}

				else if(args[i].equals("-QueryFile")) {
					System.out.println("Queries Directory: " + args[i+1]);
					QueryFile = new FileReader(args[i+1]);
				}

				else if(args[i].equals("-Results")) {
					System.out.println("Results Directory: " + args[i+1]);
					ResultsFile = new BufferedWriter(new FileWriter((args[i+1])));
				}
				else if(args[i].equals("-Porters")) {
					System.out.println("Porter's Algorithm Switch: " + args[i+1] + "\nPorter's Directory: " + args[i+2]);
					if (args[i+1].equals("true")){
						porterSwitch = true;
						porterInvIndFile = new BufferedWriter(new FileWriter((args[i+2])));
					}
					else{
						porterSwitch = false;
						porterInvIndFile = new BufferedWriter(new FileWriter((args[i+2])));
					}
				}
			}

		//Part 2 StopList
		Scanner stopListFile = new Scanner(new FileReader(stopListPath));
		stopList theStopList = new stopList(stopListFile);
		stopListFile.close();

		//Part 3 InvertedIndex
		InvertedIndex invertedIndex = new InvertedIndex(PathOfDir, theStopList);
		for (Map.Entry mapElement : invertedIndex.newInver.entrySet()) { 
			String key = (String)mapElement.getKey();
			WordDetails value = ((WordDetails)mapElement.getValue()); 
			for(int i = 0; i < value.docNames.size();i++) {
				if(!(key.equals("")))
					invIndFile.write("Word:" + key + " Document: " + value.docNames.get(i) + " Index: " + value.wordHash.get(value.docNames.get(i)) + "\n"); 

				if(porterSwitch == true){
					char[] word = new char[key.length()];
					Stemmer s = new Stemmer();
					for (int k = 0; k < key.length(); k++) {

						word[k] = key.charAt(k);
						s.add(word[k]);

					}
					s.stem();
					{
						String stemWord;
						stemWord = s.toString();
						porterInvIndFile.write("Stemmed Word:" + stemWord + " Document: " + value.docNames.get(i) + " Index: " + value.wordHash.get(value.docNames.get(i)) + "\n");
					}
				}
			}
		}
		if(porterSwitch == false)
			porterInvIndFile.write("Porter's Algorithm Switch is turned off.");

		Scanner queryFileScan = new Scanner(QueryFile);
		while(queryFileScan.hasNext()) {
			String queryString = queryFileScan.next();
			String wordToSearch = queryFileScan.next();
			query.searchForWord(wordToSearch, ResultsFile, porterSwitch);
		}
		ResultsFile.close();
		invIndFile.close();
		porterInvIndFile.close();
		stopListFile.close();
		queryFileScan.close();
	} 
}