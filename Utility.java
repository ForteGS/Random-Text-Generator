

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * This Utility class provides a number of functionalities for building the 
 * Markov model program.
 */
public class Utility 
{
	/** 
	 * A seed value is used to create a deterministic random number generator.
	 * Using the same seed value in multiple runs, will cause the random number 
	 * generator to generate the same sequence of "random" numbers each time.
	 * Likewise, a different seed value will generate a different sequence of 
	 * random numbers.
	 * <br>
	 * <br>
	 * Seeding a random number generator allows your program to run 
	 * consistently on each run because the random numbers that your program 
	 * uses will be the same each time.  This makes testing your program much 
	 * easier.
	 */
	public static final int SEED = 7;

	/** 
	 * Use this Random Number Generator for all random numbers
	 * that must be generated.
	 */
	public static final Random RNG = new Random(SEED);
	
	/**
	 * This method creates a new GUI object.
	 */
	public static void startGui()
	{
    	@SuppressWarnings("unused")
    	Gui gui = new Gui();
	}
	
    /**
     * This method accepts a String whose value is the raw text from the GUI's 
     * input text box. This method then splits this String into separate words 
     * where each word is separated by a space character in the original 
     * String. This method stores each word in an array and returns this array.
     * 
     * @param rawText the text to be split at each space character
     * @return an array storing each word from the argument String
     */
    public static String[] splitWords(String rawText)
	{
		String[] words = rawText.split("\\s+");
		return words;
	}
	
    /**
     * The GUI class
     */
	@SuppressWarnings("serial")
	private static class Gui extends JFrame implements ActionListener
	{	
		private JTextArea inputArea;
		private JTextArea outputArea;
		
		/**
		 * The constructor builds the GUI.
		 */
		public Gui() 
		{
			// Set the initial location
	        this.setLocation(400, 250);
			
	        // Create the text labels
	        JLabel inputLabel = new JLabel("Input your text here:");
	        JLabel outputLabel = new JLabel("Press the \"Generate!\" button " +
	        		"and your output\n will apear here:");

			// Create text areas
			inputArea = new JTextArea(10,30);
	        outputArea = new JTextArea(10,30);
	        outputArea.setEditable(false);
	        
	        // Create scroll planes
	        JScrollPane inputScrollPlane = new JScrollPane(inputArea,
					  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        
	        JScrollPane outputScrollPlane = new JScrollPane(outputArea,
	        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	        		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        
	        // Create button
	        JButton generateButton = new JButton("Generate!");
	        generateButton.addActionListener(this);
	        
	        // Create main panel
	        JPanel panel =new JPanel();
	  
	        // Set layout
	        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	        
	        // Add elements to panel
	        panel.add(inputLabel);
	        panel.add(inputScrollPlane);
	        panel.add(outputLabel);
	        panel.add(outputScrollPlane);
	        panel.add(generateButton);

	        // Set the border
	        panel.setBorder(new TitledBorder("Markov Text Generator"));
	        panel.setBounds(5,5,440,515);
	        
	        // Add the panel to the frame's main panel
	        add(panel);
	        
	        // Set default parameters and show GUI
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(450,550);
	        this.setVisible(true);
	    }
	 
		/**
	     * This method is executed when the "Generate!" button is clicked.  
	     * It reads the text from the input text box and stores the text into 
	     * an array of Strings.  This method then calls the TextGenerator's 
	     * runMarkovTextGeneration method supplying this method with this array 
	     * of words. 
	     */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// Retrieve input text
			String rawText = inputArea.getText();
			
			// Run student's Markov text generator
			String result = TextGenerator.runMarkovTextGeneration(rawText);
			
			// Output result
			outputArea.setText(result);
		}
	}
}
