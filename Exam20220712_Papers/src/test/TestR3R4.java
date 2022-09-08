package test;


import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;


import journals.JException;
import journals.Journals;

public class TestR3R4 {
	Journals js = new Journals();
	
	@Before
    public void setUp() throws JException {
		js = new Journals();
		js.addJournal("jSwModels", 2.6);
		js.addJournal("jDomainDrivenDesign", 2.5);
		js.addJournal("jMicroServices", 2.6);
		js.registerAuthors ("x1" , "z1", "y1", "z2");
		js.addPaper ("jSwModels", "UML", "x1");
		js.addPaper ("jSwModels", "PN", "y1");
		js.addPaper ("jDomainDrivenDesign", "DDD", "x1");
		js.addPaper ("jMicroServices", "approach1", "z1");
		js.addPaper ("jMicroServices", "approach2", "z1");
	}
	
	//R3
	
	@Test
	public void getImpactF() throws JException {
		double dbl = js.getAuthorImpactFactor ("x1");
		
		assertEquals ("Wrong author impact factor",
				5.1, dbl, 0.1);
	}
		
	@Test (expected=JException.class) //unregistered authors 
	public void getImpactF2() throws JException {
		js.getAuthorImpactFactor ("x2");
	}
	
	@Test
	public void getImpactF3() throws JException {
		double dbl = js.getAuthorImpactFactor ("z2"); //no papers

		assertEquals ("Wrong author impact factor",
				0.0, dbl, 0.1);
	}
	
	@Test
	public void impactFByAuthorsl() throws JException {
		SortedMap<Double, List<String>> map3 = js.getImpactFactorsByAuthors();
		
		assertNotNull("Missing IF by author", map3);
		assertEquals ("{2.6=[y1], 5.1=[x1], 5.2=[z1]}",
					  map3.toString());
	}
	
	
	//R4
	@Test
	public void nPapersByAuthor() throws JException {
		SortedMap<String, Integer> map4 =  js.getNumberOfPapersByAuthor();
		
		assertNotNull("Missing paper no by author", map4);
		assertEquals ( "{x1=2, y1=1, z1=2}",
				       map4.toString());
	}

	@Test
	public void journalWithManyPapers() throws JException {
		String journalName = js.getJournalWithTheLargestNumberOfPapers();

		assertEquals("jMicroServices:2",journalName);
	}	
}
