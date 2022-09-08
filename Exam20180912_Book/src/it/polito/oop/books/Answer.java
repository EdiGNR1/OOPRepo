package it.polito.oop.books;

public class Answer {
	
	private String text;
	private boolean a;
	public Answer(String text, boolean a) {
		
		this.text = text;
		this.a = a;
	}
	public boolean isA() {
		return a;
	}
	
   public String toString() {
	   return text;
   }
}
