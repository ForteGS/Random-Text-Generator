///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TextGenerator.java
// File:             MarkovModelEC.java
// Semester:         CS302 Fall 2013
//
// Author:           Minh
// CS Login:         minh
// Lecturer's Name:  Shreed Hardikar
// Lab Section:      301
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     null
// CS Login:         null
// Lecturer's Name:  null
// Lab Section:      null
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          Tyler
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class implements a Markov model for use as in a nonsense text generator.
 * Each state in the model represents a single word in the input document.
 */
public class MarkovModelEC {
	int id = 0;
	StateContainer stateContainer = new StateContainer();
	Random rng = new Random();
	/**
	 * A two dimensional array list of lists of next words of the current word.
	 */
	ArrayList<ArrayList<String>> stateNextWords = new 
												ArrayList<ArrayList<String>>();

	/**
	 * A list of states that are in the state container.
	 */
	ArrayList<State> statesList = stateContainer.getStates();

	/**
	 * A list of states that precede the current state.
	 */
	ArrayList<State> nextStates = new ArrayList<State>();

	/**
	 * The constructor accepts an array of words representing the input text. <br>
	 * For example, if the original text read "Sam I am, that Sam I am", the
	 * array of words would store: <br>
	 * <br>
	 * ["Sam", "I", "am,", "that", "Sam", "I", "am"] <br>
	 * <br>
	 * The constructor then uses this array of words to build the states and
	 * transitions that comprise the Markov model. <br>
	 * <br>
	 * <b>IMPORTANT:</b> the state associated with the last word in the input
	 * text MUST include a transition to the state associated with the first
	 * word in the input text.
	 * 
	 * @param words
	 *            all of the words from the input text box in their original
	 *            order
	 */
	public MarkovModelEC(String[] words) {
		// Traversing through the words list and check if the word has
		// associated with a state of not. If not, create a new state for the
		// word and set an id for the new state.
		
		for (int i = 0; i < words.length; i++) {
			if (stateContainer.containsWord(words[i]) == false) {
				State createdState = new State();
				createdState.setWord(words[i]);
				createdState.setId(id);
				id++;
				stateContainer.addState(createdState);
			}
		}
		
		// Now each state will have an array list of the next words. This list
		// of next words does not contain duplicate one.
		
		for (int j = 0; j < statesList.size(); j++) {
			// Call listOfNextWords to add the list of next words.

			stateNextWords.add(listOfNextWords(statesList.get(j).getWord(),
					words));
		}
		
		// For each state, count the number of the duplicate next words. And
		// then increment the transition of that next state.
		
		String joinedWords = join(words, " ");
		for (int k = 0; k < stateNextWords.size(); k++) {
			for (int g = 0; g < stateNextWords.get(k).size(); g++) {
				
				// Find the String that contains the word in the state we're
				// working in with spaces (so that the word will not intervene
				// with each other) and the next word.
				
				int occurences = countWords(" " + statesList.get(k).getWord() + 
						" " + stateNextWords.get(k).get(g) + " ", joinedWords);

				/**
				 * The next fragment of code check if the word is in a special
				 * place or not. Increment the occurrences of the word and its
				 * next word if the word is the first word or the last word of
				 * the text. Occurrences also increments if the next state's
				 * word is the last word. The fragment of code "occurences++"
				 * needs to be repeated because what if a word is also the
				 * first, the last and the nearly last word. I have not found a
				 * better solution to deal with this problem without using
				 * repetition of "occurences++.
				 */

				if (statesList.get(k).getWord().equals(words[0])
						&& stateNextWords.get(k).get(g).equals(words[1])) {
					occurences++;
				}
				if (statesList.get(k).getWord().equals(words[words.length - 1])
						&& stateNextWords.get(k).get(g).equals(words[0])) {
					occurences++;
				}
				if (statesList.get(k).getWord().equals(words[words.length - 2])
						&& stateNextWords.get(k).get(g)
								.equals(words[words.length - 1])) {
					occurences++;
				}

				for (int p = 0; p < occurences; p++) {
					statesList.get(k).incrementTransition(
							stateContainer.getStateByWord(stateNextWords.get(k)
							.get(g)));
				}
			}
		}
	}
	
	/** This method return this MarkovModel's states container.
	 * 
	 * @return a StateContainer object.
	 */
	
	public StateContainer getStateContainer()
	{
		return stateContainer;
	}

