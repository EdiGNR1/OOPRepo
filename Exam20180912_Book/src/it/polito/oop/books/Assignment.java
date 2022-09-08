package it.polito.oop.books;

import java.util.*;


public class Assignment {

	String id;
	ExerciseChapter chapter;
	Map<Question, List<String>> risposte = new TreeMap<>();
	List<Double> punteggi = new ArrayList<>();
	
	
	
    public Assignment(String id, ExerciseChapter chapter) {
		super();
		this.id = id;
		this.chapter = chapter;
	}

	public String getID() {
        return id;
    }

    public ExerciseChapter getChapter() {
        return chapter;
    }

    public double addResponse(Question q,List<String> answers) {
        risposte.put(q, answers);
        
        int FP=0;
        int FN=0;
        
        for(String s : answers) {
        	if(q.getIncorrectAnswers().contains(s)) {
        		FP++;
        	}
        	
        }
        
        for(String s : q.getCorrectAnswers()) {
        	if(!answers.contains(s)) {
        			FN++;
        	}
        }
        
      //  System.out.println(FN+","+FP+","+q.getRisposte().size());
        
        double res =  ((double)(q.getRisposte().size() - FP - FN)/q.getRisposte().size());
        punteggi.add(res);
    	return res;
    }
    
    public double totalScore() {
    	//System.out.println(punteggi);
        return punteggi.stream().mapToDouble(Double::valueOf).sum();

    }

}
