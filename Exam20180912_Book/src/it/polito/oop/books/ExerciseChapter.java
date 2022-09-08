package it.polito.oop.books;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.*;


public class ExerciseChapter {

	String title;
	int numPages;
	List<Question> domande= new ArrayList<Question>();
	
	
	
    public ExerciseChapter(String title, int numPages) {
		super();
		this.title = title;
		this.numPages = numPages;
	}


	public List<Topic> getTopics() {
		
		
        return domande.stream().map(Question::getMainTopic)
        .sorted(Comparator.comparing(Topic::getKeyword)).distinct().collect(Collectors.toList());
	};
	

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
    	this.title=newTitle;
    }

    public int getNumPages() {
        return numPages;
    }
    
    public void setNumPages(int newPages) {
    	this.numPages=newPages;
    }
    

	public void addQuestion(Question question) {
		domande.add(question);
	}	
}
