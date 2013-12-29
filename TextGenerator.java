///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            TextGenerator
// Files:            MarkovModel.java, State.java, StateContainer.java,
//					 Transition.java, Utility.java
// Semester:         CS302 Fall 2013
//
// Author:           Minh Bui
// Email:            mbui2@wisc.edu
// CS Login:         minh
// Lecturer's Name:  Shreed Hardikar
// Lab Section:      301
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     null
// Email:            null
// CS Login:         null
// Lecturer's Name:  null
// Lab Section:      null
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          Tyler
//////////////////////////// 80 columns wide //////////////////////////////////


/**
 * This class contains the program's main method.  
 * It also oversees the construction of the Markov model as well as 
 * the traversal of the constructed model.
 */
public class TextGenerator 
{
	private static final int GENERATION_LENGTH = 200;
	
	/**
	 * The main method starts the entire the program's execution. This main 
	 * method's only responsibility is to create a new GUI object.
	 */
    public static void main(String[] args) 
    {    
    	Utility.startGui();
    }
	
	/**
	 * This method is called from the GUI object when the "Generate!" button 
	 * is clicked. This method receives a String that stores the entire raw 
	 * text from the input text box in the GUI.  This method first splits this 
	 * String into an array of Strings where each String in this array is a 
	 * word from the text box.  This method then uses this sequence of words to 
	 * build a  Markov model object.  Finally, this method calls the Markov 
	 * model object's <strong>generateText</strong> to traverse the 
	 * constructed model. This method returns the final result of this 
	 * traversal.
	 * 
	 * @param rawText the entire text from the input text box in the GUI
	 * @return the final result that will be printed to the output text box
	 * in the GUI
	 */
	public static String runMarkovTextGeneration(String rawText)
	{
		String[] processedText = Utility.splitWords(rawText);
		MarkovModel MarkovModel = new MarkovModel(processedText);
		System.out.println(MarkovModel);
		return MarkovModel.generateText(GENERATION_LENGTH);
	}

}
