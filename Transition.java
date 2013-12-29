///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TextGenerator.java
// File:             Transition.java
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

/**
 * This class implements a transition between two states in the Markov model: an
 * "origin" state and a "destination" state. That is, each Transition object
 * represents a transition <i>from</i> the origin state <i>to</i> the
 * destination state. Each Transition object stores a "count" that records the
 * number of times we observe in the original text that the word associated with
 * the destination state follows the word associate with the origin state.
 */
public class Transition {
	private int destinationId;
	private int originId;
	private int count;
	
	/**
	 * Constructor.
	 */
	public Transition()
	{
	    // TODO: Implement this method.
	}
	
	/**
	 * Get the integer ID of the state this transition moves to.
	 * 
	 * @return the destination ID
	 */
	public int getDestinationId() {
		return this.destinationId;
	}

	/**
	 * Set the integer ID of the state this transition moves to.
	 * 
	 * @param destinationId
	 *            the destination ID
	 */
	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}

	/**
	 * Get the ID of the state this transition moves from.
	 * 
	 * @return
	 */
	public int getOriginId() {
		return originId;
	}

	/**
	 * Set the ID of the state this transition moves from.
	 * 
	 * @param originId
	 *            the ID of the state this transition moves from
	 */
	public void setOriginId(int originId) {
		this.originId = originId;
	}

	/**
	 * Increment this transition's counter.
	 * 
	 * @return the new counter after it has been incremented
	 */
	public int incrementCount() {
		count++;
		return count;
	}

	/**
	 * Get the counter variable associated with this transition.
	 * 
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

}