	/**
	 * Return the model as a String. Each state should be separated by a line
	 * break. The first line of each state should print the state's word
	 * enclosed in square brackets. Each line thereafter should print each
	 * outward transition moving from the current state. Each transitions should
	 * be printed as follows: <br>
	 * <br>
	 * &lt;transition count> --> [&lt;destination state's word>] <br>
	 * <br>
	 * Example of printing a state whose word is "Sam": <br>
	 * 
	 * <br>
	 * [sam]<br>
	 * 2 --> [i]<br>
	 * 1 --> [sam]<br>
	 * 1 --> [let]<br>
	 * 1 --> [you]<br>
	 * 1 --> [if]<br>
	 * <br>
	 * 
	 * <b>The states must be printed in the order they were created.</b>
	 */
	@Override
	public String toString() {
		String toString = "";
		String state = "";
		String listOfTransition = "";
		for (int i = 0; i < statesList.size(); i++) {
			state = "[" + statesList.get(i).getWord() + "]\n";
			if (i == 0) {
				toString = toString + state;
			} else {
				toString = toString + "\n" + state;
			}
			for (int j = 0; j < statesList.get(i).getTransitions().size(); j++) 
			{
				int count = statesList.get(i).getTransitions().get(j)
						.getCount();
				String nextWord = stateContainer.getStateById(
						statesList.get(i).getTransitions().get(j)
								.getDestinationId()).getWord();
				listOfTransition = count + " -->" + " [" + nextWord + "]\n";
				toString = toString + listOfTransition;
			}
		}
		return toString;
	}

	/**
	 * This method traverses the states of the Markov model and generates the
	 * final nonsense text. For each state visited during the traversal, this
	 * method should append the word from the current state to a String variable
	 * that will store the entire resultant text. This method should then return
	 * this String. <br>
	 * <br>
	 * <b>IMPORTANT: The traversal starts at the state associated with the first
	 * word of the input text.</b> <br>
	 * <br>
	 * <b>IMPORTANT: The output text moves to a new line after every 10 words
	 * that are generated. <b>
	 * 
	 * @param numWords
	 *            the number of words to output (i.e. the number of states to
	 *            visit)
	 * @return a concatenation of all of the words from each state that was
	 *         visited when traversing the model.
	 */
	public String generateText(int numWords) {
		id = 0;
		String generatedText = "";
		for (int i = 0; i < numWords; i++)
		{
			if (i % 10 == 0 && i != 0)
			{
				generatedText += "\n";
			}
			if (i == numWords - 1)
			{
				generatedText += stateContainer.getStateById(id).getWord();
			}
			else
			{
				generatedText += stateContainer.getStateById(id).getWord() +
											" ";
			}
			
			id = moveToNextState(stateContainer.getStateById(id));
		}
		// HINT: In order to meet the requirement of printing a new line after
		// every 10 words, you must append the newline character, "\n",
		// to the resulting string after appending each set of 10 words.
		return generatedText;
	}

	/**
	 * This method accepts a State object that represents the "current" state
	 * while traversing the model, and outputs the ID of the "next" state. The
	 * next state is determined by examining all of the transitions from the
	 * current state and randomly picking the next state based on the
	 * probabilities of these transitions. <br>
	 * <br>
	 * For example, consider a state, <b>A</b>, with two transitions. The first
	 * of these transitions moves from <b>A</b> to <b>B</b> with a count
	 * variable of 3. The second transition moves from <b>A</b> to <b>C</b> with
	 * a count variable of 4. Then the probability that this method returns
	 * <b>B</b>'s ID is 3/7. Similarly, the probability that this method returns
	 * <b>C</b>'s ID is 4/7.
	 * 
	 * @param currState
	 *            the current state
	 * @return the ID of the next state we should move to
	 */
	public int moveToNextState(State currState) {
		double[] listOfProb = new double[currState.getTransitions().size()];
		double[] listOfProbSorted = new double[currState.getTransitions().size()];
		int sum = 0;
		int nextStateId = 0;
		double probability = 0;
		double nextStateProb = 0;
		double rand = 0;
		int count = 0;
		for (int i = 0; i < currState.getTransitions().size(); i++) {
			sum += currState.getTransitions().get(i).getCount();
		}
		for (int k = 0; k < currState.getTransitions().size(); k++)
		{
			probability = ((currState.getTransitions().get(k).getCount())/(sum * 1.0));
			listOfProb[k] = probability;
		}
		
		for (int j = 0; j < listOfProb.length; j++)
		{
			listOfProbSorted[j] = listOfProb[j];
 		}
		Arrays.sort(listOfProbSorted);
		while (nextStateProb == 0) {
			rand = rng.nextDouble();
			for (int g = 0; g < listOfProbSorted.length; g++)
			{
				if (rand < listOfProbSorted[g])
				{
					nextStateProb = listOfProbSorted[g];
				}
			}
		}
		
		for (int a = 0; a < listOfProb.length; a++)
		{
			if (listOfProb[a] >= nextStateProb - 0.001 || listOfProb[a] <= nextStateProb + 0.001)
			{
				count++;
			}
		}
		
		State[] states = new State[count];
		int o = 0;
			for (int u = 0; u < currState.getTransitions().size(); u++)
			{
				if ((nextStateProb * sum) <= currState.getTransitions().get(u).getCount() + 0.001 ||
					(nextStateProb * sum) >= currState.getTransitions().get(u).getCount() - 0.001)
				{
					states[o] = stateContainer.getStateById(currState.getTransitions().get(u).getDestinationId());
					o++;
				}
			}
		int x = rng.nextInt(count);
		nextStateId = states[x].getId();
		return nextStateId;
	}
	
