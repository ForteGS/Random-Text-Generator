<h3> Random text generator </h3>
A non-sense text generator using Markov Model. <br>
*** UPDATE Jan 16th *** <br>
Improve accuracy in generating next random words using shorter and quicker method in dertermining next words when generating text. 

<h2> Classes </h2>
<ul>
	<li>MarkovModelEC.java - the main engine class for the algorithms</li>
	<li>State.java - Each state is represent by an unique word in the given text.</li>
	<li>StateContainer.java - stores states</li>
	<li>TextGenerator.java - main class</li>
	<li>Transition.java - Calculate the probability for each state to other states.</li>
	<li>Utility.java - Random objects, constants, seeds, and stuffs.</li>
</ul>

Compiling:<br>
<code> javac *.java </code>
