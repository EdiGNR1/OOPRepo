package it.polito.oop.books;

import java.util.*;
import java.util.stream.Collectors;

public class Book{
	
	Map<String, Question> domande =new TreeMap<>();
	Map<String,Topic> topics = new TreeMap<>();
	Map<String,TheoryChapter> capitoliTeoria = new TreeMap<>();
	Map<String,ExerciseChapter> capitoliEsercizi = new TreeMap<>();
	List<Assignment> compiti = new ArrayList<>();
	
	
    
	public Topic getTopic(String keyword) throws BookException {
		
		if(keyword==null || keyword.isEmpty()) {
			throw new BookException();
		}
		
		
		if(!topics.containsKey(keyword)) {
			topics.put(keyword, new Topic(keyword));
		}
		
		return topics.get(keyword);
	    
	    	
		   							}

	public Question createQuestion(String question, Topic mainTopic) {
		
		domande.put(question, new Question(question, mainTopic));
		topics.put(mainTopic.getKeyword(), mainTopic);
		return domande.get(question);
        
	}

	public TheoryChapter createTheoryChapter(String title, int numPages, String text) {
		
		TheoryChapter t = new TheoryChapter(title, numPages, text);
		capitoliTeoria.put(title, t);
		
		return capitoliTeoria.get(title);
      
	}

	public ExerciseChapter createExerciseChapter(String title, int numPages) {
		ExerciseChapter e = new ExerciseChapter(title, numPages);
		capitoliEsercizi.put(title, e);
		
		return capitoliEsercizi.get(title);
      
	}

	public List<Topic> getAllTopics() {
        return topics.values().stream().sorted(Comparator.comparing(Topic::getKeyword)).collect(Collectors.toList());
	}

	public boolean checkTopics() {
			 
	    Set<Topic> t = capitoliTeoria.values().stream().flatMap(c -> c.getTopics().stream())
		.collect(Collectors.toSet());
	    Set<Topic> e = capitoliEsercizi.values().stream().flatMap(c -> c.getTopics().stream())
		.collect(Collectors.toSet());
		
	    if(t.containsAll(e)) {
	    
            return true;
	     }
	    else {
	    	return false;
	    }
	}

	public Assignment newAssignment(String ID, ExerciseChapter chapter) {
		
		Assignment a = new Assignment(ID, chapter);
		compiti.add(a);
        return a;
	}
	
    /**
     * builds a map having as key the number of answers and 
     * as values the list of questions having that number of answers.
     * @return
     */
    public Map<Long,List<Question>> questionOptions(){
       
    	return domande.values().stream().collect(Collectors.groupingBy(Question::numAnswers, Collectors.toList()));
    	}
}