	/** Calculate the average degree the states in the Markov model. 
	 *  A state's degree is defined as the number of transitions that move from 
	 *  the state. For example, the following state's degree is 2: 
	 *  [Sam]
	 *	23 --> [I]
	 *	11 --> [do]
	 * 
	 * @return the average degree in the states.
	 */
	
	public double calculateAverageDegree()
	{
		double avgDegree = 0;
		int degree = 0;
		for (int i = 0; i < statesList.size(); i++)
		{
			degree += statesList.get(i).getTransitions().size();
		}
		avgDegree = degree/(statesList.size() * 1.0);
		return avgDegree;
	}
	
	
	/** Calculate the average frequency of the words in the input text. 
	 * For example, in the following text, the word "Sam" has a frequency of 2:
	 *  "Sam I am. I do not like that Sam I am."
	 * @return The average frequency of the words in the input text
	 */
	public double calculateAverageFrequency()
	{
		
		double avgF = 0;
		int f = 0;
		for (int i = 0; i < statesList.size(); i ++)
		{
		}
		return 0;
	}
	
	/** This method calculates the mode of the degrees of states in this model. 
	 * A state's degree is defined as the number of transitions that move from 
	 * the state. The mode is the value that appears most often in a set of 
	 * values. For example, if we have the following set of 
	 * numbers [1,3,2,1,3,6,1], the mode is 1 because it appears most often. 
	 * Two values share the mode if they both have the same frequency in the 
	 * data AND they appear more often than any other value. For example, if we
	 * have 3 states in the model with a degree of 6, and 2 states with a degree
	 * of 4. The mode of the degree of the states would be 6 because this is the
	 * most frequently occurring number of degrees.
	 * 
	 * @return an array list of all of the degrees corresponding to the mode. 
	 * 			If there is one mode, this array list stores one value. For 
	 * 			example, given the following states:
     *
	 *			[A]
	 *		 	1 --> [A]
     *			1 --> [B]
	 *			2 --> [D]
     *
     *			[B]
	 *			1 --> [C]
     *
	 *			[C]
	 *			2 --> [C]
	 *			1 --> [D]
	 *			1 --> [A]
     *
	 *			[D]
	 *			1 --> [A]
     *
	 *			Two degree values share the mode. This method would then return
	 *			an array list storing [3, 1] the values of this mode.
	 */
	
	public ArrayList<Integer> calculateDegreeModes()
	{
		ArrayList<Integer> degreeModes = new ArrayList<Integer>();
		return degreeModes;
	}
	
	/** Get all words that occur with the specified frequency in the text.
	 * 
	 * @param frequency
	 * @return an array list containing each unique word that occurs in the text
	 * 			with the specified frequency.
	 */
	
	public ArrayList<String> getWordsOfFrequency(int frequency)
	{
		ArrayList<String> words = new ArrayList<String>();
		return words;
	}
	
	/** This method returns all states that have a specified degree. A state's 
	 * 	degree is defined as the number of transitions that move from the state.
	 * 
	 * @param degree
	 * @return an array list storing all states of the specified degree.
	 */
	
