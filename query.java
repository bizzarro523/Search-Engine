import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class query
{
	static InvertedIndex invertedIndex;

	public static void searchForWord(String word, BufferedWriter outFile, boolean porter) throws IOException {	
		if(porter == false)
			for(int i = 0; i < invertedIndex.newInver.get(word).docNames.size();i++)
				for(int j = 0; j < invertedIndex.newInver.get(word).wordHash.get(invertedIndex.newInver.get(word).docNames.get(i)).size(); j++)
					outFile.write("Query: '" + word + "' found in " + invertedIndex.newInver.get(word).docNames.get(i) + " at index "+ 
							invertedIndex.newInver.get(word).wordHash.get(invertedIndex.newInver.get(word).docNames.get(i)).get(j) + "\n");

		else{
			char[] w = new char[word.length()];
			Stemmer s = new Stemmer();
			for (int k = 0; k < word.length(); k++) {
				w[k] = word.charAt(k);
				s.add(w[k]);
			}
			s.stem();
			{
				String stemWord;
				stemWord = s.toString();
				for(int i = 0; i < invertedIndex.newInver.get(stemWord).docNames.size();i++)
					for(int j = 0; j < invertedIndex.newInver.get(stemWord).wordHash.get(invertedIndex.newInver.get(stemWord).docNames.get(i)).size(); j++)
						outFile.write("Query: '" + stemWord + "' found in " + invertedIndex.newInver.get(stemWord).docNames.get(i) + " at index "+ 
								invertedIndex.newInver.get(stemWord).wordHash.get(invertedIndex.newInver.get(stemWord).docNames.get(i)).get(j) + "\n");
			}
		}
	}

public static String[] searchDoc(String docName) throws FileNotFoundException {
	StringBuilder html = new StringBuilder();
	for(int i = 0; i < InvertedIndex.listOfFiles.length; i++)
		if(InvertedIndex.listOfFiles[i].getName().equals(docName)) {
			FileReader fr = new FileReader(InvertedIndex.listOfFiles[i]);
			try {
				BufferedReader br = new BufferedReader(fr);
				String val = "";
				while((val=br.readLine())  != null){
					html.append(val);
				}
				br.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	String result = html.toString().replaceAll("/(<([^>]+)>)/", "");
	String[] words;
	words = result.split(" ");

	return words;
}
}
