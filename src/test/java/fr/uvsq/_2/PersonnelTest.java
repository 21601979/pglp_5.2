package fr.uvsq._2;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import fr.uvsq._2.Personnel.builder;

public class PersonnelTest 
{
	@Test
	public void builderTest()
	{
		builder b = new builder("bow","ser",LocalDate.of(1, 1, 1),1);
		assertEquals(b.toString(),"[]  0001-01-01 ser bow");
	}
	
	@Test
	public void setfonctionTest()
	{
		builder b = new builder("bow","ser",LocalDate.of(1, 1, 1),1);
		b.setfonction("roi");
		assertEquals(b.toString(),"[] roi 0001-01-01 ser bow");
	}
	
	@Test
	public void settelephoneTest()
	{
		builder b = new builder("bow","ser",LocalDate.of(1, 1, 1),1);
		b.settelephone("0000000000");
		assertEquals(b.toString(),"[0000000000]  0001-01-01 ser bow");
	}
	
	@Test
	public void buildTest()
	{
		builder b = new builder("bow","ser",LocalDate.of(1, 1, 1),1);
		b.setfonction("roi");
		b.settelephone("0000000000");
		Personnel p = b.build();
		assertEquals(p.toString(),"[0000000000] roi 0001-01-01 ser bow");
	}
}