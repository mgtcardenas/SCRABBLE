import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

public class Dictionary
{
	private static final String DICTIONARY_PATH = "C:\\Users\\mgtca\\IdeaProjects\\SCRABBLE\\src\\english-dictionary.txt";
	
	/**
	 * Determines whether a word exists or not by consulting
	 * a dictionary file using binary search. The path to the dictionary
	 * must be correctly set as a constant in this source file
	 *
	 * @param  word - the word we want to know exists or not
	 * @return      - true if the word exists, false if it does not
	 */
	public static boolean wordExists(String word)
	{
		// TODO: What if the word contains '_'? We should try with all the letters and check whether it exists, if it exists we should replace it with the same function
		try
		{
			File			file	= new File(DICTIONARY_PATH);
			List<String>	lines	= Files.readAllLines(file.toPath());
			return Collections.binarySearch(lines, word) >= 0;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}// end try - catch
	}// end wordExists
}// end Dictionary - class
