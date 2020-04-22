package fr.uvsq._2;

import java.time.LocalDate;

import fr.uvsq._2.Personnel.builder;

public class main {

    public static void main(String[] args){

       BDD.delBDD();
       BDD.initBDD();
       builder b = new builder("bow", "ser", LocalDate.of(1, 1, 1), 1);
       b.setfonction("roi");
       b.settelephone("11");
       builder b2 = new builder("pat", "ate", LocalDate.of(1, 1, 1), 2);
       b2.setfonction("legume");


       GroupeComposite g = new GroupeComposite(1);
       GroupeComposite g2 = new GroupeComposite(2);
       g2.add(b.build());
       g.add(b2.build());
       g.add(g2);

       DAO<GroupeComposite> d = ((DAOFactoryJDBC) AbstractDAOFactory.
               getFactory(AbstractDAOFactory.DAOtype.JDBC)).
               getGroupeCompositeDAO();
       try {
        d.create(g);
    } catch (ExisteDejaException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
       System.out.println("." + d.find("1").toString());
    }
}

