package it.polito.po;

public class Quiz {
	final static public String[] questions = {
	"What is a 'baseline' in configuration management?",
	"Which among the following statements are correct?",
	"Which among the following statements are correct for a comparator?"
	};
	final static public String[][] options = {
	{
		"The first revision of a configuration item",
		"A set of configuration items, each in a specific version",
		"A frozen configuration",
		"A stable configuration item",
		"A configuration not yet ready for delivery"	},
	{
		"Stubs are units developed to drive other units during unit test",
		"In the V model the detailed specifications are used to devise the unit testing",
		"Code smell detects structures in the code that may negatively impact design quality",
		"Verification is often an internal process",
		"Exhaustive testing should be used in safety-critical products"	},
	{
		"A comparator implements method compareTo()",
		"A comparator implements method compare()",
		"A comparator contains abstract methods",
		"A comparator can be passed as argument to method sort() of class Collections",
		"A comparator must contain static methods only"	}
	};
	
	/**
	 * Return the index of the right answer(s) for the given question 
	 */
	public static int[] answer(int question){
		// TODO: answer the question
		
		switch(question){
			case 0: return new int[] {2}; // replace with your answers
			case 1: return new int [] {3,5}; // replace with your answers
			case 2: return new int [] {1,4}; // replace with your answers
		}
		return null; // means: "No answer"
	}

	/**
	 * When executed will show the answers you selected
	 */
	public static void main(String[] args){
		for(int q=0; q<questions.length; ++q){
			System.out.println("Question: " + questions[q]);
			int[] a = answer(q);
			if(a==null || a.length==0){
				System.out.println("<undefined>");
				continue;
			}
			System.out.println("Answer" + (a.length>1?"s":"") + ":" );
			for(int i=0; i<a.length; ++i){
				System.out.println(a[i] + " - " + options[q][a[i]]);
			}
		}
	}
}

