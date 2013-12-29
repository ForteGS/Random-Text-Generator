///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TextGenerator.java
// File:             StateContainer.java
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

/**
 * This class is used to store the state objects that comprise the Markov model.
 */
public class StateContainer {

	private ArrayList<State> states = new ArrayList<State>();

	// HINT: Each object of this class needs a data structure to store an
	// arbitrary number of State objects.

	/**
	 * Constructor.
	 */
	public StateContainer() {
		// TODO: Implement this method.
	}

	/**
	 * @return the array list of all of the states in the model
	 */
	public ArrayList<State> getStates() {

		return this.states;
	}

	/**
	 * Add a state to this container.
	 * 
	 * @param newState
	 *            the new state
	 */
	public void addState(State newState) {
		states.add(newState);
	}

	/**
	 * Retrieve a state from this container that represents a specified word.
	 * 
	 * @param word
	 *            The word represented by the state that should be retrieved
	 * @return The state that stores the specified word. If a state with the
	 *         specified word does not exist in this container, then return
	 *         null.
	 */
	public State getStateByWord(String word) {
		// Traverse through the states lists and find the word specified.
		for (int i = 0; i < states.size(); i++) {
			if (states.get(i).getWord().equals(word) == true) {
				return states.get(i);
			}
		}
		return null;
	}

	/**
	 * Retrieve a state with a specified unique ID from this state container.
	 * 
	 * @param id
	 *            the unique integer of ID of the state to be retrieved
	 * @return
	 */
	public State getStateById(int id) {
		// Traverse the list of states' words to find the ID. If the state with
		// the specified ID found, return that state. Otherwise return null.
		for (int i = 0; i < states.size(); i++) {
			if (states.get(i).getId() == id) {
				return states.get(i);
			}
		}
		return null;
	}

	/**
	 * This method returns true if this container is storing a state with the
	 * specified word. It returns false if this container is storing no such
	 * state.
	 * 
	 * @param word
	 * @return true if a state associated with the specified word is stored in
	 *         this state container. False, if this state is not in the
	 *         container.
	 */
	public boolean containsWord(String word) {
		// Traverse the list of states' words to find the match. If the word is
		// found, return true. Otherwise return false.
		for (int i = 0; i < states.size(); i++) {
			if (states.get(i).getWord().equals(word)) {
				return true;
			}
		}
		return false;
	}
}