	public ArrayList<State> getStatesOfDegree(int degree)
	{
		ArrayList<State> statesOfDegree = new ArrayList<State>();
		
		return statesOfDegree;
	}

	/**
	 * This method creates an array list of the words that are preceding with
	 * the current state's word. This method traverses through the array words[]
	 * and the array list of states to find the matching word. The word that
	 * preceding the specified word then will be checked if they have been
	 * included or not; if not that word will be added to the array list
	 * nextWords. This method also solve the problem for the <b>LAST WORD</b>. <br>
	 * Example: <br>
	 * A String[] words = {"I", "am", "Sam,", "not", "Math.", "I", "am",
	 * "dead."} <br>
	 * List of states created: {"I", "am", "Sam,", "not", "Math.", "dead."} <br>
	 * The nextWords for "I" would be: {"am"} <br>
	 * The nextWords for "am" would be: {"Sam", "dead"} ... <br>
	 * The nextWords for "dead." would be: {"I"}
	 * 
	 * @param state
	 * @param words
	 * @param states
	 * @return the array list that contains the preceding word of the specified
	 *         word
	 */
	private ArrayList<String> listOfNextWords(String word, String[] words) {
		// Create a new ArrayList<String> to stores the next words of the given
		// word.
		
		ArrayList<String> nextWords = new ArrayList<String>();
		// Traversing through the list.
		
		for (int j = 0; j < words.length; j++) {
			if (word.equals(words[j])) {
				
				// Check if the word is the LAST WORD or not. If it is,
				// check
				// if the FIRST WORD has been included in the list or not.
				// if yes, add the word to the nextWords.
				
				if ((j + 1) == words.length) {
					if (findWord1(words[0], nextWords) == false) {
						nextWords.add(words[0]);
					}
				} else if (findWord1(words[j + 1], nextWords) == false) {
					nextWords.add(words[j + 1]);
				} else {
				}
			}
		}
		return nextWords;
	}

	/**
	 * This method return true if a specified word is found in the the list
	 * 
	 * @param word
	 * @param list
	 * @return true if the word is found and false otherwise.
	 */
	private boolean findWord1(String word, ArrayList<String> list) {
		for (int i = 0; i < list.size(); i++) {
			if (word.equals(list.get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method traverses through a two dimensional array list of string. It
	 * also traverses through another string array list. This method checks if
	 * the array list provided has the same elements as the sub array list in
	 * the two dimensional array list or not. <br>
	 * Example: two dimensional array = {{"name", "I"}, {"he"}} provided array
	 * list = {"he"} This example will return false;
	 * 
	 * @param arrayList
	 * @param twoDarrayList
	 * @return true if the array list provided is different from the sub array
	 *         lists in the 2D array.
	 */

	private boolean differentElements(ArrayList<String> arrayList,
			ArrayList<ArrayList<String>> twoDarrayList) {
		for (int i = 0; i < twoDarrayList.size(); i++) {
			if (arrayList.size() != twoDarrayList.get(i).size()) {
				return false;
			} else {
				for (int j = 0; j < arrayList.size(); j++) {
					if (arrayList.get(j).equals(twoDarrayList.get(i).get(j))) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * This method return true if a specified word is found in the the list
	 * 
	 * @param word
	 * @param list
	 * @return true if the word is found and false otherwise.
	 */
	private boolean findWord2(String word, String[] words) {
		for (int i = 0; i < words.length; i++) {
			if (word.equals(words[i])) {
				return true;
			}
		}
		return false;
	}

	/** This method joint a provided array of strings into String using 
	 * a given delimiter.
	 * 
	 * @param words
	 * @param delimiter
	 * @return A String that has delimiter connected each element in the array
	 * 			of String.
	 */
	private String join(String[] words, String delimiter) {
		String newWords = "";
		for (int i = 0; i < words.length; i++) {
			newWords = newWords + words[i] + delimiter;
		}
		newWords = newWords.substring(0, newWords.length() - 1);
		return newWords;
	}

	/**
	 * This method counts and returns the number of specified word in an array
	 * of string.
	 * 
	 * @param word
	 * @param words
	 * @return the number of occurences of a word.
	 */
	private static int countWords(String word, String words) {
		int count = 0;
		for (int i = 0; i < words.length() - word.length() + 1; i++) {
			if (word.equals(words.substring(i, i + word.length()))) {
				count++;
			}
		}
		return count;
	}
}
