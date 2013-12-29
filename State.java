///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TextGenerator.java
// File:             State.java
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
 * This class implements a single state in the Markov model. Each State object
 * represent single unique word in the input text. Furthermore, each State
 * object stores all Transition objects that transition from this state to other
 * states in the model.
 */
public class State {
	// TODO: Create private instance variables
	private ArrayList<Transition> transitions = new ArrayList<Transition>();
	private int id;
	private String word;

	// HINT: Each state needs a data structure that will store an arbitrary
	// number of Transition objects.
	/**
	 * Constructor
	 */
	public State() {
		// TODO: Implement this method.

	}

	/**
	 * @return the unique integer ID of this state
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the unique integer ID of this state
	 */
	public void setId(int id) {
		this.id = id;
	}    

	/**
	 * @return the unique word in the input text associated with this state
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word
	 *            the unique word in the input text associated with this state
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * Add an outgoing transition from this state to some other state.
	 * 
	 * @param transition
	 *            the new transition
	 */
	public void addTransition(Transition transition) {
		// TODO: Implement this method.
		transitions.add(transition);
	}

	/**
	 * @return the array list containing all transition objects moving from this
	 *         state
	 */
	public ArrayList<Transition> getTransitions() {
		return transitions;
	}

	/**
	 * Increment the count for the transition that moves from this state to the
	 * argument state. If this transition does not exist, this method creates a
	 * new transition from this state to the specified state and sets this new
	 * transitions count to 1. Otherwise, if the transition exists, this method
	 * finds the transition and increments the transition's count variable by
	 * one.
	 * 
	 * @param nextState
	 *            the state that the transition from this state moves to
	 * @return the transition whose count variable was incremented by this
	 *         method
	 */
	public Transition incrementTransition(State nextState) {
		// Initialize a new Transition type variable
		Transition nextTransition = new Transition();
		// If the next state does not have any transition, create a new
		// transition, add it to the transitions list and increment
		// the count to 1. Else increment the found transition.
		if (findTransition(nextState) == null) {
			nextTransition = createTransition(nextState);
		} else {
			nextTransition = findTransition(nextState);
		}
		nextTransition.incrementCount();
		return nextTransition;

	}

	/**
	 * Create a new transition from this state to a specified state.
	 * 
	 * @param nextState
	 *            the state that the new transition moves to
	 * @return the newly created transition
	 */
	public Transition createTransition(State nextState) {
		// Create a new transition and assign its original id, destination id
		// to the state's id and the next state's id.
		Transition nextTransition = new Transition();
		nextTransition.setDestinationId(nextState.id);
		nextTransition.setOriginId(id);
		// Add the newly created transition to the list of the current state's
		// transition.
		transitions.add(nextTransition);
		return nextTransition;

	}

	/**
	 * Find and return a transition from this state to the specified state. If
	 * this transition does not exist this method returns null.
	 * 
	 * @return the transition that moves from this state to the specified state,
	 *         if such a transition does not exist, this method returns null
	 */
	public Transition findTransition(State nextState) {
		// Traverse the list of the transitions to see if the DestinationId
		// of that transition equals to the next state's id. If found the match
		// return that transition. Otherwise, return null.
		for (int i = 0; i < transitions.size(); i++) {
			if (transitions.get(i).getDestinationId() == nextState.id) {
				return transitions.get(i);
			}
		}
		return null;
	}
}
