package fr.uvsq._2;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import fr.uvsq._2.Personnel.builder;

public class DAOTest {

    @Test
    public void PersonnelDAOSerialisationTest()
    {
        builder b = new builder("taga","da",LocalDate.of(1, 1, 1),1);
        Personnel p = b.build();
        Personnel p2 = null;
        DAO<Personnel> DAOP = DAOFactory.getPersonnelDAO();
        DAOP.serialize(p, "test");
        p2 = DAOP.deserialize("Test");
        assertEquals(p.toString(),p2.toString());
    }
    
}
