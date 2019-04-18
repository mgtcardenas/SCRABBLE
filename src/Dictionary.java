import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

/**
 * @author Marco Cárdenas
 *
 *         This class handles the request from a player to know whether a certain word exists in the english vocabulary.
 *         To know that, it reads an ordered dictionary file and performs a binary search over it.
 */
public class Dictionary
{
	private static final String	DICTIONARY_PATH	= "C:\\Users\\mgtca\\IdeaProjects\\SCRABBLE\\src\\english-dictionary.txt";
	private static final char[]	ALPHABET		= new char[] { 'A', 'B', 'C', 'D', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	
	/**
	 * Determines whether a word exists or not by consulting
	 * a dictionary file using binary search. The path to the dictionary
	 * must be correctly set as a constant in this source file
	 *
	 * @param  word the word we want to know exists or not
	 * @return      true if the word exists, false if it does not
	 */
	public static boolean wordExists(String word)
	{
		try
		{
			File			file	= new File(DICTIONARY_PATH);
			List<String>	lines	= Files.readAllLines(file.toPath());
			
			if (word.contains("_"))
			{
				if (word.indexOf('_') == word.lastIndexOf('_'))
					return singleReplaceSearch(word, lines);
				else
					return doubleReplaceSearch(word, lines);
			}
			else
			{
				return Collections.binarySearch(lines, word) >= 0;
			}// end if - else
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}// end try - catch
	}// end wordExists
	
	/**
	 * Determines whether the word with one underscore (wildcard) is present in the dictionary.
	 * It uses a single replace searching through all the letters of the alphabet once, so at most
	 * it will search the dictionary 26 times.
	 *
	 * @return true if the word is in the dictionary / false if it is not in the dictionary
	 */
	private static boolean singleReplaceSearch(String word, List<String> lines)
	{
		char[] wordLetters;
		
		for (char firstLetter : ALPHABET)
		{
			wordLetters						= word.toCharArray();
			wordLetters[word.indexOf('_')]	= firstLetter;
			if (Collections.binarySearch(lines, new String(wordLetters)) >= 0)
				return true;
		}// end foreach
		
		return false;
	}// end singleReplaceSearch
	
	/**
	 * Determines whether the word with two underscores (wildcards) is present in the dictionary
	 * It uses a double replace searching through all the letters of the alphabet twice, so at most
	 * it will search the dictionary 26 x 26 times.
	 * 
	 * @return true if the word is in the dictionary / false if it is not in the dictionary
	 */
	private static boolean doubleReplaceSearch(String word, List<String> lines)
	{
		char[] wordLetters;
		
		for (char firstLetter : ALPHABET)
		{
			wordLetters						= word.toCharArray();
			wordLetters[word.indexOf('_')]	= firstLetter;
			
			for (char secondLetter : ALPHABET)
			{
				wordLetters[word.lastIndexOf('_')] = secondLetter;
				
				if (Collections.binarySearch(lines, new String(wordLetters)) >= 0)
					return true;
			}// end foreach
		}// end foreach
		
		return false;
	}// end doubleReplaceSearch
}// end Dictionary - class
