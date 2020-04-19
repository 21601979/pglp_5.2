package fr.uvsq._2;

import java.time.LocalDate;

import fr.uvsq._2.Personnel.builder;

public class main {

    public static void main(String[] args){
       
       BDD.delBDD();
       BDD.initBDD();
       builder b = new builder("bow","ser",LocalDate.of(1, 1, 1),1);
       b.setfonction("roi");
       b.settelephone("11");
       builder b2 = new builder("bow","ser",LocalDate.of(1, 1, 1),1);
       b2.setfonction("roi");
       b2.settelephone("11");
       b2.settelephone("00");
       PersonnelJdbcDAO test = new PersonnelJdbcDAO();
       try {
        test.create(b.build());
        test.update(b2.build());
    } catch (ExisteDejaException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
       Personnel res = test.find("1");
       System.out.print(b.toString()+ " " +res);
       test.find("1");
       test.delete(b2.build());
    }
}

