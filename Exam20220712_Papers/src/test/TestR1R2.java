package test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import journals.JException;
import journals.Journals;

public class TestR1R2 {
	Journals js = new Journals();
	
	@Before
    public void setUp() {
		js = new Journals();
	}
	
	//R1
	
	@Test
	public void addJournal1() throws JException {
		int j = js.addJournal("jSwModels", 2.6);
		
		assertEquals(9, j);
	}
	
	@Test (expected=JException.class)
	public void addJournal2() throws JException {
		js.addJournal("jSwModels", 2.6);
		js.addJournal("jSwModels", 2.7);
	}
	
	@Test
	public void getImpactF1() throws JException {
		js.addJournal("jSwModels", 2.6);
		double dbl = js.getImpactFactor("jSwModels");
		
		assertEquals ("Wrong journal IF",
				2.6, dbl, 0.1);
	}
	
	@Test (expected=JException.class)
	public void getImpactF2() throws JException {
		js.getImpactFactor("jSwModels");
	}
	
	@Test
	public void groupJournalsByImpactFactor1() throws JException {
		js.addJournal("jSwModels", 2.6);
		js.addJournal("jDomainDrivenDesign", 2.5);
		js.addJournal("jMicroServices", 2.6);
		SortedMap<Double, List<String>> map = js.groupJournalsByImpactFactor();

		assertNotNull("Missing journals by IF", map);
		assertEquals("{2.5=[jDomainDrivenDesign], 2.6=[jMicroServices, jSwModels]}",
					 map.toString());
	}
	
	//R2
	
	@Test
	public void registerAuthors1() {
		int j = js.registerAuthors ("x1" , "z1", "y1");
		
		assertEquals(3, j);
	}
	
	@Test
	public void registerAuthors2() {
		int j = js.registerAuthors ("x1" , "z1", "y1");
		j = js.registerAuthors ("x2" , "z1", "x3");
		
		assertEquals(5, j);
	}
	
	@Test
	public void addPaper1() throws JException {
		js.addJournal("jSwModels", 2.6);
		js.registerAuthors ("x1" , "z1", "y1");
		String result  = js.addPaper ("jSwModels", "UML", "x1", "y1");
		
		assertEquals ("jSwModels" + ":" +"UML",
					  result);
	}
	
	@Test (expected=JException.class) //the journal is missing
	public void addPaper2() throws JException {
		js.registerAuthors ("x1" , "z1", "y1");
		js.addPaper ("jSwModels", "UML", "x1", "y1");
	}
	
	@Test (expected=JException.class) //dup title 
	public void addPaper3() throws JException {
		js.addJournal("jSwModels", 2.6); 
		js.registerAuthors ("x1" , "z1", "y1");
		js.addPaper ("jSwModels", "UML", "x1", "y1");
		js.addPaper ("jSwModels", "UML", "x1", "y1");
	}
	
	
	@Test (expected=JException.class) //unregistered authors 
	public void addPaper4() throws JException {
		js.addJournal("jSwModels", 2.6); 
		js.registerAuthors ("x1" , "z1", "y1");
		js.addPaper ("jSwModels", "UML", "x2", "y1");
	}
	
	@Test
	public void numberOfPapersByJournal() throws JException {
		js.addJournal("jSwModels", 2.6);
		js.addJournal("jDomainDrivenDesign", 2.5);
		js.addJournal("jMicroServices", 2.6);
		js.registerAuthors ("x1" , "z1", "y1");
		js.addPaper ("jSwModels", "UML", "x1");
		js.addPaper ("jSwModels", "PN", "x1");
		js.addPaper ("jDomainDrivenDesign", "DDD", "y1");
		js.addPaper ("jMicroServices", "approach1", "z1");
		js.addPaper ("jMicroServices", "approach2", "z1");
		SortedMap<String, Integer> map2 = js.giveNumberOfPapersByJournal();
		
		assertNotNull("Missing paper no", map2);
		assertEquals ("{jDomainDrivenDesign=1, jMicroServices=2, jSwModels=2}", 
					  map2.toString());
	}
}
