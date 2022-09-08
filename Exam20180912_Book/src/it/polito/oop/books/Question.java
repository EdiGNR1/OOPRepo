package it.polito.oop.books;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.*;

public class Question implements Comparable<Question>{
		private String text;
		private Topic topic;
		Map<String, Boolean> risposte = new TreeMap<>();
		
		
		public Question(String text, Topic topic) {
		
			this.text = text;
			this.topic = topic;
		}

		public String getQuestion() {
			return this.text;
		}
		
		public Topic getMainTopic() {
			return this.topic;
		}

		public void addAnswer(String answer, boolean correct) {
			risposte.put(answer, correct);
		}
		
	    @Override
	    public String toString() {
	        return this.text+" "+"("+this.topic+")";
	    }

		public long numAnswers() {
			return risposte.size();
		    
		}

		public Set<String> getCorrectAnswers() {
			Set<String> res = Set.copyOf(risposte.entrySet().stream().filter(x->x.getValue()==true).map(Entry::getKey).collect(Collectors.toList()));
			return res;
		
			}
		public Set<String> getIncorrectAnswers() {
			Set<String> res = Set.copyOf(risposte.entrySet().stream().filter(x->x.getValue()==false).map(Entry::getKey).collect(Collectors.toList()));
			return res;
		}

		public Map<String, Boolean> getRisposte() {
			return risposte;
		}

		@Override
		public int compareTo(Question o) {
			
			return this.topic.getKeyword().compareTo(o.getMainTopic().getKeyword());
		}

		
		
		
}
