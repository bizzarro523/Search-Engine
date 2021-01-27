# Search-Engine
README
=======

Compile the program with the following command:

javac SearchEngine.java

Then run the program with the following command:

java SearchEngine -CorpusDir PathOfDir -StopList NameOfStopListFile -InvertedIndex NameOfIIndexFile -Queries QueryFile -Results ResultsFile -Porters PorterSwitch NameOfPorterIndexFile -Snippet DesiredSnippetLength

Where any argument beginning with "-" is a flag and the argument(s) following that flag are the names of important files or values needed for the program to run. (See below)

Flag:   		Argument(s):		Description:

-CorpusDir		PathOfDir 		Folder containing list of 200 HTML files
-StopList		NameOfStopListFile	Read File, single word on each line (.txt)
-InvertedIndex		NameOfIIndexFile	Write File, writes inverted index here(.txt)
-Queries		QueryFile		Read File, reads a query to be searched line by line (.txt)
-Results		ResultsFile		Write File, writes results of query here (.txt)

-Porters		PorterSwitch		Boolean, "true" to switch on Porter's Algorithm ... "false" to switch off
			NameOfPorterIndexFile	Write File, write inverted index with Porter's Algorithm applied (.txt)		

-Snippet		DesiredSnippetLength	Integer value representing fixed number of words before & after the position where the word appears

***(NOTE: "-Porters" flag takes in 2 arguments, the first argument is a string, if "true" is passed as this argument then Porter's Algorithm is switched on. If "false" or anything else is passed, then Porter's Algorithm is switched off. The second argument is a .txt file to write the PortersInvertedIndex using Porter's Algorithm if switched on.) ***
	  
