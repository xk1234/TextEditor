package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{	
		ListNode prevWord = null;
		String[] srcarr = sourceText.split(" ");
		// TODO: Implement this method
		for (String word : srcarr) {
			word = word.replace("\n", "");
			word = word.replace(" ", "");
			if (word.length() > 0) {
				if (starter.equals("")) {
					starter = word;
					prevWord = new ListNode(word);
					wordList.add(prevWord);
				} else {
					// not first word - add to both prev word and current word
					ListNode searchCurr = search(wordList, word);
					if (searchCurr == null) {
						// word not found in wordlist
						searchCurr = new ListNode(word);
						wordList.add(searchCurr);
					}
					prevWord.addNextWord(word);
					prevWord = searchCurr;
				}
			}
		}
		if (wordList.size() == 0) {
			return;
		} else {
			ListNode last = wordList.get(wordList.size() - 1);
			last.addNextWord(starter);
		}
		
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		if (wordList.size() == 0 || numWords == 0) {
			return "";
		}
	    // TODO: Implement this method
		String result = starter;
		String currWord = starter;
		for (int i = 0; i < (numWords- 1); i++) {
			ListNode currNode = search(wordList, currWord);
			String nextWord = currNode.getRandomNextWord(rnGenerator);
			result += " " + nextWord;
			currWord = nextWord;
		}
		return result;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		wordList = new LinkedList<ListNode>();
		starter = "";
		ListNode prevWord = null;
		String[] srcarr = sourceText.split(" ");
		// TODO: Implement this method
		for (String word : srcarr) {
			word = word.replace("\n", "");
			word = word.replace(" ", "");
			if (word.length() > 0) {
				if (starter.equals("")) {
					starter = word;
					prevWord = new ListNode(word);
					wordList.add(prevWord);
				} else {
					// not first word - add to both prev word and current word
					ListNode searchCurr = search(wordList, word);
					if (searchCurr == null) {
						// word not found in wordlist
						searchCurr = new ListNode(word);
						wordList.add(searchCurr);
					}
					prevWord.addNextWord(word);
					prevWord = searchCurr;
				}
			}
		}
		if (wordList.size() == 0) {
			return;
		} else {
			ListNode last = wordList.get(wordList.size() - 1);
			last.addNextWord(starter);
		}
	}
	
	// TODO: Add any private helper methods you need here.
	public ListNode search(List<ListNode> wordl, String word) {
		// returns index of word in wordlist
		for (ListNode ln : wordl) {
			if (ln.getWord().equals(word)) {
				return ln;
			}
		}
		return null;
	}
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		System.out.println(gen.generateText(20).split(" ").length);
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		int upperbound = nextWords.size();
		int selected = generator.nextInt(upperbound);
		String w = nextWords.get(selected);
	    return w;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


