package it.polito.oop.books;

import java.util.*;
import java.util.stream.Collectors;


public class TheoryChapter extends Book{
	
	private String title;
	private int npages;
	private String text;
	List<Topic> topics = new ArrayList<>();
  
	
    public TheoryChapter(String title, int npages, String text)  {
        super();
        
		this.title = title;
		this.npages = npages;
		this.text = text;
	   
    }

	public String getText() {
		return text;
		
	}

    public void setText(String newText) {
    	this.text = newText;
    }


	public List<Topic> getTopics() {
		return topics.stream().distinct().sorted(Comparator.comparing(Topic::getKeyword)).collect(Collectors.toList());
		
	}

    public String getTitle() {
		return title;
     
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public int getNumPages() {
		return npages;
       
    }
    
    public void setNumPages(int newPages) {
        this.npages = newPages;
    }
    
    public void addTopic(Topic topic) {
    	topics.add(topic);
    	topics.addAll(topic.getSubTopics());
    	
    	List<Topic> temp= new ArrayList<>(topics);
    	
    	for(Topic t : temp) {
    		topics.addAll(t.getSubTopics());
    	}
    	List<Topic> temp2= new ArrayList<>(topics);
    	
    	for(Topic t : temp2) {
    		topics.addAll(t.getSubTopics());
    	}
    	
    }
    
}
