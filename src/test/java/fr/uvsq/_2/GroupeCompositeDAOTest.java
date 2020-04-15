package fr.uvsq._2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.time.LocalDate;

import org.junit.Test;

import fr.uvsq._2.Personnel.builder;

public class GroupeCompositeDAOTest {
    @Test
    public void SerialisationTest() {
        GroupeComposite g = new GroupeComposite(1);  
        GroupeComposite g2 = new GroupeComposite(2);
        builder b = new builder("charl","atant",LocalDate.of(1, 1, 1),1);
        Personnel p = b.build();
        builder b2 = new builder("bri","oche",LocalDate.of(1, 1, 1),1);
        Personnel p2 = b2.build();
        g.add(p);
        g.add(g2);
        g2.add(p2);
        DAO<GroupeComposite> DAOP = DAOFactory.getGroupeCompositeDAO();
        DAOP.serialize(g, "test");
        GroupeComposite g3 = DAOP.deserialize("Test");
        assertEquals(g.toString(),g3.toString());
    }
    
    @Test
    public void CreateFindTest() throws ExisteDejaException{
        File del = new File("GroupeComposite\\" + 1);
        del.delete();
        GroupeComposite g = new GroupeComposite(1);  
        GroupeComposite g2 = new GroupeComposite(2);
        builder b = new builder("charl","atant",LocalDate.of(1, 1, 1),2);
        Personnel p = b.build();
        builder b2 = new builder("glob","glob",LocalDate.of(1, 1, 1),1);
        Personnel p2 = b2.build();
        g.add(p);
        g.add(g2);
        g2.add(p2);
        DAO<GroupeComposite> DAOP = DAOFactory.getGroupeCompositeDAO();
        DAOP.create(g);
        assertEquals(DAOP.find("1").toString(),g.toString());
    }

    @Test
    public void DeletTest() {
        File del = new File("GroupeComposite\\" + 3);
        del.delete();
        GroupeComposite g = new GroupeComposite(3);  
        DAO<GroupeComposite> DAOP = DAOFactory.getGroupeCompositeDAO();
        try {
            DAOP.create(g);
        } catch (ExisteDejaException e) {
            e.printStackTrace();
        }
        DAOP.delete(g);
        
        assertTrue(DAOP.find("3") == null);
    }
    
    @Test
    public void UpdateTest() {
        File del = new File("GroupeComposite\\" + 3);
        del.delete();
        GroupeComposite g = new GroupeComposite(3);  
        DAO<GroupeComposite> DAOP = DAOFactory.getGroupeCompositeDAO();
        try {
            DAOP.create(g);
        } catch (ExisteDejaException e) {
            e.printStackTrace();
        }
        builder b = new builder("charl","atant",LocalDate.of(1, 1, 1),2);
        Personnel p = b.build();
        g.add(p);
        DAOP.update(g);
        assertEquals(g.toString(),DAOP.find("3").toString());
    }
}
