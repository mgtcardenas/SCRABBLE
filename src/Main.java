import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

public class Main
{
	private static final String DICTIONARY_PATH = "C:\\Users\\mgtca\\IdeaProjects\\SCRABBLE\\src\\english-dictionary.txt";
	
	public static void main(String[] args) throws Exception
	{
		Game scrabble = new Game();
		scrabble.play();
		System.out.println("Just to make a point");
	}// end main
	
	/**
	 * Determines whether a word exists or not by consulting
	 * a dictionary file using binary search. The path to the dictionary
	 * must be correctly set as a constant in this source file
	 *
	 * @param  word - the word we want to know exists or not
	 * @return      - true if the word exists, false if it does not
	 */
	private static boolean wordExists(String word)
	{
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
}// end Main - class
