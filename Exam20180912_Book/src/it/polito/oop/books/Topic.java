package it.polito.oop.books;

import java.util.*;
import java.util.stream.Collectors;

public class Topic {
    
	
	private String topic;
	List<Topic> subtopics = new ArrayList<Topic>();
	
	
	
	public Topic(String topic) {
		
		this.topic = topic;
	}
	
	public String getKeyword() {
        return this.topic;
								}
	
	
	@Override
	public String toString() {
	    return topic;
	}

	public boolean addSubTopic(Topic topic) {
		
		if(subtopics.contains(topic)) {
		return false;
		}else {
			subtopics.add(topic);
			return true;
		}
        
		
	}

	/*
	 * Returns a sorted list of subtopics. Topics in the list *MAY* be modified without
	 * affecting any of the Book topic.
	 */
	public List<Topic> getSubTopics() {
		return subtopics.stream().sorted(Comparator.comparing(Topic::getKeyword)).collect(Collectors.toList());
      	}
}
