package fr.uvsq._2;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import fr.uvsq._2.Personnel.builder;

public class groupeCompositeTest 
{   
    @Test
	public void addTest()
	{
		GroupeComposite g = new GroupeComposite(1);
		GroupeComposite g2 = new GroupeComposite(2);

		builder b = new builder("bow","ser",LocalDate.of(1, 1, 1),1);
		Personnel p = b.build();
		g.add(p);
		g.add(g2);
		assertEquals(g.getL().get(0),p);
	} 
	
    @Test
	public void lecture_compositeTest()
	{
		GroupeComposite g = new GroupeComposite(1);  
		GroupeComposite g2 = new GroupeComposite(2);

		builder b = new builder("bow","ser",LocalDate.of(1, 1, 1),1);
		Personnel p = b.build();
		builder b2 = new builder("ma","rio",LocalDate.of(1, 1, 1),1);
		Personnel p2 = b2.build();
		g.add(p);
		g.add(g2);
		g2.add(p2);
		System.out.println(g);
	}
}
