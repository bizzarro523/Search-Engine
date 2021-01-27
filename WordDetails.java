import java.util.ArrayList;
import java.util.HashMap;

public class WordDetails {
	String word;
	int frequency;
	ArrayList<String> docNames = new ArrayList <String>();
	HashMap<String, ArrayList<Integer>> wordHash = new HashMap<String, ArrayList<Integer>>();
	HashMap<wordHash, String> snipHash = new HashMap<wordHash, String>();
	WordDetails(String theWord){
		word = theWord;
	}
}
